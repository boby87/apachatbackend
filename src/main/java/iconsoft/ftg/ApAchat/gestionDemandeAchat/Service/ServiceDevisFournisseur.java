package iconsoft.ftg.ApAchat.gestionDemandeAchat.Service;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao.DaoDamandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao.DaoDevisFournisseur;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DevisFournisseurDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DaStatut;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DemandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DevisFournisseur;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DevisStatut;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier.MetierDevisfournisseur;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Dao.DaoFournisseur;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Entities.Fournisseurs;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Metier.MetierFournisseur;
import iconsoft.ftg.ApAchat.gestionUtilisateur.RandomReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Transactional
@Service
public class ServiceDevisFournisseur implements MetierDevisfournisseur {
    @Autowired
    MetierFournisseur metierFournisseur;
    @Autowired
    DaoDevisFournisseur daoDevisFournisseur;
    @Autowired
    DaoDamandeAchat daoDamandeAchat;

    @Override
    public DevisFournisseurDto findByReferencedocAndActiveIsTrue(String referencefournisseur) {
        DevisFournisseur df = daoDevisFournisseur.findByReferencedocAndActiveIsTrue(referencefournisseur);
        if(df==null){
            return null;
        } else {
            return convertDevisFournisseurToDeviFournisseurDto(df);
        }    }

    @Override
    public DevisFournisseurDto findByReferenceAndActiveIsTrue(String reference) {
        DevisFournisseur df = daoDevisFournisseur.findByReferenceAndActiveIsTrue(reference);
        if(df==null){
            return null;
        } else {
            return convertDevisFournisseurToDeviFournisseurDto(df);
        }
    }

    @Override
    public List<DevisFournisseurDto> findByActiveIsTrue() {
        List<DevisFournisseurDto> dtos = new ArrayList<>();
        daoDevisFournisseur.findByActiveIsTrue().forEach(devisFournisseur -> {
            dtos.add(convertDevisFournisseurToDeviFournisseurDto(devisFournisseur));
        });
        return dtos;
    }

    @Override
    public List<DevisFournisseurDto> findByDemandeAchatAndActiveIsTrue(String reference) {
        List<DevisFournisseurDto> dtos = new ArrayList<>();
        daoDevisFournisseur.findAllByReferenceDemandeAchatAndIsActive(reference).forEach(devisFournisseur -> {
            dtos.add(convertDevisFournisseurToDeviFournisseurDto(devisFournisseur));
        });
        return dtos;
    }

    @Override
    public List<DevisFournisseurDto> uploadDevis(List<DevisFournisseurDto> devisFournisseurDto, String referencefournisseur, String referencedemandeachat) {
        DemandeAchat da = daoDamandeAchat.findByReferenceAndActiveIsTrue(referencedemandeachat);
        if(da==null) return null;
        Fournisseurs fo = metierFournisseur.localfindByReferenceAndActiveIsTrue(referencefournisseur);
        if(fo==null) return null;

        List<DevisFournisseurDto> devisFournisseurList = new ArrayList<>();

        for (DevisFournisseurDto dto : devisFournisseurDto) {
            DevisFournisseur devisFournisseur = new DevisFournisseur();
            devisFournisseur.setDate(new Date());
            devisFournisseur.setImagedevis(dto.getImagedevis());
            devisFournisseur.setReference(RandomReference.randomString(10));
            devisFournisseur.setDemandeachat(da);
            devisFournisseur.setFournisseurs(fo);
            devisFournisseur.setStatut(DevisStatut.VALIDE.name());

            devisFournisseur = daoDevisFournisseur.save(devisFournisseur);
            fo.getDevisfournisseurs().add(devisFournisseur);
            da.getDevisfournisseurs().add(devisFournisseur);

            devisFournisseurList.add(
                    convertDevisFournisseurToDeviFournisseurDto(devisFournisseur));
        }

        da.setStatut(DaStatut.EN_COURS_DE_VALIDATION.name());

        return devisFournisseurList;
    }

    public DevisFournisseurDto convertDevisFournisseurToDeviFournisseurDto(DevisFournisseur devisFournisseur){
        DevisFournisseurDto dto = new DevisFournisseurDto();
        BeanUtils.copyProperties(devisFournisseur, dto);
        dto.setReferencefornisseur(devisFournisseur.getFournisseurs().getReference());
        return dto;
    }
}
