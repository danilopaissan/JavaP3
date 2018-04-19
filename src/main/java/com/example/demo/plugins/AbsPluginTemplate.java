package com.example.demo.plugins;

import com.example.demo.objects.Errors;
import com.example.demo.objects.Error;

public abstract class AbsPluginTemplate implements PluginTemplate {
    private int stakOrder = 0;
    private Errors errors = new Errors();

    @Override
    public int getStackOrder() {
        return stakOrder;
    }

    @Override
    public void initialize(int order) {
        stakOrder = order;
    }

    @Override
    public Errors getErrors() {
        return errors;
    }

    @Override
    public Errors getErrors(String componentName) {
        errors.forEach(error -> {
            error.setComponentName(componentName);
        });
        return errors;
    }

    public Errors addError(Error error){
        this.errors.add(error);
        return this.errors;
    }
}
