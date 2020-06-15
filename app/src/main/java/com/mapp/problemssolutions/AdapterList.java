package com.mapp.problemssolutions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {
    List<MyDataList> myDataList;
    ItemClickListener itemClickListener;

    public AdapterList(List<MyDataList> myDataList, ItemClickListener itemClickListener){
        this.myDataList=myDataList;
        this.itemClickListener=itemClickListener;
    }


    @NonNull
    @Override
    public AdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_data, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterList.ViewHolder holder, int position) {
        MyDataList mDataList=myDataList.get(position);

        /*TextView txtTitle=holder.txtTitle;
        txtTitle.setText(mDataList.getTitle());

        TextView txtSite=holder.txtSite;
        txtSite.setText(mDataList.getSite());

        TextView txtLevel=holder.txtLevel;
        txtLevel.setText(mDataList.getLevel());

        TextView txtUrl=holder.txtUrl;
        txtUrl.setText(mDataList.getUrl());*/

        holder.bindData(mDataList, itemClickListener);

    }

    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public TextView txtSite;
        public TextView txtLevel;
        public TextView txtUrl;


        public void bindData(final MyDataList dt, final ItemClickListener click){

            txtTitle.setText(dt.getTitle());
            txtSite.setText(dt.getSite());
            txtLevel.setText(dt.getLevel());
            txtUrl.setText(dt.getUrl());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onItemClick(dt);
                }
            });

        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle=(TextView) itemView.findViewById(R.id.txtTitle);
            txtSite=(TextView) itemView.findViewById(R.id.txtSite);
            txtLevel=(TextView) itemView.findViewById(R.id.txtLevel);
            txtUrl=(TextView) itemView.findViewById(R.id.txtUrl);
        }
    }
}
