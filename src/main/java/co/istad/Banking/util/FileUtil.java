package co.istad.Banking.util;

import co.istad.Banking.api.file.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {

    @Value("${file.server-path}")
    public String fileServerPath;

    @Value("${file.base-url}")
    public String fileBaseUrl;

    @Value("${file.base-download}")
    public String fileDownload;



    public FileDto upload(MultipartFile file){


        List<FileDto> uploadedFiles= new ArrayList<>();
        int lastDotIndex = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastDotIndex + 1);
        long size = file.getSize();
        String name = String.format("%s.%s", UUID.randomUUID(), extension);
        String downloadUrl =fileDownload + name;
        String url = String.format("%s%s", fileBaseUrl, name);

        Path path = Paths.get(fileServerPath + name);
        try {
            Files.copy(file.getInputStream(), path);
            FileDto fileDto = new FileDto(name, url,downloadUrl, extension, size);
            uploadedFiles.add(fileDto);
            return fileDto;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, " file failed!,Please Check");
        }
    }



}
