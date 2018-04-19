package com.example.demo.plugins;

import com.example.demo.objects.Error;
import com.example.demo.objects.Errors;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component(value = "removeSpaces")
public class RemoveSpaces extends AbsPluginTemplate {

    @Override
    public void execute() {
        try {
            this.readData().replace("\\s","");
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
