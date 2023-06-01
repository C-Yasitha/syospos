package com.brylix.derp.model;

public class NamedEntityFactory {

    public Category createCategory(int id, String name) {
        return new Category(id,name);
    }

    public Brand createBrand(int id, String name) {
        return new Brand(id,name);
    }
}
