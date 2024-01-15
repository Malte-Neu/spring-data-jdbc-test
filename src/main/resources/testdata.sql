DROP TABLE if exists article_prices;
DROP TABLE if exists articles;

CREATE TABLE articles(
    id integer NOT NULL, 
    name varchar(100) NOT NULL, 
    size varchar(100),
    created timestamp default current_timestamp,
    PRIMARY KEY(id)
    );   
CREATE TABLE article_prices(
    id integer NOT NULL,
    article_id integer,
    type_id varchar(10) NOT NULL,
    price dec(10,2),
    PRIMARY KEY(id),
    FOREIGN KEY(article_id) REFERENCES articles(id) ON UPDATE CASCADE ON DELETE CASCADE,
    UNIQUE(article_id, type_id)
    );

INSERT INTO articles(id, name, size) VALUES
    (1, 'T-Shirt', 'small'), 
    (2, 'T-Shirt', 'medium'), 
    (3, 'Jacket', 'extra small'),
    (4, 'Jacket', 'extra large');
    
INSERT INTO article_prices(id, article_id, type_id, price) VALUES
    (1, 1, 'wholesale', '8.93'),
    (2, 1, 'retail', '39.90'),
    (3, 2, 'wholesale', '7.93'),
    (4, 2, 'retail', '39.90'),
    (5, 3, 'wholesale', '34.26'),
    (6, 3, 'retail', '128.00'),
    (7, 4, 'wholesale', '35.26'),
    (8, 4, 'retail', '129.00');
