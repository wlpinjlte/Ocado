package org.example;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigParser {
    private final String pathFile;

    public ConfigParser(String pathFile) {
        this.pathFile = pathFile;
    }
    public Map<String, List<String>> praseJson() throws IOException, ParseException {
        FileReader reader = new FileReader(pathFile);

        JSONObject jsonObject = (JSONObject)new JSONParser().parse(reader);

        return new Gson().fromJson(jsonObject.toString(), HashMap.class);
    }
}