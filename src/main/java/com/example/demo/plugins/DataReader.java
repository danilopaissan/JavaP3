package com.example.demo.plugins;

import com.example.demo.objects.Error;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

@Component(value = "dataReader")
public class DataReader extends AbsPluginTemplate {

    private String stringPath;

    @Override
    public void execute() {
        Path path = Paths.get(stringPath);
        StringBuffer stringBuffer = new StringBuffer();
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> stringBuffer.append(s));
        } catch (IOException ex) {
            Error error = new Error();
            error.setErrorDescription(ex.toString());
            addError(error);
        } catch (Exception ex) {
            Error error = new Error();
            error.setErrorDescription(ex.toString());
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
        this.stringPath = prop.get("file-input-path");
        this.initialize(order);
    }
}
