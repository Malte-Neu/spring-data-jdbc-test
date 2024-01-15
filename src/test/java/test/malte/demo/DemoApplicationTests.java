package test.malte.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    ArticleRepository repository;

	@Test
	void findByNameTest() {
	    List<ArticleModel> articles = repository.findByName("T-Shirt");
	    assertNotNull(articles);


        String data = createString(articles);
        System.out.println("\r\rTestoutput\r\r" + data);
	}

	@Test
    void findByNameWithRetailPriceTest() {
        List<ArticleModel> articles = repository.findByNameWithRetailPrice("T-Shirt");
        assertNotNull(articles);


        String data = createString(articles);
        System.out.println("\r\rTestoutput\r\r" + data);
    }

	private String createString(List<ArticleModel> articles) {
        StringBuffer result = new StringBuffer();
        for (ArticleModel article : articles) {
            result.append(formatArticleText(article));
            for (ArticlePriceModel price : article.getPrices()) {
                result.append(formatPrice(price));
            }
        }
        return result.toString();
    }

    private String formatArticleText(ArticleModel article) {
        return String.format("Article id=%d name=\"%s\" size=\"%s\"\r\n", article.getId(), article.getName(),
                article.getSize());
    }

    private String formatPrice(ArticlePriceModel price) {
        return String.format("\t%s %s\r\n", price.getPrice().toPlainString(), price.getPriceType());
    }


}
