package iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.LigneDemandeAchat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoLigneCommandeAchat extends JpaRepository<LigneDemandeAchat,Long> {
    LigneDemandeAchat findByReference(String reference);
}
