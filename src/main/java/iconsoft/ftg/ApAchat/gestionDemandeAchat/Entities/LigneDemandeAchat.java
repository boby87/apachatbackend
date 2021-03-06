package iconsoft.ftg.ApAchat.gestionDemandeAchat.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iconsoft.ftg.ApAchat.gestionDesArticles.Entities.Article;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LigneDemandeAchat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double pu;
    private double pt;
    private int quantite;
    private Date date;
    @Column(nullable = false,unique = true)
    private String reference;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "iddemandeachat")
    private DemandeAchat demandeachat;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idarticle")
    private Article article;

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getPt() {
        return pt;
    }

    public void setPt(double pt) {
        this.pt = pt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public DemandeAchat getDemandeachat() {
        return demandeachat;
    }

    public void setDemandeachat(DemandeAchat demandeachat) {
        this.demandeachat = demandeachat;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
