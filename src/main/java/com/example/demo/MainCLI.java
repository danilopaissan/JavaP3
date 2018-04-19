package com.example.demo;

import com.example.demo.objects.Errors;
import com.example.demo.objects.ListOfBean;
import com.example.demo.objects.PipeLineElement;
import com.example.demo.plugins.AbsPluginTemplate;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MainCLI implements CommandLineRunner, ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        Map<String,AbsPluginTemplate> pluginContextMap = ctx.getBeansOfType(AbsPluginTemplate.class);
        PluginConfiguration configuration = new PluginConfiguration().readConfig();
        Map<String,AbsPluginTemplate> pluginEnabledMap = new HashMap<>();
        Map<String,ListOfBean> configurationEnabledMap = new HashMap<>();
        configuration.getListOfBean().forEach((bean) -> {
            if(pluginContextMap.containsKey(bean.getName())) {
                pluginEnabledMap.put(bean.getName(), pluginContextMap.get(bean.getName()));
                configurationEnabledMap.put(bean.getName(),bean);
            }
        });
        PipeLineElement[] plugins = new PipeLineElement[pluginEnabledMap.size()];
        pluginEnabledMap.forEach((key, bean) -> {
            PipeLineElement element = new PipeLineElement();
            element.setConfiguration(configurationEnabledMap.get(key));
            element.setPlugin(bean);
            plugins[configurationEnabledMap.get(key).getOrder()] = element;
        });
        Errors errors = new Errors();
        for (int length= 0; length < pluginEnabledMap.size(); length++) {
            AbsPluginTemplate plugin = plugins[length].getPlugin();
            System.out.println("Configuro il bean: " + plugins[length].getConfiguration().getName());
            plugin.initialize(plugins[length].getConfiguration().getOrder(),plugins[length].getConfiguration().getProperties());
            System.out.println("Eseguo il bean: " + plugins[length].getConfiguration().getName());
            plugin.execute();
            //Qui sarebbe meglio interrompere e uscire
            errors.addAll(plugin.getErrors(plugins[length].getConfiguration().getName()));
            System.out.println("Bean: " + plugins[length].getConfiguration().getName() + " eseguito!");
            plugins[length].setPlugin(plugin);
        }
        if(errors.size()>0){
            System.out.println("Programma terminato con errori");
            errors.forEach((error) -> {
                System.out.println("\t - " + error.getComponentName() + " -- " + error.getErrorDescription());
            });
        }
        System.in.read();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
