package iconsoft.ftg.ApAchat.gestionDemandeAchat.Dto;

import iconsoft.ftg.ApAchat.gestionDesArticles.Entities.Article;

import java.util.Date;

public class LigneDemandeAchatDto {
    private double pu;
    private double pt;
    private Date date;

    private DemandeAchatDto demandeachat;

    private Article article;

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

    public DemandeAchatDto getDemandeachat() {
        return demandeachat;
    }

    public void setDemandeachat(DemandeAchatDto demandeachat) {
        this.demandeachat = demandeachat;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
