package iconsoft.ftg.ApAchat.gestionDesArticles.Metier;

import iconsoft.ftg.ApAchat.gestionDesArticles.Dto.ArticleDto;
import iconsoft.ftg.ApAchat.gestionDesArticles.Entities.Article;

import java.util.List;

public interface MetierArticle {
    List<ArticleDto> findByActiveIsTrue();
    ArticleDto findByDenominationAndActiveIsTrue(String denomination);
    ArticleDto saveArticle(ArticleDto articleDto);


}
