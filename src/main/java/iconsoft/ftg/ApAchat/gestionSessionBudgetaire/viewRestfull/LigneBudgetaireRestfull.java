package iconsoft.ftg.ApAchat.gestionSessionBudgetaire.viewRestfull;

import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Dto.LigneBudgetaireDto;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Entities.LigneBudgetaire;
import iconsoft.ftg.ApAchat.gestionSessionBudgetaire.Metier.MetierLigneBudgetaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("lignebudgetaire")
public class LigneBudgetaireRestfull {
    @Autowired
    MetierLigneBudgetaire metierLigneBudgetaire;


    @GetMapping("all")
    List<LigneBudgetaire> findall(){
        return metierLigneBudgetaire.findall();
    }
    @GetMapping("allactive")
    List<LigneBudgetaire> findByActiveIsTrue(){
        return metierLigneBudgetaire.findByActiveIsTrue();
    }
    @GetMapping("by/reference/{reference}")
    LigneBudgetaire findByReferenceAndActiveIsTrue(@PathVariable String reference){
        return metierLigneBudgetaire.findByReferenceAndActiveIsTrue(reference);
    }
    @PostMapping("save")
    LigneBudgetaire saveligne(@RequestBody LigneBudgetaireDto ligneBudgetaireDto){
        return metierLigneBudgetaire.saveligne(ligneBudgetaireDto);
    }
    @PutMapping("update")
    boolean updatelignebudgetaire(@RequestBody LigneBudgetaireDto ligneBudgetaireDto){
        return metierLigneBudgetaire.updatelignebudgetaire(ligneBudgetaireDto);
    }

}
