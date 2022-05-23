package iconsoft.ftg.ApAchat.gestionDesArticles.Service;

import iconsoft.ftg.ApAchat.gestionDesArticles.Dao.DaoArticles;
import iconsoft.ftg.ApAchat.gestionDesArticles.Dto.ArticleDto;
import iconsoft.ftg.ApAchat.gestionDesArticles.Entities.Article;
import iconsoft.ftg.ApAchat.gestionDesArticles.Metier.MetierArticle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        BeanUtils.copyProperties(articleDto,article);
        article=daoArticles.save(article);
        BeanUtils.copyProperties(article,articleDto);
        return articleDto;
    }
}
