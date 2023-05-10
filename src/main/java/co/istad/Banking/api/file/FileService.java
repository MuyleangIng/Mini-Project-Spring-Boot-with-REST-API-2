package co.istad.Banking.api.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileDto uploadSingle(MultipartFile file);

    List<FileDto> uploadMultiple(List<MultipartFile> files);

    List<FileDto> getAllFiles();

    FileDto removeFileByName(String filename);
    boolean removeAllFile();

    Resource getDownloadFileByName(String fileName);

}
