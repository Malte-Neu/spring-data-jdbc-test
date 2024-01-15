package test.malte.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @GetMapping(path = "/articles/find", produces = "text/plain")
    public String findArticles(@RequestParam String name) {
        //http://localhost:8080/articles/find?name=T-Shirt


        List<ArticleModel> articles = articleRepository.findByName(name);

        StringBuffer result = createString(articles);

        return result.toString();
    }

    @GetMapping(path = "/articles/retailprice/find", produces = "text/plain")
    public String getArticleRetail(@RequestParam String name) {
        //http://localhost:8080/articles/retailprice/find?name=T-Shirt

        List<ArticleModel> articles = articleRepository.findByNameWithRetailPrice(name);

        StringBuffer result = createString(articles);

        return result.toString();
    }

//    @GetMapping(path = "/article/{id}/testupdate", produces = "text/plain")
//    public String updateArticlePrice(@PathVariable Integer id) {
//        //http://localhost:8080/article/2/testupdate
//
//        ArticleModel article = articleRepository.findById(id).get();
//        article.setName("XX");
//
//        articleRepository.save(article);
//
//        StringBuffer result = createString(article);
//
//        return result.toString();
//    }


    private StringBuffer createString(ArticleModel article) {
        StringBuffer result = new StringBuffer(formatArticleText(article));
        for (ArticlePriceModel price : article.getPrices())
            result.append(formatPrice(price));

        return result;
    }

    private StringBuffer createString(List<ArticleModel> articles) {
        StringBuffer result = new StringBuffer();
        for (ArticleModel article : articles) {
            result.append(formatArticleText(article));
            for (ArticlePriceModel price : article.getPrices()) {
                result.append(formatPrice(price));
            }
        }
        return result;
    }

    private String formatArticleText(ArticleModel article) {
        return String.format("Article id=%d name=\"%s\" size=\"%s\"\r\n", article.getId(), article.getName(),
                article.getSize());
    }

    private String formatPrice(ArticlePriceModel price) {
        return String.format("\t%s %s\r\n", price.getPrice().toPlainString(), price.getPriceType());
    }

}
