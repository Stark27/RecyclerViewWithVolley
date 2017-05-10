package com.example.luismunoz.recyclerviewwithdata;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by LuisMunoz on 10-05-17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Photo> mDataSet;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;
        String urlImage;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.titleRow);
            mImageView = (ImageView) v.findViewById(R.id.imageIcon);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    //Toast.makeText(view.getContext(), "posicion " + pos, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("image", urlImage);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    public ListAdapter(Context context, ArrayList<Photo> mDataSet) {
        this.mContext = context;
        this.mDataSet = mDataSet;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(mDataSet.get(position).getTitle());
        holder.urlImage = mDataSet.get(position).getImageUrl();
        Glide.with(mContext).load(mDataSet.get(position).getImageUrl()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


}
