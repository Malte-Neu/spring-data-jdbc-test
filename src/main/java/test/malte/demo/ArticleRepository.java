package test.malte.demo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<ArticleModel, Integer> {

    @Query("SELECT * FROM articles WHERE name LIKE :name")
    List<ArticleModel> findByName(String name);

    @Query(value =  """
            SELECT a.*, ap.* FROM articles a
                INNER JOIN article_prices ap ON a.id = ap.article_id
                WHERE type_id LIKE 'retail' AND name LIKE :name
            """
            , rowMapperClass = SinglePriceRowMapper.class)
    List<ArticleModel> findByNameWithRetailPrice(String name);
}
