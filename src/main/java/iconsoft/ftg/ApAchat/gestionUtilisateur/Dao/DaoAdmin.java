package iconsoft.ftg.ApAchat.gestionUtilisateur.Dao;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.AdminUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoAdmin extends JpaRepository<AdminUtilisateur,Long> {
}
