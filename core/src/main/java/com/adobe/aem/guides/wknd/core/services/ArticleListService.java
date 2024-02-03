package com.adobe.aem.guides.wknd.core.services;

import com.adobe.aem.guides.wknd.core.beans.ArticleListDataBean;
import org.apache.sling.api.resource.Resource;

import java.util.List;

public interface ArticleListService {
    public List<ArticleListDataBean> getArticleListDataBeans(Resource resource, String articleRootPath);
}
