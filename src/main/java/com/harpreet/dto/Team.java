package com.harpreet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

public enum Team {

    A("AUTOBOTS"),
    D("DECEPTICONS"),
    @JsonIgnore None("");

    @Getter
    private String transformerType;

    Team(String type) {
        transformerType = type;
    }
}
