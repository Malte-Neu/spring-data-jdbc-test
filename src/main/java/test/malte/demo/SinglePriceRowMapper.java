package test.malte.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

public class SinglePriceRowMapper implements RowMapper<ArticleModel> {

    @Override
    public ArticleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        ArticleModel result = new ArticleModel();

        result.setId(rs.getInt("id"));
        result.setName(rs.getString("name"));
        result.setSize(rs.getString("size"));

        ArticlePriceModel price = new ArticlePriceModel();
        price.setId(rs.getInt(findIndex(rs, "article_prices", "id")));
        price.setPrice(rs.getBigDecimal("price"));
        price.setPriceType(rs.getString("type_id"));

        result.setPrices(Set.of(price));

        return result;
    }

    private int findIndex(ResultSet rs, String tableName, String colName) throws SQLException {
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            String tb = rs.getMetaData().getTableName(i);
            String col = rs.getMetaData().getColumnName(i);
            if (tb.equalsIgnoreCase(tableName) && col.equalsIgnoreCase(colName))
                return i;
        }
        throw new SQLException("Column not found");
    }



}
