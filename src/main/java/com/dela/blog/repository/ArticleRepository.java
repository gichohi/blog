package com.dela.blog.repository;

import com.dela.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    void findByTitle(String title);
}
