package iconsoft.ftg.ApAchat.gestionDemandeAchat.viewRestfull;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DevisFournisseurDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier.MetierDevisfournisseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("devis")
public class DevisfournisserRestfull {

    @Autowired
    MetierDevisfournisseur metierDevisfournisseur;


    @GetMapping("by/referencedoc/{referencedoc}")
    DevisFournisseurDto findByReferencedocAndActiveIsTrue(@PathVariable String referencedoc) {
        return metierDevisfournisseur.findByReferencedocAndActiveIsTrue(referencedoc);
    }
    @GetMapping("by/reference/{reference}")
    DevisFournisseurDto findByReferenceAndActiveIsTrue(@PathVariable String reference) {
        return metierDevisfournisseur.findByReferenceAndActiveIsTrue(reference);
    }
    @GetMapping("all")
    List<DevisFournisseurDto> findByActiveIsTrue() {
        return metierDevisfournisseur.findByActiveIsTrue();
    }
    @GetMapping("demandeachat/{reference}")
    List<DevisFournisseurDto> findAllByDemandeAchatAndActiveIsTrue(@PathVariable String reference) {
        return metierDevisfournisseur.findByDemandeAchatAndActiveIsTrue(reference);
    }
    @PostMapping("save/{referencedemandeachat}")
    List<DevisFournisseurDto> uploadDevis(@RequestBody List<DevisFournisseurDto> devisFournisseurDto,
                                    @PathVariable String referencedemandeachat) {
        return metierDevisfournisseur.uploadDevis(devisFournisseurDto, referencedemandeachat);
    }

    @PostMapping("choosedevis/{referencedemandeachat}")
    DevisFournisseurDto chooseDevis(@RequestBody DevisFournisseurDto dto, @PathVariable String referencedemandeachat){
        return metierDevisfournisseur.chooseDevis(dto,referencedemandeachat);
    }
}
