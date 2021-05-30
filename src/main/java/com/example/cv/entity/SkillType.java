package com.example.cv.entity;

public enum SkillType {

    LANGUAGE("Language"),
    FRAMEWORK("Framework"),
    DATABASE("Database");

    public final String label;

    SkillType(String label) {
        this.label = label;
    }

}
