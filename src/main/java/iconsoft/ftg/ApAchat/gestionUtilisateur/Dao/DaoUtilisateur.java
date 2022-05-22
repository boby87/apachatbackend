package iconsoft.ftg.ApAchat.gestionUtilisateur.Dao;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DaoUtilisateur extends JpaRepository<Utilisateur,Long> {
    @Query("select u from Utilisateur u where u.active=true and (u.matricule=?1 or u.login=?1 or u.email=?1 or u.telephone=?1)")
    Utilisateur findByMatriculeOrLoginAndActiveIsTrue(String username);
}
