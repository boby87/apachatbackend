package iconsoft.ftg.ApAchat.gestionDesArticles.Dao;

import iconsoft.ftg.ApAchat.gestionDesArticles.Entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoArticles extends JpaRepository<Article,Long> {
    List<Article> findByActiveIsTrue();
    Article findByCodeArticleAndActiveIsTrue(String codeArticle);
    Article findByDenominationAndActiveIsTrue(String denomination);
}
