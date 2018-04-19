package com.example.demo.plugins;

import com.example.demo.objects.Error;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(value = "toUpperCase")
public class ToUpperCase extends AbsPluginTemplate {

    @Override
    public void execute() {
        try {
            readData().toUpperCase();
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorDescription(e.toString());
            addError(error);
        }
    }

    @Override
    public String readData() throws Exception {
        return null;
    }

    @Override
    public void writeData() throws Exception {

    }

    @Override
    public void initialize(int order, Map<String, String> prop) {
        this.initialize(order);
    }
}
