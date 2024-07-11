package com.dela.blog.service;

import com.dela.blog.exception.ArticleNotFoundException;
import com.dela.blog.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getArticles();
    Article getArticle(Long id) throws ArticleNotFoundException;
    Article addArticle(Article article);
    Article updateArticle(long id, Article article);
    void deleteArticle(long id);
}
