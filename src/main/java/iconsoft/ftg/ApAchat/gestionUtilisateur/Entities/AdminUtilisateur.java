package iconsoft.ftg.ApAchat.gestionUtilisateur.Entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AdminUtilisateur extends Utilisateur{
}
