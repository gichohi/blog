package com.dela.blog.service;

import com.dela.blog.model.Article;
import com.dela.blog.repository.ArticleRepository;
import com.dela.blog.exception.ArticleNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id) throws ArticleNotFoundException {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("Article Not Found"));
    }

    @Override
    public Article addArticle(@Valid Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(long id, Article article) {
        Article savedArticle = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("Article Not Found"));
        savedArticle.setTitle(article.getTitle());
        savedArticle.setContent(article.getContent());
        return articleRepository.save(savedArticle);
    }

    @Override
    public void deleteArticle(long id) {
        articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("Article Not Found"));
        articleRepository.deleteById(id);
    }
}
