package co.istad.Banking.api.file;

import co.istad.Banking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@Slf4j
@RequiredArgsConstructor
public class FileRestController {

    @Value("${file.base-url}")
    public String fileBaseUrl;
    private final FileService fileService;

    @PostMapping
    public BaseRest<?> uploadSingle(@RequestPart MultipartFile file) {
        FileDto fileDto = fileService.uploadSingle(file);
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("File have been Uploaded Successfully....")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @PostMapping("/upload-multiple")
    public BaseRest<?> uploadMultiple(@RequestPart List<MultipartFile> files) {
        List<FileDto> fileDto = fileService.uploadMultiple(files);
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("File have been Uploaded Successfully.....")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> getListOfFile() {
        List<FileDto> fileDtos = fileService.getAllFiles();
        if (fileDtos.isEmpty()) {
            System.out.println("Files Not Found");
        }
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("File have been Retrieve *")
                .timestamp(LocalDateTime.now())
                .data(fileDtos)
                .build();
    }

    @DeleteMapping("/{name}")
    public BaseRest<?> removeFileByName(@PathVariable String name) {
        FileDto fileDto = fileService.removeFileByName(name);
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("This file have been deleted......")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }


    @DeleteMapping
    public BaseRest<?> removeAllFile() {
        boolean fileDto = fileService.removeAllFile();
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("There is no more file......")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<?> downloadFile(@PathVariable String name) {
        Resource resource = fileService.getDownloadFileByName(name);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() +"\"")
                .body(resource);


    }
}
