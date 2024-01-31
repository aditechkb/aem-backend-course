package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import java.util.List;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProfileModel {

    @ValueMapValue
    private List<String> hobbies;
    @ChildResource
    private List<Experience> previousExperience;

    public List<Experience> getPreviousExperience() {
        return previousExperience;
    }


    public List<String> getHobbies() {
        return hobbies;
    }
}

