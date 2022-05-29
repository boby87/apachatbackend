package iconsoft.ftg.ApAchat.gestionFournisseurs.Service;

import iconsoft.ftg.ApAchat.gestionFournisseurs.Dao.DaoFournisseur;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Dto.FournisseursDto;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Entities.Fournisseurs;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Metier.MetierFournisseur;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class ServiceFournisseurs implements MetierFournisseur {

    @Autowired
    DaoFournisseur daoFournisseur;


    @Override
    public FournisseursDto findByReferenceAndActiveIsTrue(String reference) {
        Fournisseurs fournisseurs= daoFournisseur.findByReferenceAndActiveIsTrue(reference);
            FournisseursDto fournisseursDto=new FournisseursDto();
            BeanUtils.copyProperties(fournisseurs,fournisseursDto);
        return fournisseursDto;
    }

    @Override
    public List<FournisseursDto> findByActiveIsTrue() {
        List<FournisseursDto> fournisseursDtos=new ArrayList<>();
        daoFournisseur.findByActiveIsTrue().forEach(f->{
            FournisseursDto fournisseursDto=new FournisseursDto();
            BeanUtils.copyProperties(f,fournisseursDto);
            fournisseursDtos.add(fournisseursDto);
        });
        return fournisseursDtos;
    }

    @Override
    public FournisseursDto saveFournisseur(FournisseursDto fournisseursDto) {
        Fournisseurs fournisseurs=new Fournisseurs();
        BeanUtils.copyProperties(fournisseursDto,fournisseurs);
        fournisseurs=daoFournisseur.save(fournisseurs);
        BeanUtils.copyProperties(fournisseurs,fournisseursDto);
        return fournisseursDto;
    }

    @Override
    public Fournisseurs localfindByReferenceAndActiveIsTrue(String referencefournisseur) {
        return daoFournisseur.findByReferenceAndActiveIsTrue(referencefournisseur);
    }
}
