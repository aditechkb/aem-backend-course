    package com.adobe.aem.guides.wknd.core.models;

    import com.adobe.aem.guides.wknd.core.beans.ArticleListDataBean;
    import org.apache.sling.api.resource.Resource;
    import org.apache.sling.models.annotations.DefaultInjectionStrategy;
    import org.apache.sling.models.annotations.Model;
    import org.apache.sling.models.annotations.injectorspecific.Self;
    import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
    import org.apache.sling.models.annotations.injectorspecific.OSGiService;
    import javax.annotation.PostConstruct;
    import com.adobe.aem.guides.wknd.core.services.ArticleListService;

    import java.util.*;


    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public class ArticleListModel {

        @ValueMapValue
        private String articleRootPath;

        @Self
        private Resource resource;

        @OSGiService
        private ArticleListService articleListService;

        private List<ArticleListDataBean> articleListDataBeans;

        public String getArticleRootPath() {
            return articleRootPath;
        }

        public List<ArticleListDataBean> getArticleListDataBeans() {
            return articleListDataBeans;
        }

        @PostConstruct
        protected void init() {
            articleListDataBeans = articleListService.getArticleListDataBeans(resource, articleRootPath);
        }
    }



