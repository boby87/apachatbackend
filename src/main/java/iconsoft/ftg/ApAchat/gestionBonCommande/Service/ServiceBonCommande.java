package iconsoft.ftg.ApAchat.gestionBonCommande.Service;

import iconsoft.ftg.ApAchat.gestionBonCommande.Dao.DaoBonCommande;
import iconsoft.ftg.ApAchat.gestionBonCommande.Dto.BonCommandeDto;
import iconsoft.ftg.ApAchat.gestionBonCommande.Entities.BonCmdStatut;
import iconsoft.ftg.ApAchat.gestionBonCommande.Entities.BonCommande;
import iconsoft.ftg.ApAchat.gestionBonCommande.Metier.MetierBonCommande;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao.DaoLigneCommandeAchat;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto.LigneBonCommandeDto;
import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.LigneDemandeAchat;
import iconsoft.ftg.ApAchat.gestionUtilisateur.RandomReference;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServiceBonCommande implements MetierBonCommande {
    @Autowired
    DaoBonCommande daoBonCommande;
    final DaoLigneCommandeAchat daoLigneCommandeAchat;

    public ServiceBonCommande(@Lazy DaoLigneCommandeAchat daoLigneCommandeAchat) {
        this.daoLigneCommandeAchat = daoLigneCommandeAchat;
    }

    @Override
    public BonCommandeDto findByReferenceAndActiveIsTrue(String reference) {
        return null;
    }

    @Override
    public List<BonCommandeDto> findByActiveIsTrue() {
        List<BonCommande> bonCommandes = new ArrayList<BonCommande>();
        bonCommandes = daoBonCommande.findByActiveIsTrue();
        return convertListBonCommandeToListBonCommandeDto(bonCommandes);
    }

    @Override
    public BonCommandeDto save(BonCommandeDto bonCommandeDto) {
        return null;
    }

    @Override
    public BonCommande saveLocal(BonCommande bonCommande) {
        BonCommande bo = new BonCommande();
        bo.setDate(new Date());
        bo.setStatut(BonCmdStatut.INITIE.name());
        bo.setActive(Boolean.TRUE);
        bo.setDemandeachat(bonCommande.getDemandeachat());
        bo.setMontant(bonCommande.getDemandeachat().getPrixestimatif());
        bo.setReference(RandomReference.randomString(10));
        return daoBonCommande.save(bo);
    }

    @Override
    public BonCommande updateArticle(BonCommandeDto bcDto) {
        BonCommande bc = daoBonCommande.findByReferenceAndActiveIsTrue(bcDto.getReference());
        if(bc==null) return null;
        if(bcDto.getLignebc()!=null && !bcDto.getLignebc().isEmpty() ) {
            final double[] som = {0};
            for (LigneBonCommandeDto lbc : bcDto.getLignebc()) {
                if(bc.getDemandeachat().getReference().equalsIgnoreCase(lbc.getReferencedemandeachat())){
                    bc.getDemandeachat().getLignedemandeachats().forEach(l->{
                        if(l.getArticle().getDenomination().equalsIgnoreCase(lbc.getArticle().getDenomination())){
                            l.setPu(lbc.getPu());
                            l.setQuantite(lbc.getQuantite());
                            l.setPt(lbc.getPt());
                            l = daoLigneCommandeAchat.save(l);
                            som[0] +=l.getPt();
                        }
                    });
                }
            }
            bc.setMontant(som[0]);
            bc.setStatut(BonCmdStatut.ENCOURS_VALIDATION.name());
            return bc;
        }
        return null;
    }

    @Override
    public String updateStatut(BonCommandeDto bcDto) {
        BonCommande bc = daoBonCommande.findByReferenceAndActiveIsTrue(bcDto.getReference());
        if(bc==null) return null;

        if(bcDto.getStatut().equalsIgnoreCase(BonCmdStatut.INITIE.name())){
            return bc.getStatut();
        } else if(bcDto.getStatut().equalsIgnoreCase(BonCmdStatut.ENCOURS_VALIDATION.name())){
            if(bc.getStatut().equalsIgnoreCase(BonCmdStatut.INITIE.name())){
                bc.setStatut(BonCmdStatut.ENCOURS_VALIDATION.name());
            }
            return bc.getStatut();
        } else if(bcDto.getStatut().equalsIgnoreCase(BonCmdStatut.VALIDE.name())){
            if(bc.getStatut().equalsIgnoreCase(BonCmdStatut.ENCOURS_VALIDATION.name()) ||
                    bc.getStatut().equalsIgnoreCase(BonCmdStatut.REJETE_CORRECTION.name())){
                bc.setStatut(BonCmdStatut.VALIDE.name());
            }
            return bc.getStatut();
        } else if(bcDto.getStatut().equalsIgnoreCase(BonCmdStatut.REJETE_CORRECTION.name())){
            if(bc.getStatut().equalsIgnoreCase(BonCmdStatut.ENCOURS_VALIDATION.name())){
                bc.setStatut(BonCmdStatut.REJETE_CORRECTION.name());
            }
            return bc.getStatut();
        } else if(bcDto.getStatut().equalsIgnoreCase(BonCmdStatut.RECEPTIONNE.name())){
            if(bc.getStatut().equalsIgnoreCase(BonCmdStatut.VALIDE.name())){
                bc.setStatut(BonCmdStatut.RECEPTIONNE.name());
            }
            return bc.getStatut();
        } else if(bcDto.getStatut().equalsIgnoreCase(BonCmdStatut.A_PAYE.name())){
            if(bc.getStatut().equalsIgnoreCase(BonCmdStatut.RECEPTIONNE.name())){
                bc.setStatut(BonCmdStatut.A_PAYE.name());
            }
            return bc.getStatut();
        } else if(bcDto.getStatut().equalsIgnoreCase(BonCmdStatut.PAYE.name())){
            if(bc.getStatut().equalsIgnoreCase(BonCmdStatut.A_PAYE.name())){
                bc.setStatut(BonCmdStatut.PAYE.name());
            }
            return bc.getStatut();
        } else return null;
    }

    @Override
    public BonCommande getByReferenceDA(String reference) {
        return daoBonCommande.findByReferenceDA(reference);
    }

    @Override
    public List<BonCommandeDto> convertListBonCommandeToListBonCommandeDto(List<BonCommande> bonCommandes) {
        List<BonCommandeDto> bonCommandeDtos = new ArrayList<BonCommandeDto>();
        bonCommandes.forEach(b -> {
            BonCommandeDto bcDto = new BonCommandeDto();
            List<LigneBonCommandeDto> lignebcDtos = new ArrayList<LigneBonCommandeDto>();
            BeanUtils.copyProperties(b,bcDto);
            b.getDemandeachat().getLignedemandeachats().forEach(ligeDa -> {
                LigneBonCommandeDto lbcDto = new LigneBonCommandeDto();
                BeanUtils.copyProperties(ligeDa,lbcDto);
                lignebcDtos.add(lbcDto);
            });
            bcDto.setLignebc(lignebcDtos);
            bonCommandeDtos.add(bcDto);
        });
        return bonCommandeDtos;
    }
}
