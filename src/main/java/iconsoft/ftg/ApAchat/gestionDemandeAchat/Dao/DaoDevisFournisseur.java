package iconsoft.ftg.ApAchat.gestionDemandeAchat.Dao;

import iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities.DevisFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaoDevisFournisseur extends JpaRepository<DevisFournisseur,Long> {
    DevisFournisseur findByReferencedocAndActiveIsTrue(String referencefournisseur);
    DevisFournisseur findByReferenceAndActiveIsTrue(String reference);
    List<DevisFournisseur> findByActiveIsTrue();
    @Query("select d from DevisFournisseur d where d.demandeachat.reference = ?1 and d.active = true")
    List<DevisFournisseur> findAllByReferenceDemandeAchatAndIsActive(@Param("x") String reference);

}
