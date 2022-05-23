package iconsoft.ftg.ApAchat.gestionFournisseurs.Dao;

import iconsoft.ftg.ApAchat.gestionFournisseurs.Dto.FournisseursDto;
import iconsoft.ftg.ApAchat.gestionFournisseurs.Entities.Fournisseurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoFournisseur extends JpaRepository<Fournisseurs,Long> {
    Fournisseurs findByReferenceAndActiveIsTrue(String reference);
    List<Fournisseurs> findByActiveIsTrue();
}
