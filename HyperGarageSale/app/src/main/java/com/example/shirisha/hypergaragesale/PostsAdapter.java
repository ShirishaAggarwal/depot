package com.example.shirisha.hypergaragesale;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shirisha on 3/11/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private ArrayList<BrowsePosts> pDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pTitle;
        public TextView pPrice;
        public ViewHolder(View view){
            super(view);
            pTitle = (TextView) itemView.findViewById(R.id.titleView);
            pPrice = (TextView) itemView.findViewById(R.id.priceView);
        }
    }

    public PostsAdapter(ArrayList<BrowsePosts> myDataSet){
        pDataSet = myDataSet;
    }

    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_text_view,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pTitle.setText(pDataSet.get(position).pTitle);
        holder.pPrice.setText(pDataSet.get(position).pPrice);
    }

    @Override
    public int getItemCount(){
        return pDataSet.size();
    }
}
