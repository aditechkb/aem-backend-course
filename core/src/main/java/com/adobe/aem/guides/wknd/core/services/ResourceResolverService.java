package com.adobe.aem.guides.wknd.core.services;

import org.apache.sling.api.resource.ResourceResolver;

public interface ResourceResolverService {

    public ResourceResolver getResourceResolverForSystemUser();
}
