package iconsoft.ftg.ApAchat.Commons.viewRestfull;

import iconsoft.ftg.ApAchat.Commons.services.UploadFilesMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/upload")
public class UploadFilesRestfull {
    @Autowired
    UploadFilesMetier uploadFilesMetier;

    @PostMapping("/xlsfile/{categorie}")
    boolean updloadXLSFile(@RequestParam("file") MultipartFile multipartFile, @PathVariable String categorie){
        return uploadFilesMetier.uploadXLSFile(multipartFile, categorie);
    }
}
