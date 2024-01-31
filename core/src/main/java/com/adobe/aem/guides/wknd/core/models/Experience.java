package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

//Inner Class for Experience
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Experience {
    @ValueMapValue
    private String companyName;
    @ValueMapValue
    private String startDate;
    @ValueMapValue
    private String endDate;

    // Getters
    public String getCompanyName() { return companyName; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
}

