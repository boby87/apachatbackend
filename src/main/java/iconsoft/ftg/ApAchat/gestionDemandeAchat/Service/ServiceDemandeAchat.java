package iconsoft.ftg.ApAchat.gestionDemandeAchat.Service;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao.DaoDamandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao.DaoLigneCommandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.DemandeAchatDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.LigneDemandeAchatDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DaStatut;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DemandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.LigneDemandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Metier.MetierDemandeAchat;
import iconsoft.ftg.ApAchat.gestionUtilisateur.ConstanteRoles;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.AcheteurMetier;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.DirecteurAchat;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.Utilisateur;
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

        demandeAchat.setDirecteurachat((DirecteurAchat) metierAccount.findByFonction(ConstanteRoles.DIRECTEUR_ACHAT));
        demandeAchat = daoDamandeAchat.save(demandeAchat);
        return convertDemandeAchatToDemandeAchatDto(demandeAchat);
    }

    @Override
    public DemandeAchatDto saveArticles(DemandeAchatDto demandeAchatDto) {
        DemandeAchat demandeAchat = daoDamandeAchat.findByReferenceAndActiveIsTrue(demandeAchatDto.getReference());
        if(demandeAchat==null) return null;
        if(demandeAchatDto.getLignedemandeAchats()==null) return null;

        try {
            for (LigneDemandeAchatDto lda : demandeAchatDto.getLignedemandeAchats()) {
                LigneDemandeAchat ligneDemandeAchat = new LigneDemandeAchat();
                ligneDemandeAchat.setDate(new Date());
                ligneDemandeAchat.setDemandeachat(demandeAchat);
                ligneDemandeAchat.setArticle(lda.getArticle());
                ligneDemandeAchat.setReference(RandomReference.randomString(10));
                ligneDemandeAchat = daoLigneCommandeAchat.save(ligneDemandeAchat);

                demandeAchat.getLignedemandeAchats().add(ligneDemandeAchat);
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
