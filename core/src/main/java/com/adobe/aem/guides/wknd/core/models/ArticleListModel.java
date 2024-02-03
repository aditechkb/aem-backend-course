package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.beans.ArticleListDataBean;
import com.adobe.aem.guides.wknd.core.services.RecipeService;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import com.day.cq.search.result.SearchResult;

import java.util.*;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public class ArticleListModel {

    @ValueMapValue
    private String articleRootPath;

    @Self
    Resource resource;


    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleListModel.class);

    private final List<ArticleListDataBean> articleListDataBeans = new ArrayList<ArticleListDataBean>();


    public String getArticleRootPath() {
        return articleRootPath;
    }
    public List<ArticleListDataBean> getArticleListDataBeans(){return articleListDataBeans;}

    @PostConstruct
    protected void init() {

        LOGGER.debug("Init Method Triggered!!");

        ResourceResolver resourceResolver = resource.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        QueryBuilder builder = resourceResolver.adaptTo(QueryBuilder.class);

        Map<String, String> predicate = new HashMap<>();
        predicate.put("path",articleRootPath);
        predicate.put("type","cq:Page");

        Query query = null;

        try{
            if(builder !=null){
                LOGGER.debug("Query Builder not null");
                query = builder.createQuery(PredicateGroup.create(predicate),session);

                SearchResult searchResult = null;

                if (null != query){
                    LOGGER.debug("Query not null");
                    searchResult = query.getResult();

                    if(null != searchResult){
                        LOGGER.debug("SearchResult not null");
                        for(Hit hit : searchResult.getHits()){
                            String path = null;
                            ArticleListDataBean articleListDataBean = new ArticleListDataBean();

                            try {
                                path = hit.getPath();
                                LOGGER.debug("Path:{}",path);
                                Resource articleResource = resourceResolver.getResource(path);

                                if(null != articleResource){
                                    LOGGER.debug("ArticleResource not null");
                                    Page articlePage = articleResource.adaptTo(Page.class);

                                    if(null != articlePage){
                                        LOGGER.debug("ArticlePage not null");
                                        String title = articlePage.getTitle();
                                        String description = articlePage.getDescription();

                                        articleListDataBean.setPath(path);
                                        articleListDataBean.setTitle(title);
                                        articleListDataBean.setDescription(description);
                                        LOGGER.debug("Path:{}\nTitle:{}\nDescription:{}",path,title,description);

                                        articleListDataBeans.add(articleListDataBean);
                                    }
                                }
                            } catch (RepositoryException e) {
                                LOGGER.error("Error in getting Hits",e);
                            }

                        }
                    }
                }
            }
        }catch (Exception e){
            LOGGER.error("Error in query builder",e);
        }
    }
}

