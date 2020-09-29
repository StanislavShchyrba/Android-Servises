package com.example.sortingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder> {
    @NonNull
    private int[] mNumArray;

    public NumbersAdapter() {
        mNumArray = new int[0];
    }

    public void setDataSet(@NonNull int[] array) {
        mNumArray = array;
        notifyDataSetChanged();
    }

    @NonNull
    public int[] getDataSet() {
        return mNumArray;
    }

    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycler_element, parent, false);

        return new NumbersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersViewHolder holder, int position) {
        holder.bind(mNumArray[position]);
    }

    @Override
    public int getItemCount() {
        return mNumArray.length;
    }

    static class NumbersViewHolder extends RecyclerView.ViewHolder {
        TextView mListItemNumbersView;

        public NumbersViewHolder(View itemView) {
            super(itemView);
            mListItemNumbersView = itemView.findViewById(R.id.numberTextView);
        }

        public void bind(int model) {
            mListItemNumbersView.setText(String.valueOf(model));
        }
    }
}
