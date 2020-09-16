package com.example.test;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder> {
    private int[] mNumArray;

    public NumbersAdapter(int numArray) {
        mNumArray = new int[numArray];
    }

    public void setDataSet(int[] array) {
        mNumArray = array;
        notifyDataSetChanged();
    }

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

        public void bind(int listIndex) {
            mListItemNumbersView.setText(String.valueOf(listIndex));
        }
    }
}
