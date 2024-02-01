package com.adobe.aem.guides.wknd.core.services.impl;


import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;
import java.io.IOException;
import com.adobe.aem.guides.wknd.core.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component(service = RecipeService.class)
public class RecipeServiceImpl implements RecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);

    @Override
    public String getRecipes() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet request = new HttpGet("https://dummyjson.com/recipes");
            String response = EntityUtils.toString(httpClient.execute(request).getEntity());
            LOGGER.debug(response);
            return response;

        } catch (IOException e) {
            LOGGER.error("IO Error",e);
            return null;
        }
    }
}
