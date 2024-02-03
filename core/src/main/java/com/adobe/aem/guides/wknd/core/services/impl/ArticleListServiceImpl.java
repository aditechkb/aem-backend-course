package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.beans.ArticleListDataBean;
import com.adobe.aem.guides.wknd.core.services.ArticleListService;
import com.adobe.aem.guides.wknd.core.services.RecipeService;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ArticleListService.java
@Component(service = ArticleListService.class)
public class ArticleListServiceImpl implements ArticleListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleListServiceImpl.class);

    @Reference
    private QueryBuilder builder;

    @Override
    public List<ArticleListDataBean> getArticleListDataBeans(Resource resource, String articleRootPath) {
        List<ArticleListDataBean> articleListDataBeans = new ArrayList<>();
        ResourceResolver resourceResolver = resource.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);

        Map<String, String> predicate = new HashMap<>();
        predicate.put("path", articleRootPath);
        predicate.put("type", "cq:Page");

        try {
            Query query = builder.createQuery(PredicateGroup.create(predicate), session);
            SearchResult searchResult = query.getResult();

            for (Hit hit : searchResult.getHits()) {
                try {
                    String path = hit.getPath();
                    Resource articleResource = resourceResolver.getResource(path);
                    Page articlePage = null!= articleResource ? articleResource.adaptTo(Page.class) : null;

                    if (articlePage != null) {
                        ArticleListDataBean articleListDataBean = new ArticleListDataBean();
                        articleListDataBean.setPath(path);
                        articleListDataBean.setTitle(articlePage.getTitle());
                        articleListDataBean.setDescription(articlePage.getDescription());

                        articleListDataBeans.add(articleListDataBean);
                    }
                } catch (RepositoryException e) {
                    LOGGER.error("Error processing search result hit", e);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error executing query", e);
        }
        return articleListDataBeans;
    }
}

