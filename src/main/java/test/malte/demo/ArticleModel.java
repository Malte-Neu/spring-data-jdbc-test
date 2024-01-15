package test.malte.demo;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("articles")
public class ArticleModel {

    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("size")
    private String size;

    @MappedCollection(idColumn = "article_id", keyColumn = "id")
    private Set<ArticlePriceModel> prices;

    public ArticleModel() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<ArticlePriceModel> getPrices() {
        return prices;
    }

    public String getSize() {
        return size;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrices(Set<ArticlePriceModel> prices) {
        this.prices = prices;
    }

    public void setSize(String size) {
        this.size = size;
    }



}
