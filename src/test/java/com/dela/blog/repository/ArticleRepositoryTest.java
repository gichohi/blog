package com.dela.blog.repository;

import com.dela.blog.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void findAll(){
        List<Article> articles = this.articleRepository.findAll();
        assertEquals(3, articles.size());
    }

    @Test
    void findById(){
        Optional<Article> article = this.articleRepository.findById(2L);
        assertTrue(article.isPresent());
    }

    @Test
    void insertArticle(){
        Article article = new Article();
        article.setTitle("Effective Kotlin");
        article.setContent("People often miss the difference between Iterable and Sequence. " +
                "It is understandable since even their definitions are nearly identical.");

        Article savedArticle = this.articleRepository.save(article);
        assertNotNull(savedArticle);
        assertEquals(4, article.getId());
    }

    @Test
    void updateArticle(){
        String newTitle = "Kotlin as a modern language";
        Article article = new Article();
        article.setId(2);
        article.setTitle(newTitle);

        Article savedArticle = this.articleRepository.save(article);
        assertNotNull(savedArticle);
        assertEquals(newTitle, savedArticle.getTitle());
    }

    @Test
    void deleteArticle(){
        this.articleRepository.deleteById(2L);
        Optional<Article> article = this.articleRepository.findById(2L);
        assertFalse(article.isPresent());
    }

}
