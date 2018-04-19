package com.example.demo.plugins;

import com.example.demo.objects.Errors;

import java.util.Map;

public interface PluginTemplate {

    void execute();
    int getStakOrder();
    String readData() throws Exception;
    void writeData() throws Exception;
    void initialize(int order);
    void initialize(int order, Map<String,String> prop);
    Errors getErrors();
    Errors getErrors(String componentName);
}
