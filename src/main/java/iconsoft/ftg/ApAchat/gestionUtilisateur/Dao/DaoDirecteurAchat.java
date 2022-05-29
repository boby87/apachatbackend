package iconsoft.ftg.ApAchat.gestionUtilisateur.Dao;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.DirecteurAchat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoDirecteurAchat extends JpaRepository<DirecteurAchat,Long> {
    DirecteurAchat findByReferenceAndActiveIsTrue(String reference);
}
