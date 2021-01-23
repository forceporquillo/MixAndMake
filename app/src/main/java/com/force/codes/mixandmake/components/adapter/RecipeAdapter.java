package com.force.codes.mixandmake.components.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.mixandmake.R;
import com.force.codes.mixandmake.components.model.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    public static final Recipe[] RECIPES = new Recipe[]{
            new Recipe(R.drawable.egg, "egg"),
            new Recipe(R.drawable.sugar, "sugar"),
            new Recipe(R.drawable.butter, "butter"),
            new Recipe(R.drawable.milk, "milk"),
            new Recipe(R.drawable.baking_powder, "baking powder"),
            new Recipe(R.drawable.flour, "flour")
    };

    public interface RecipeItemListener {
        void onClickRecipeItem(final int index);
    }

    private final RecipeItemListener listener;

    public RecipeAdapter(RecipeItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RecipeViewHolder.createInstance(parent, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bindRecipe(RECIPES[position]);
    }

    @Override
    public int getItemCount() {
        return RECIPES.length;
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView recipeIcon;
        private TextView recipeText;

        private final RecipeItemListener listener;

        public RecipeViewHolder(@NonNull View itemView, RecipeItemListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            recipeIcon = itemView.findViewById(R.id.recipe_icon);
            recipeText = itemView.findViewById(R.id.recipe_name);
        }

        void bindRecipe(Recipe recipe) {
            recipeIcon.setBackgroundResource(recipe.getIcon());
            recipeText.setText(recipe.getName());
        }

        static RecipeViewHolder createInstance(ViewGroup parent, RecipeItemListener listener) {
            final View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_item, parent, false);
            return new RecipeViewHolder(itemView, listener);
        }

        @Override
        public void onClick(View v) {
            listener.onClickRecipeItem(getAdapterPosition());
            v.setEnabled(false);
            v.postDelayed(() -> v.setEnabled(true), 500);
        }
    }
}
