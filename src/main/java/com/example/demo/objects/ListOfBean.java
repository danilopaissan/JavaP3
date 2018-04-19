
package com.example.demo.objects;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "order",
    "properties"
})
public class ListOfBean {

    @JsonProperty("name")
    private String name;
    @JsonProperty("order")
    private Integer order;
    @JsonProperty("properties")
    private Map<String,String> properties = null;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("order")
    public Integer getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(Integer order) {
        this.order = order;
    }

    @JsonProperty("properties")
    public Map<String,String> getProperties() {
        return properties;
    }

    @JsonProperty("properties")
    public void setProperties(Map<String,String> properties) {
        this.properties = properties;
    }

}
