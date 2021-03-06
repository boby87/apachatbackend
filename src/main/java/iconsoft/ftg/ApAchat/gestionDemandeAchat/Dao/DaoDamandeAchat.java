package iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DemandeAchat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoDamandeAchat extends JpaRepository<DemandeAchat,Long> {
    DemandeAchat findByReferenceAndActiveIsTrue(String reference);
    List<DemandeAchat> findAllByActiveIsTrue();

}
