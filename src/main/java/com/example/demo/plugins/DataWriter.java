package com.example.demo.plugins;

import com.example.demo.objects.Error;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Component(value = "dataWriter")
public class DataWriter extends AbsPluginTemplate {
    private String path;

    @Override
    public void execute() {
        BufferedWriter writer = null;
        try{
            File logFile = new File(path);
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(this.readData());
        } catch (IOException e) {
            Error error = new Error();
            error.setErrorDescription(e.toString());
            addError(error);
        } catch (Exception e) {
            Error error = new Error();
            error.setErrorDescription(e.toString());
            addError(error);
        } finally {
            try{
                writer.close();
            } catch (IOException e) {
                Error error = new Error();
                error.setErrorDescription(e.toString());
                addError(error);
            } catch (Exception e) {
                Error error = new Error();
                error.setErrorDescription(e.toString());
                addError(error);
            }
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
        path = prop.get("file-output-path");
        this.initialize(order);
    }
}
