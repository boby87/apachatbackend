package iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DevisFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoDevisFournisseur extends JpaRepository<DevisFournisseur,Long> {
    DevisFournisseur findByReferencedocAndActiveIsTrue(String referencefournisseur);
    DevisFournisseur findByReferenceAndActiveIsTrue(String reference);
    List<DevisFournisseur> findByActiveIsTrue();

}
