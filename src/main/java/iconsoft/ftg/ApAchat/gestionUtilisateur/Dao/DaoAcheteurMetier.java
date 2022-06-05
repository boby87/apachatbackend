package iconsoft.ftg.ApAchat.gestionUtilisateur.Dao;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.AcheteurMetier;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DaoAcheteurMetier extends JpaRepository<AcheteurMetier, Long> {
    AcheteurMetier findByReferenceAndActiveIsTrue(String reference);
}
