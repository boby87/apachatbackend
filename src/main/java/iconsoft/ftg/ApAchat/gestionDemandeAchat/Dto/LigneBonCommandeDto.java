package iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto;

import iconsoft.ftg.ApAchat.gestionDesArticles.Entities.Article;

import java.util.Date;

public class LigneBonCommandeDto {
    private double pu;
    private int quantite;
    private double pt;
    private Date date;
    private String referencedemandeachat;
    private Article article;

    public double getPu() {
        return pu;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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

    public String getReferencedemandeachat() {
        return referencedemandeachat;
    }

    public void setReferencedemandeachat(String referencedemandeachat) {
        this.referencedemandeachat = referencedemandeachat;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
