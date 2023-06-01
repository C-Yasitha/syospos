package com.brylix.derp.model;

public class Category extends NamedEntity {
    private int id;
    private String name;

    public Category(int id, String name) {
        super(id, name);
    }
}
