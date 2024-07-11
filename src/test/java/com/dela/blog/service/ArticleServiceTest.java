package com.dela.blog.service;

import com.dela.blog.model.Article;
import com.dela.blog.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    void getArticles_should_return_article_list() {
        Article article = this.buildTestingArticle();
        when(articleRepository.findAll()).thenReturn(List.of(article));
        List<Article> articles = this.articleService.getArticles();

        assertEquals(1, articles.size());
        verify(this.articleRepository).findAll();
    }

    @Test
    void getArticle_should_return_article(){
        Article article = this.buildTestingArticle();
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));
        Article returnedArticle = this.articleService.getArticle(1L);

        assertEquals(article.getId(), returnedArticle.getId());
        verify(this.articleRepository).findById(1L);
    }

    @Test
    void addArticle_should_insert_new_article(){
        Article article = this.buildTestingArticle();

       this.articleService.addArticle(article);

       verify(this.articleRepository).save(article);
    }

    @Test
    void deleteById_should_delete_article(){
        this.articleService.deleteArticle(1L);

        verify(this.articleRepository).deleteById(1L);
    }

    private Article buildTestingArticle() {
        Article article = new Article();
        article.setId(1);
        article.setTitle("ARTICLE");
        article.setContent("CONTENT");
        return article;
    }

}
