package iconsoft.ftg.ApAchat.gestionDemandeAchat.Service;

import iconsoft.ftg.ApAchat.gestionBonCommande.Entities.BonCommande;
import iconsoft.ftg.ApAchat.gestionBonCommande.Metier.MetierBonCommande;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao.DaoDamandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao.DaoDevisFournisseur;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DevisFournisseurDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DaStatut;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DemandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DevisFournisseur;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DevisStatut;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier.MetierDevisfournisseur;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Metier.MetierFournisseur;
import iconsoft.ftg.ApAchat.gestionUtilisateur.RandomReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    final
    MetierBonCommande metierBonCommande;

    public ServiceDevisFournisseur(@Lazy MetierBonCommande metierBonCommande) {
        this.metierBonCommande = metierBonCommande;
    }

    @Override
    public DevisFournisseurDto findByReferencedocAndActiveIsTrue(String referencefournisseur) {
        DevisFournisseur df = daoDevisFournisseur.findByReferencedocAndActiveIsTrue(referencefournisseur);
        if(df==null){
            return null;
        } else {
            return convertDevisFournisseurToDeviFournisseurDto(df);
        }
    }

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
    public List<DevisFournisseurDto> uploadDevis(List<DevisFournisseurDto> devisFournisseurDto,String referencedemandeachat) {
        DemandeAchat da = daoDamandeAchat.findByReferenceAndActiveIsTrue(referencedemandeachat);
        if(da==null) return null;
      /*  Fournisseurs fo = metierFournisseur.localfindByReferenceAndActiveIsTrue(referencefournisseur);
        if(fo==null) return null;*/

        List<DevisFournisseurDto> devisFournisseurList = new ArrayList<>();

        for (DevisFournisseurDto dto : devisFournisseurDto) {
            DevisFournisseur devisFournisseur = new DevisFournisseur();
            devisFournisseur.setDate(new Date());
            devisFournisseur.setImagedevis(dto.getImagedevis());
            devisFournisseur.setReference(RandomReference.randomString(10));
            devisFournisseur = daoDevisFournisseur.save(devisFournisseur);
            devisFournisseur.setDemandeachat(da);
          //  devisFournisseur.setFournisseurs(fo);
            devisFournisseur.setStatut(DevisStatut.VALIDE.name());

           // fo.getDevisfournisseurs().add(devisFournisseur);
            da.getDevisfournisseurs().add(devisFournisseur);

            devisFournisseurList.add(
                    convertDevisFournisseurToDeviFournisseurDto(devisFournisseur));
        }

        da.setStatut(DaStatut.EN_COURS_DE_VALIDATION.name());

        return devisFournisseurList;
    }

    @Override
    public DevisFournisseurDto chooseDevis(DevisFournisseurDto dto, String rDa) {
        final DevisFournisseur[] devisFournisseur = {daoDevisFournisseur.findByReferenceAndActiveIsTrue(dto.getReference())};
        if(devisFournisseur[0] ==null) return null;
        DemandeAchat da = daoDamandeAchat.findByReferenceAndActiveIsTrue(rDa);
        if(da==null) return null;
        if(!devisFournisseur[0].getDemandeachat().equals(da)) return null;

        da.getDevisfournisseurs().forEach(de -> {
            if(de.getReference().equalsIgnoreCase(devisFournisseur[0].getReference())){
                devisFournisseur[0].setStatut(DevisStatut.VALIDE.name());
                devisFournisseur[0] = daoDevisFournisseur.save(devisFournisseur[0]);
            } else {
                de.setStatut(DevisStatut.NON_VALIDE.name());
                daoDevisFournisseur.save(de);
            }
        });
        if(devisFournisseur[0].getStatut().equals(DevisStatut.VALIDE.name())){
            da.setStatut(DaStatut.VALIDE.name());
            BonCommande bo = new BonCommande();
            bo.setDemandeachat(da);
            metierBonCommande.saveLocal(bo);
            return convertDevisFournisseurToDeviFournisseurDto(devisFournisseur[0]);
        } else return null;
    }

    @Override
    public DevisFournisseurDto findDevisValideByReferenceDA(String reference) {
        final DevisFournisseur[] df = new DevisFournisseur[1];
        daoDevisFournisseur.findAllByReferenceDemandeAchatAndIsActive(reference).forEach(devisFournisseur -> {
            if(devisFournisseur.getStatut().equalsIgnoreCase(DevisStatut.VALIDE.name())){
                df[0] = devisFournisseur;
            }
        });
        if(df[0]!=null){
            return convertDevisFournisseurToDeviFournisseurDto(df[0]);
        } else return null;
    }

    public DevisFournisseurDto convertDevisFournisseurToDeviFournisseurDto(DevisFournisseur devisFournisseur){
        DevisFournisseurDto dto = new DevisFournisseurDto();
        BeanUtils.copyProperties(devisFournisseur, dto);
       // dto.setReferencefornisseur(devisFournisseur.getFournisseurs().getReference());
        return dto;
    }
}
