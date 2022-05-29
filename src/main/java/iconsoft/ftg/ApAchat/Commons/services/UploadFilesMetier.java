package iconsoft.ftg.ApAchat.Commons.services;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFilesMetier {
    boolean uploadXLSFile(MultipartFile multipartFile, String categorie);
}
