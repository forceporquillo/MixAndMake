package com.force.codes.mixandmake.components.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomGridDecoration extends RecyclerView.ItemDecoration {

    private static final int COLUMNS = 3;
    private static final int SPACING = 10;

    @Override
    public void getItemOffsets(
            @NonNull Rect outRect,
            @NonNull View view,
            @NonNull RecyclerView parent,
            @NonNull RecyclerView.State state
    ) {

        final int offsetPos = parent.getChildAdapterPosition(view);

        final int column = offsetPos / COLUMNS;

        outRect.left = SPACING - column * SPACING / COLUMNS;
        outRect.right = (column + 1) * SPACING / COLUMNS;

        if (offsetPos < COLUMNS) {
            outRect.top = SPACING;
        }

        outRect.bottom = SPACING;

    }

    public static CustomGridDecoration createGrid() {
        return new CustomGridDecoration();
    }
}
