package iconsoft.ftg.ApAchat.gestionFournisseurs.viewRestfull;

import iconsoft.ftg.ApAchat.gestionFournisseurs.Dto.FournisseursDto;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Metier.MetierFournisseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("fournisseur")
public class FournisseurRestfull {

    @Autowired
    MetierFournisseur metierFournisseur;

    @GetMapping("all")
    List<FournisseursDto> findByActiveIsTrue(){
        return metierFournisseur.findByActiveIsTrue();
    }

    @GetMapping("byreference/{reference}")
    FournisseursDto findByReferenceAndActiveIsTrue(@PathVariable String reference){
        return metierFournisseur.findByReferenceAndActiveIsTrue(reference);
    }

    @PostMapping("save")
    FournisseursDto saveFournisseur(@RequestBody FournisseursDto fournisseursDto){
        return metierFournisseur.saveFournisseur(fournisseursDto);
    }
}
