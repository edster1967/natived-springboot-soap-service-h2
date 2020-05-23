package com.natived.soapsservice.mysoapservice.services.impl;

import com.natived.soapsservice.mysoapservice.domain.Article;
import com.natived.soapsservice.mysoapservice.repositories.ArticleRepository;
import com.natived.soapsservice.mysoapservice.services.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article getArticleById(long articleId) {
        log.debug("Starting getArticleById with value -" + articleId);
        Article obj = articleRepository.findByArticleId(articleId);
        return obj;
    }

    @Override
    public List<Article> getAllArticles() {
        log.debug("Starting getArticleById with no value");
        List<Article> list = new ArrayList<>();
        articleRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public synchronized boolean addArticle(Article article) {
        log.debug("Starting addArticle with value -" + article.getArticleId());
        List<Article> list = articleRepository.findByTitleAndCategory(article.getTitle(), article.getCategory());
        if (list.size() > 0) {
            return false;
        } else {
            article = articleRepository.save(article);
            return true;
        }
    }

    @Override
    public void updateArticle(Article article) {
        log.debug("Starting updateArticle with value -" + article.getArticleId());
        articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        log.debug("Starting updateArticle with value -" + article.getArticleId());
        articleRepository.delete(article);
    }
}
