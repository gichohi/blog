DROP TABLE IF EXISTS article;
CREATE TABLE article(
 id INT AUTO_INCREMENT PRIMARY KEY,
 title VARCHAR(200),
 content VARCHAR(1023)
)