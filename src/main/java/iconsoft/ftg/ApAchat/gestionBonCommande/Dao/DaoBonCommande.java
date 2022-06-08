package iconsoft.ftg.ApAchat.gestionBonCommande.Dao;

import iconsoft.ftg.ApAchat.gestionBonCommande.Entities.BonCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaoBonCommande extends JpaRepository<BonCommande,Long> {
    BonCommande findByReferenceAndActiveIsTrue(String reference);
    List<BonCommande> findByActiveIsTrue();
    @Query("select bc from BonCommande bc where bc.demandeachat.reference = ?1")
    BonCommande findByReferenceDA(@Param("reference") String reference);

}
