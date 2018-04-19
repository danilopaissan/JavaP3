
package com.example.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.objects.ListOfBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "listOfBean"
})
public class PluginConfiguration {

    @JsonProperty("listOfBean")
    private List<ListOfBean> listOfBean = null;

    @JsonProperty("listOfBean")
    public List<ListOfBean> getListOfBean() {
        return listOfBean;
    }

    @JsonProperty("listOfBean")
    public void setListOfBean(List<ListOfBean> listOfBean) {
        this.listOfBean = listOfBean;
    }

    public PluginConfiguration readConfig() throws Exception{
        String fileName = "pluginPipeline.json";
        ClassLoader classLoader = getClass().getClassLoader();
        String result;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<PluginConfiguration> typeRef
                = new TypeReference<PluginConfiguration>() {};
        PluginConfiguration configuration = null;
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
            configuration = mapper.readValue(result, typeRef);
        } catch (IOException e) {
            throw e;
        }
        return configuration;
    }

}
