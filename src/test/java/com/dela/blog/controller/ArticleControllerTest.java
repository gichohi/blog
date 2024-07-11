package com.dela.blog.controller;

import com.dela.blog.exception.ArticleNotFoundException;
import com.dela.blog.model.Article;
import com.dela.blog.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @MockBean
    private ArticleService articleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_article_list() throws Exception {
        Article article = this.buildTestingArticle();
        when(articleService.getArticles()).thenReturn(List.of(article));

        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("ARTICLE")))
                .andExpect(jsonPath("$[0].content", is("CONTENT")));
    }

    @Test
    void should_return_article() throws Exception {
        Article article = this.buildTestingArticle();
        when(articleService.getArticle(1L)).thenReturn(article);

        mockMvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("ARTICLE")))
                .andExpect(jsonPath("$.content", is("CONTENT")));

    }

    @Test
    void should_add_new_article() throws Exception {
        Article article = this.buildTestingArticle();
        when(articleService.addArticle(any(Article.class))).thenReturn(article);

        mockMvc.perform(post("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\": \"TITLE\", \"content\": \"CONTENT\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("ARTICLE")))
                .andExpect(jsonPath("$.content", is("CONTENT")));
    }

    @Test
    void should_update_existing_article() throws Exception {
        Article article = this.buildTestingArticle();
        article.setTitle("Edited Title");
        when(articleService.updateArticle(1L, article)).thenReturn(article);

        mockMvc.perform(put("/articles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"TITLE\", \"content\": \"CONTENT\" }"))
                .andExpect(status().isOk());
    }

    @Test
    void should_delete_existing_article() throws Exception {
        mockMvc.perform(delete("/articles/1"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_exceptio_if_id_does_not_exist() throws Exception {

        when(articleService.getArticle(12L)).thenThrow(new ArticleNotFoundException("Article Not Found"));

        mockMvc.perform(get("/articles/12"))
                .andExpect(status().isNotFound());

    }

    @Test
    void should_return_error_for_id_not_numeric() throws Exception {

        mockMvc.perform(get("/articles/A"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void should_return_bad_request_for_invalid_request_body() throws Exception {
        mockMvc.perform(post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"titel\": \"TITLE\", \"context\": \"CONTEXT\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_bad_gateway_for_wrong_url() throws Exception {
        mockMvc.perform(post("/articlis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"TITLE\", \"context\": \"CONTEXT\" }"))
                .andExpect(status().isNotFound());
    }


    private Article buildTestingArticle() {
        Article article = new Article();
        article.setId(1);
        article.setTitle("ARTICLE");
        article.setContent("CONTENT");
        return article;
    }

}
