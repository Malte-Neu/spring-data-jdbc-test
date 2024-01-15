package test.malte.demo;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("article_prices")
public class ArticlePriceModel {
    @Id
    @Column("id")
    private Integer id;

    @Column("type_id")
    private String priceType;

    @Column("price")
    private BigDecimal price;

    @Column("article_id")
    AggregateReference<ArticleModel, Integer> articleReference;

    public Integer getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

}
