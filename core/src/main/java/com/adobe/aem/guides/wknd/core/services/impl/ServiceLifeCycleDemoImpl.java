package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.services.ServiceLifeCycleDemo;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ServiceLifeCycleDemo.class)
public class ServiceLifeCycleDemoImpl implements ServiceLifeCycleDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLifeCycleDemoImpl.class);
    @Activate
    protected void activate() {
        // Code to execute when service is activated
        LOGGER.debug("MyService Activated");
    }

    @Deactivate
    protected void deactivate() {
        // Code to execute when service is deactivated
        LOGGER.debug("MyService Deactivated");
    }

    @Override
    public void doSomething() {
        // Implementation of a service method
        LOGGER.debug("Doing something");
    }
}
