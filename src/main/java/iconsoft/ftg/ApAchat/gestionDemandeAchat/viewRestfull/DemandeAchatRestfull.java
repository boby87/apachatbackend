package iconsoft.ftg.ApAchat.gestionDemandeAchat.viewRestfull;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DemandeAchatDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier.MetierDemandeAchat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("demandeachat")
public class DemandeAchatRestfull {
    @Autowired
    MetierDemandeAchat metierDemandeAchat;


    @GetMapping("by/reference/{reference}")
    DemandeAchatDto findByReferenceAndActiveIsTrue(@PathVariable String reference){
        return metierDemandeAchat.findByReferenceAndActiveIsTrue(reference);
    }
    @GetMapping("all")
    List<DemandeAchatDto> findByActiveIsTrue(){
        return metierDemandeAchat.findByActiveIsTrue();
    }
    @PostMapping("save")
    DemandeAchatDto save(@RequestBody DemandeAchatDto demandeAchatDto){
        return metierDemandeAchat.save(demandeAchatDto);
    }
    @PostMapping("updatearticle")
    private DemandeAchatDto updateArticleToDemandeAchat(@RequestBody DemandeAchatDto demandeAchatDto){
        return metierDemandeAchat.saveArticles(demandeAchatDto);
    }

    @GetMapping("/updatestatut/{statut}/{reference}")
    private boolean updateStatutDa(@PathVariable String statut, @PathVariable String reference){
        return metierDemandeAchat.updateStatut(reference, statut);
    }
}
