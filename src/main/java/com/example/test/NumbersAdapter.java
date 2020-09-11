package com.example.test;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder>{

    private static int mViewHolderCount;
    private int mNumberOfItems;

    public NumbersAdapter(int numberOfItems){
        mNumberOfItems = numberOfItems;
        mViewHolderCount=0;
    }
    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layoutIdForListItem = R.layout.main_recycler_element;

        LayoutInflater inflater=LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem,parent,false);

        NumbersViewHolder viewHolder = new NumbersViewHolder(view);
        viewHolder.mViewHolderIndex.setText("ViewHolder Index: "+mViewHolderCount);

        ++mViewHolderCount;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberOfItems;
    }

    static class NumbersViewHolder extends RecyclerView.ViewHolder{

        TextView mListItemNumbersView;
        TextView mViewHolderIndex;

        public NumbersViewHolder(View itemView){
            super(itemView);
            mListItemNumbersView=itemView.findViewById(R.id.main_RecyclerElement);

            mViewHolderIndex=itemView.findViewById(R.id.main_RecyclerElementHolder);
        }
        public void bind(int listIndex){
            mListItemNumbersView.setText(String.valueOf(listIndex));
        }

    }
}
