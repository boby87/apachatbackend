package iconsoft.ftg.ApAchat.gestionUtilisateur.Dao;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.RolesUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoRolesUser extends JpaRepository<RolesUser,Long> {
    RolesUser findByRolesnameAndActiveIsTrue(String rolename);
}
