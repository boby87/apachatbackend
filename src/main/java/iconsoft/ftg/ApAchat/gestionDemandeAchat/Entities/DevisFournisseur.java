package iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DevisFournisseur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statut;
    private Date date;
    private boolean active=true;
    @Column(columnDefinition = "text")
    private String imagedevis;
    @Column(nullable = false,unique = true)
    private String reference;
    private String referencefornisseur;
    @ManyToOne
    @JoinColumn(name = "iddemandeachat",unique = true)
    private DemandeAchat demandeachat;
}
