package co.istad.Banking.api.file;


import co.istad.Banking.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private FileUtil fileUtil;
    @Value("${file.server-path}")
    public String fileServerPath;

    @Value("${file.base-download}")
    public String fileDownload;

    @Autowired
    private void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return fileUtil.upload(file);
    }
    @Override
    public Resource getDownloadFileByName(String fileName) {
        Resource resource = new PathResource(fileServerPath + fileName);

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Error: Empty  or cannot be read");
        }
        return resource;
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> filesDto = new ArrayList<>();
        for (MultipartFile file : files) {
            filesDto.add(fileUtil.upload(file));
        }
        return filesDto;
    }

    @Override
    public List<FileDto> getAllFiles() {
        List<FileDto> fileDtoList = new ArrayList<>();
        File folder = new File(fileUtil.fileServerPath);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String name = file.getName();
                String url = fileUtil.fileBaseUrl + name;
                long size = file.length();
                String downloadUrl =fileDownload + name;
                int lastDotIndex = name.lastIndexOf(".");
                String extension = name.substring(lastDotIndex + 1);

                fileDtoList.add(new FileDto(name, url, downloadUrl, extension, size));
            }
        }
        return fileDtoList;
    }


    @Override
    public FileDto removeFileByName(String filename) {

        FileDto fileDto = this.getAllFiles().stream().filter(
                fileDto1 -> fileDto1.name().equalsIgnoreCase(filename)).findFirst().orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found alert......!!!"));

        File file = new File(fileUtil.fileServerPath, filename);
        file.delete();
        return fileDto;
    }

    @Override
    public boolean removeAllFile() {
        if (this.getAllFiles().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no file.....check  !");
        }

        for (FileDto fileDto : this.getAllFiles()) {
            this.removeFileByName(fileDto.name());
        }

        return true;
    }



}
