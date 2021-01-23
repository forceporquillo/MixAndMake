package com.force.codes.mixandmake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.mixandmake.components.adapter.CustomGridDecoration;
import com.force.codes.mixandmake.components.adapter.RecipeAdapter;

import java.util.ArrayList;
import java.util.Collections;

import static com.force.codes.mixandmake.components.Ingredients.CAKE;
import static com.force.codes.mixandmake.components.Ingredients.CREPES;
import static com.force.codes.mixandmake.components.Ingredients.ICING;
import static com.force.codes.mixandmake.components.Ingredients.OMELETTE;
import static com.force.codes.mixandmake.components.Ingredients.PANCAKE;
import static com.force.codes.mixandmake.components.Ingredients.SCRAMBLED;
import static com.force.codes.mixandmake.components.Ingredients.MATCH_ANSWERS;
import static com.force.codes.mixandmake.components.adapter.RecipeAdapter.RECIPES;

public class MixFragment extends Fragment implements RecipeAdapter.RecipeItemListener {

    public MixFragment() {

    }

    private String addRecipe;
    private TextView displayRecipe;

    private boolean isClicked = false;
    private boolean setOnce = true;

    private final ArrayList<String> recipes = new ArrayList<>();
    private final RecipeAdapter adapter = new RecipeAdapter(this);

    public static MixFragment newInstance() {
        return new MixFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getActivity() != null;
        assert getContext() != null;

        final int color = ContextCompat.getColor(getContext(), R.color.pink);
        getActivity().getWindow().setStatusBarColor(color);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mix, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayRecipe = view.findViewById(R.id.recipe_display);
        initRecipeGrid(view.findViewById(R.id.recipes_recyclerview));
        initClickListeners(view);
    }

    private void initRecipeGrid(final RecyclerView view) {
        view.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        view.addItemDecoration(CustomGridDecoration.createGrid());
        view.setAdapter(adapter);
    }

    private void initClickListeners(View root) {
        assert getFragmentManager() != null;
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        root.findViewById(R.id.question_button).setOnClickListener(v -> {
            Fragment prev = getFragmentManager().findFragmentByTag("recipe_dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            RecipesDialogFragment dialogFragment = new RecipesDialogFragment();
            dialogFragment.show(ft, "recipe_dialog");
        });

        root.findViewById(R.id.clear_button).setOnClickListener(v -> {
            clearAll();
            displayRecipe.setText(getResources().getString(R.string.add_recipes));
        });

        root.findViewById(R.id.add).setOnClickListener(v -> {
            if (addRecipe != null) {
                displayRecipe.append(" + ");
                isClicked = true;
            }
            addRecipe = null;
        });

        root.findViewById(R.id.equals).setOnClickListener(v -> {
            Collections.sort(recipes);
            String item = "";

            if (recipes.size() == 2) {
                if (recipes.equals(ICING)) {
                    item = "Icing";
                } else if (recipes.equals(SCRAMBLED)) {
                    item = "Scrambled Egg";
                }
            } else if (recipes.size() == 3) {
                if (recipes.equals(OMELETTE)) {
                    item = "Omelette";
                } else if (recipes.equals(PANCAKE)) {
                    item = "Pancake";
                } else if (recipes.equals(CREPES)){
                    item = "Crepes";
                }
            } else if (recipes.size() == 6){
                if (recipes.equals(CAKE)){
                    item = "Cake";
                }
            }

            for (String answers : MATCH_ANSWERS) {
                if (answers.equals(item)) {
                    displayRecipe.setText(getResources().getString(R.string.display_message, item));
                    break;
                } else {
                    displayRecipe.setText(getResources().getString(R.string.unknown));
                }
            }

            clearAll();
        });
    }

    @Override
    public void onClickRecipeItem(int index) {
        final String recipeItem = RECIPES[index].getName();
        addRecipe = recipeItem;

        if (setOnce || isClicked) {
            recipes.add(recipeItem);
        }

        if (setOnce) {
            displayRecipe.setText(recipeItem);
            setOnce = !setOnce;
        } else {
            if (isClicked) {
                displayRecipe.append(recipeItem);
                isClicked = !isClicked;
            }
        }
    }

    private void clearAll() {
        setOnce = true;
        recipes.clear();
    }

}