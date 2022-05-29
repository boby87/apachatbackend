package iconsoft.ftg.ApAchat.Commons.services;

import iconsoft.ftg.ApAchat.Commons.Utils.CategorieUploadFile;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.ConstateBudget;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.LigneBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.PeriodeBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier.MetierLigneBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier.MetierPeriodeBudgetaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UploadFilesImpl implements UploadFilesMetier {
    @Autowired
    MetierLigneBudgetaire metierLigneBudgetaire;
    @Autowired
    MetierPeriodeBudgetaire metierPeriodeBudgetaire;

    @Override
    public boolean uploadXLSFile(MultipartFile multipartFile, String categorie) {
        File file = writeFile(multipartFile);

        if(categorie.equalsIgnoreCase(CategorieUploadFile.BUDGET.name())) {
            System.out.println("Traitement du fichier excel : " + file.getName());
            this.loadXlsLigneBudget(file);
        } else if (categorie.equalsIgnoreCase(CategorieUploadFile.DA.name())){
            System.out.println("Traitement du fichier excel : " + file.getName());
        }
        return false;
    }

    public File writeFile(MultipartFile multipartFile) {
        File file = new File(System.getProperty("user.dir") + "/tmp/" + multipartFile.getOriginalFilename());
        try {
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void loadXlsLigneBudget(File file) {
        XSSFWorkbook fichier;
        XSSFSheet feuille;
        FileInputStream fils;

        List<PeriodeBudgetaire> periodBudgs = new ArrayList<>();

        try {
            fils = new FileInputStream(file);

            fichier=new XSSFWorkbook(fils);
            int index=0;
            feuille=fichier.getSheetAt(1);
            Row row=feuille.getRow(index);

            for (int i = 1; i < 4; i++) {
                if(row.getCell(i)!=null){
                    periodBudgs.add(metierPeriodeBudgetaire.findByAnneeAndActiveIsTrue(row.getCell(1).getStringCellValue()));
                } else break;
            }
            row=feuille.getRow(index++);
            while (row!=null){
                for(int i = 0; i < periodBudgs.size() + 1; i++){
                    LigneBudgetaire ligneBudg = metierLigneBudgetaire.localSaveligne(new LigneBudgetaire(
                            row.getCell(0).getStringCellValue(),
                            row.getCell(i+1).getNumericCellValue(),
                            ConstateBudget.NON_VALIDE,
                            periodBudgs.get(i)));
                    periodBudgs.get(i).getLigneBudgetaires().add(ligneBudg);
                }
                row=feuille.getRow(index++);
            }
            periodBudgs.forEach(p-> metierPeriodeBudgetaire.localSaveperiodebudgetaire(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
