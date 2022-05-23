package iconsoft.ftg.ApAchat.gestionDesArticles.viewRestfull;

import iconsoft.ftg.ApAchat.gestionDesArticles.Dto.ArticleDto;
import iconsoft.ftg.ApAchat.gestionDesArticles.Metier.MetierArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleRestfull {

    @Autowired
    MetierArticle metierArticle;

    @GetMapping("all")
    List<ArticleDto> findByActiveIsTrue(){
        return metierArticle.findByActiveIsTrue();
    }
    @GetMapping("bydenomination/{denomination}")
    ArticleDto findByDenominationAndActiveIsTrue(@PathVariable String denomination){
        return metierArticle.findByDenominationAndActiveIsTrue(denomination);
    }
    @PostMapping("save")
    ArticleDto saveArticle(@RequestBody ArticleDto articleDto){
        return metierArticle.saveArticle(articleDto);
    }
}
