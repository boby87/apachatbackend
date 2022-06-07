package iconsoft.ftg.ApAchat.gestionDemandeAchat.Service;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao.DaoDamandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao.DaoLigneCommandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DemandeAchatDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.LigneDemandeAchatDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DaStatut;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DemandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.LigneDemandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier.MetierDemandeAchat;
import iconsoft.ftg.ApAchat.gestionDesArticles.Dao.DaoArticles;
import iconsoft.ftg.ApAchat.gestionDesArticles.Metier.MetierArticle;
import iconsoft.ftg.ApAchat.gestionUtilisateur.ConstanteRoles;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.AcheteurMetier;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.DirecteurAchat;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Metier.MetierAccount;
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
public class ServiceDemandeAchat implements MetierDemandeAchat {
    @Autowired
    DaoDamandeAchat daoDamandeAchat;
    @Autowired
    MetierAccount metierAccount;
    @Autowired
    DaoLigneCommandeAchat daoLigneCommandeAchat;
    @Autowired
    DaoArticles daoArticles;
    @Autowired
    MetierArticle metierArticle;

    @Override
    public DemandeAchatDto findByReferenceAndActiveIsTrue(String reference) {
        return convertDemandeAchatToDemandeAchatDto(daoDamandeAchat.findByReferenceAndActiveIsTrue(reference));
    }

    @Override
    public List<DemandeAchatDto> findByActiveIsTrue() {
        return convertListDemandeAchatToListDemandeAchatDto(daoDamandeAchat.findAllByActiveIsTrue());
    }

    @Override
    public DemandeAchatDto save(DemandeAchatDto demandeAchatDto) {
        AcheteurMetier am = metierAccount.LocalfindByMatriculeOrLoginAndActiveIsTrue(demandeAchatDto.getMatriculeAcheteurmetier());
        if(am == null) return null;

        DemandeAchat demandeAchat = new DemandeAchat();
        demandeAchat.setDate(new Date());
        demandeAchat.setActive(Boolean.TRUE);
        demandeAchat.setStatut(DaStatut.NOUVELLE.name());
        demandeAchat.setPrixestimatif(demandeAchatDto.getPrixestimatif());
        demandeAchat.setReference(RandomReference.randomString(10));
        demandeAchat.setAcheteurmetier(am);
        demandeAchat.setDirecteurachat((DirecteurAchat) metierAccount.findByFonction(ConstanteRoles.DIRECTEUR_ACHAT));
        demandeAchat = daoDamandeAchat.save(demandeAchat);
        return convertDemandeAchatToDemandeAchatDto(demandeAchat);
    }

    @Override
    public DemandeAchatDto saveDemandeDtoWithLigneDemandeAchatDto(DemandeAchatDto demandeAchatDto) {
        demandeAchatDto.setReference(save(demandeAchatDto).getReference());
        demandeAchatDto = saveArticles(demandeAchatDto);
        return demandeAchatDto;
    }

    @Override
    public boolean updateStatut(String reference, String statut) {
        DemandeAchat da = daoDamandeAchat.findByReferenceAndActiveIsTrue(reference);
        if(da==null) return false;
        if(statut!=null && statut.equalsIgnoreCase("")) return false;

        if(da.getStatut().equalsIgnoreCase(statut)){
            return false;
        } else {
            assert statut != null;
            if(statut.equalsIgnoreCase(DaStatut.NOUVELLE.name())){
                da.setStatut(statut);
                return true;
            } else if(statut.equalsIgnoreCase(DaStatut.EN_COURS_DE_VALIDATION.name())){
                da.setStatut(statut);
                return true;
            } else if(statut.equalsIgnoreCase(DaStatut.REJETE_A_COMPLETER.name())){
                da.setStatut(statut);
                return true;
            } else if(statut.equalsIgnoreCase(DaStatut.REJETE_DEFINITIVEMENT.name())){
                da.setStatut(statut);
                return true;
            } else if(statut.equalsIgnoreCase(DaStatut.CLOTURE.name())){
                da.setStatut(statut);
                return true;
            } else return false;
        }
    }

    @Override
    public DemandeAchatDto saveArticles(DemandeAchatDto demandeAchatDto) {
        DemandeAchat demandeAchat = daoDamandeAchat.findByReferenceAndActiveIsTrue(demandeAchatDto.getReference());
        if(demandeAchat==null) return null;
        if(demandeAchatDto.getLignedemandeachats()==null) return null;

        try {
            for (LigneDemandeAchatDto lda : demandeAchatDto.getLignedemandeachats()) {
                LigneDemandeAchat ligneDemandeAchat = new LigneDemandeAchat();
                ligneDemandeAchat.setDate(new Date());
                ligneDemandeAchat.setDemandeachat(demandeAchat);
                ligneDemandeAchat.setArticle(daoArticles.findByDenominationAndActiveIsTrue(lda.getArticle().getDenomination()));
                ligneDemandeAchat.setReference(RandomReference.randomString(10));
                ligneDemandeAchat = daoLigneCommandeAchat.save(ligneDemandeAchat);

                demandeAchat.getLignedemandeachats().add(ligneDemandeAchat);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return convertDemandeAchatToDemandeAchatDto(demandeAchat);
    }


    public DemandeAchatDto convertDemandeAchatToDemandeAchatDto(DemandeAchat dem){
        DemandeAchatDto dto = new DemandeAchatDto();
        BeanUtils.copyProperties(dem, dto);
        return dto;
    }

    public List<DemandeAchatDto> convertListDemandeAchatToListDemandeAchatDto(List<DemandeAchat> demandeAchats){
        List<DemandeAchatDto> dtos = new ArrayList<>();
        demandeAchats.forEach(d->{
            DemandeAchatDto dto = new DemandeAchatDto();
            BeanUtils.copyProperties(d, dto);
            dtos.add(dto);
        });
        return dtos;
    }
}
