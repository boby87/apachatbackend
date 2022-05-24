package iconsoft.ftg.ApAchat.gestionBonCommande.viewRestfull;

import iconsoft.ftg.ApAchat.gestionBonCommande.Dto.BonCommandeDto;
import iconsoft.ftg.ApAchat.gestionBonCommande.Metier.MetierBonCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("bondecommande")
public class BonCommandeRestfull {
    @Autowired
    MetierBonCommande metierBonCommande;

@GetMapping("by/reference/{reference}")
    BonCommandeDto findByReferenceAndActiveIsTrue(@PathVariable String reference){
        return metierBonCommande.findByReferenceAndActiveIsTrue(reference);
    }
    @GetMapping("allactive")
    List<BonCommandeDto> findByActiveIsTrue(){
        return metierBonCommande.findByActiveIsTrue();
    }
    @PostMapping("save")
    BonCommandeDto save(@RequestBody BonCommandeDto bonCommandeDto){
        return metierBonCommande.save(bonCommandeDto);
    }
}
