package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.services.ResourceResolverService;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(service = ResourceResolverService.class)
public class ResourceResolverServiceImpl implements ResourceResolverService {

    @Reference
    private ResourceResolverFactory resourceResolverFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceResolverServiceImpl.class);

    @Override
    public ResourceResolver getResourceResolverForSystemUser() {
        ResourceResolver resourceResolver = null;
        try {
            String systemUserName = "wknd-admin-user";

            // Create a map of service user properties
            Map<String, Object> serviceUserProps = new HashMap<>();
            serviceUserProps.put(ResourceResolverFactory.SUBSERVICE, systemUserName);

            // Get the resource resolver using the ResourceResolverFactory
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(serviceUserProps);
        }  catch (Exception e) {
        // Log the exception to understand the failure reason
            LOGGER.error("Error obtaining resource resolver", e);
    }
        return resourceResolver;
    }
}

