package com.dela.blog.controller;

import com.dela.blog.exception.ArticleNotFoundException;
import com.dela.blog.model.Article;
import com.dela.blog.service.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getArticles() {
        List<Article> articles = articleService.getArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") @Positive Long id) throws ArticleNotFoundException {
        return new ResponseEntity<>(articleService.getArticle(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Article> addArticle(@Valid @RequestBody Article article) {
        Article savedArticle = articleService.addArticle(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") @Positive Long id, @RequestBody Article article) throws ArticleNotFoundException {
        Article updateArticle = articleService.updateArticle(id, article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") @Positive Long id) throws ArticleNotFoundException {
        articleService.deleteArticle(id);
        return new ResponseEntity<>("Article " + id + " deleted", HttpStatus.OK);
    }

}
