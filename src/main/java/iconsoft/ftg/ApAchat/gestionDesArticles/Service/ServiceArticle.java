package iconsoft.ftg.ApAchat.gestionDesArticles.Service;

import iconsoft.ftg.ApAchat.gestionDesArticles.Dao.DaoArticles;
import iconsoft.ftg.ApAchat.gestionDesArticles.Dto.ArticleDto;
import iconsoft.ftg.ApAchat.gestionDesArticles.Entities.Article;
import iconsoft.ftg.ApAchat.gestionDesArticles.Metier.MetierArticle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServiceArticle implements MetierArticle {
    @Autowired
    DaoArticles daoArticles;

    @Override
    public List<ArticleDto> findByActiveIsTrue() {
        List<ArticleDto> articleDtos=new ArrayList<>();
        daoArticles.findByActiveIsTrue().forEach(a->{
            ArticleDto articleDto=new ArticleDto();
            BeanUtils.copyProperties(a,articleDto);
            articleDtos.add(articleDto);
        });
        return articleDtos;
    }

    @Override
    public ArticleDto findByDenominationAndActiveIsTrue(String denomination) {
        Article article= daoArticles.findByDenominationAndActiveIsTrue(denomination);
        ArticleDto articleDto=new ArticleDto();
        BeanUtils.copyProperties(article,articleDto);
        return articleDto;
    }

    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) {
        Article article=new Article();
        articleDto.setActive(true);
        BeanUtils.copyProperties(articleDto,article);
        article=daoArticles.save(article);
        this.generateCodeArticle(article);
        BeanUtils.copyProperties(article,articleDto);
        return articleDto;
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        if (articleDto.getDenomination().equals("")) {
            throw new RuntimeException("Le nom de l'article ne peut pas être vide");
        } else if (articleDto.getMarque().equals("")) {
            throw new RuntimeException("La marque de l'article ne peut pas être vide");
        }
        Article article=daoArticles.findByCodeArticleAndActiveIsTrue(articleDto.getCodeArticle());
        BeanUtils.copyProperties(articleDto,article);
        article=daoArticles.save(article);
        BeanUtils.copyProperties(article,articleDto);
        return articleDto;
    }

    @Override
    public void generateCodeArticle(Article article) {
        SimpleDateFormat formater = new SimpleDateFormat("yy");
        Date today = new Date();
        String digits = "";

        if (article.getId() <= 9) {
            digits = String.format("%04d", article.getId());
        } else if(article.getId() <= 99) {
            digits = String.format("%03d", article.getId());
        } else if(article.getId() <= 999) {
            digits = String.format("%02d", article.getId());
        } else {
            digits = String.format("%d", article.getId());
        }

        String codeArticle = "AR" + formater.format(today) + digits;
        article.setCodeArticle(codeArticle);
    }
    
}
