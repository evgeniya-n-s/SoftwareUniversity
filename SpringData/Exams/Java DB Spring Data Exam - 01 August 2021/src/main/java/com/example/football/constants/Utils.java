package com.example.football.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;

public enum Utils {
    ;
    public static final ModelMapper MODEL_MAPPER = new ModelMapper();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
}
