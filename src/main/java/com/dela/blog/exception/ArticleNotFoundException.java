package com.dela.blog.exception;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(String message){
        super(message);
    }

}
