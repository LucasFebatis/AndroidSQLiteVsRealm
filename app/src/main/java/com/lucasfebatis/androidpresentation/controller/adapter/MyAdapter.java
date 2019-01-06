package com.lucasfebatis.androidpresentation.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucasfebatis.androidpresentation.R;
import com.lucasfebatis.androidpresentation.realm.PostObject;
import com.lucasfebatis.androidpresentation.sqlite.PostContract;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvId;
        public TextView tvTitle;
        public TextView tvSubTitle;
        public MyViewHolder(View v) {
            super(v);
            tvId = v.findViewById(R.id.tv_id);
            tvTitle = v.findViewById(R.id.tv_title);
            tvSubTitle = v.findViewById(R.id.tv_subtitle);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_base, parent, false);
        //...
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if(mDataset.get(position) instanceof PostContract.PostObject) {

            PostContract.PostObject postObject = (PostContract.PostObject) mDataset.get(position);

            holder.tvId.setText(postObject.id.toString());
            holder.tvTitle.setText(postObject.titulo);
            holder.tvSubTitle.setText(postObject.subtitulo);

        } else if(mDataset.get(position) instanceof PostObject) {

            PostObject postObject = (PostObject) mDataset.get(position);

            holder.tvId.setText(postObject.id.toString());
            holder.tvTitle.setText(postObject.titulo);
            holder.tvSubTitle.setText(postObject.subtitulo);

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}