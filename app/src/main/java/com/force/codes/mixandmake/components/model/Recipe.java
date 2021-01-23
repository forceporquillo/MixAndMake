package com.force.codes.mixandmake.components.model;

import androidx.annotation.IdRes;

public class Recipe {

    @IdRes
    private final int drawable;
    private final String name;

    public Recipe() {
        this(0, null);
    }

    public Recipe(int drawable, String name) {
        this.drawable = drawable;
        this.name = name;
    }

    public int getIcon() {
        return drawable;
    }

    public String getName() {
        return name;
    }
}
