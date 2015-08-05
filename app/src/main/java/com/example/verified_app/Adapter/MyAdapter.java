package com.example.verified_app.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.verified_app.R;

import java.util.List;

import com.example.verified_app.dao.ItemInfo;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
  private List<ItemInfo> itemInfoList;
    Activity activity;



  public MyAdapter(List<ItemInfo> itemInfoList, Activity activity) {
            this.itemInfoList =itemInfoList;
            this.activity=activity;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, final int position) {




  }

  @Override
  public int getItemCount() {
    return itemInfoList.size();
  }


    class onItemCliked implements View.OnClickListener {
        ItemInfo itemInfo;
        int position;
        public onItemCliked(ItemInfo stickInfo,int position) {
            this.itemInfo =stickInfo;
            this.position=position;
        }
        public onItemCliked() {
        }
        @Override
        public void onClick(View v) {

            switch(v.getId()){

            }

        }



    }




}




