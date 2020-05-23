package com.natived.soapsservice.mysoapservice.services;

import com.natived.soapsservice.mysoapservice.domain.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    Article getArticleById(long articleId);

    boolean addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(Article article);
}
