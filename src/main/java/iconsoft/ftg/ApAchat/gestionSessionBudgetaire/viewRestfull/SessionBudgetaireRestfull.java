package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.viewRestfull;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dto.PeriodeBudgetaireDto;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.PeriodeBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier.MetierPeriodeBudgetaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("budget")
public class SessionBudgetaireRestfull {
    @Autowired
    MetierPeriodeBudgetaire metierPeriodeBudgetaire;


    @GetMapping("all")
    List<PeriodeBudgetaire> findall(){
        return metierPeriodeBudgetaire.findall();
    }
    @GetMapping("allactive")
    List<PeriodeBudgetaireDto> findByActiveIsTrue(){
        return metierPeriodeBudgetaire.findByActiveIsTrue();
    }

    @GetMapping("by/reference/{reference}")
    PeriodeBudgetaireDto findByReferenceAndActiveIsTrue(@PathVariable String reference){
        return metierPeriodeBudgetaire.findByReferenceAndActiveIsTrue(reference);
    }

    @GetMapping("by/anneebudgetaire/{anneebudgetaire}")
    PeriodeBudgetaireDto findByAnneebugetaireAndActiveIsTrue(@PathVariable String anneebudgetaire){
        return metierPeriodeBudgetaire.findByAnneebugetaireAndActiveIsTrue(anneebudgetaire);
    }
    @PostMapping("save")
    PeriodeBudgetaireDto saveperiodebudgetaire(@RequestBody PeriodeBudgetaireDto periodeBudgetaireDto){
        return metierPeriodeBudgetaire.saveperiodebudgetaire(periodeBudgetaireDto);
    }

    @PatchMapping("update/status")
    String updateStatusPeriodeBudgetaire(@RequestBody PeriodeBudgetaireDto periodeBudgetaireDto){
        return metierPeriodeBudgetaire.changeStatusPBudget(periodeBudgetaireDto);
    }
}
