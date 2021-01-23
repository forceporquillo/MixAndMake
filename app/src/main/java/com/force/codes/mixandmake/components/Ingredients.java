package com.force.codes.mixandmake.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ingredients {

    public static final List<String> OMELETTE = new ArrayList<>(
            Arrays.asList("butter", "egg", "milk")
    );

    public static final List<String> PANCAKE = new ArrayList<>(
            Arrays.asList("egg", "flour", "milk")
    );

    public static final List<String> CREPES = new ArrayList<>(
            Arrays.asList("flour", "milk", "milk")
    );

    public static final List<String> CAKE = new ArrayList<>(
            Arrays.asList("baking powder", "butter", "egg", "flour", "milk", "sugar")
    );

    public static final List<String> ICING = new ArrayList<>(
            Arrays.asList("butter", "sugar")
    );

    public static final List<String> SCRAMBLED = new ArrayList<>(
            Arrays.asList("egg", "egg")
    );

    public static final String[] MATCH_ANSWERS = new String[] {
            "Icing", "Scrambled Egg", "Omelette", "Pancake", "Cake", "Crepes"
    };
}