package com.example.demo.objects;

import com.example.demo.plugins.AbsPluginTemplate;

public class PipeLineElement {
    private ListOfBean configuration;
    private AbsPluginTemplate plugin;

    public ListOfBean getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ListOfBean configuration) {
        this.configuration = configuration;
    }

    public AbsPluginTemplate getPlugin() {
        return plugin;
    }

    public void setPlugin(AbsPluginTemplate plugin) {
        this.plugin = plugin;
    }
}
