package iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.LigneDemandeAchat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoLigneCommandeAchat extends JpaRepository<LigneDemandeAchat,Long> {
}
