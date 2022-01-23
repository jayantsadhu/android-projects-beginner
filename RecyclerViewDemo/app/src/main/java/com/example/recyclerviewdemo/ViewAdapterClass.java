package com.example.recyclerviewdemo;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewAdapterClass extends RecyclerView.Adapter<ViewAdapterClass.ViewHolder> {

    List<RecyclerModel> userList;
    public ViewAdapterClass(List<RecyclerModel> userList){
        this.userList = userList;
    }

    // Where to get the single card as viewHolder Object
    @NonNull
    @Override
    public ViewAdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    // What will happen after we create the viewHolder object
    @Override
    public void onBindViewHolder(@NonNull ViewAdapterClass.ViewHolder holder, int position) {
        int imgRes = userList.get(position).getImageView();
        String viewName = userList.get(position).getNameView();
        String viewMessage = userList.get(position).getMessageView();
        String viewTime = userList.get(position).getTimeView();
        holder.setData(imgRes, viewName, viewMessage, viewTime);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MessageActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    // How many items?
    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameView;
        private TextView messageView;
        private TextView timeView;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.profile_image);
            nameView = itemView.findViewById(R.id.name_view);
            messageView = itemView.findViewById(R.id.message_view);
            timeView = itemView.findViewById(R.id.time_view);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
        }

        public void setData(int imgRes, String viewName, String viewMessage, String viewTime) {
            imageView.setImageResource(imgRes);
            nameView.setText(viewName);
            messageView.setText(viewMessage);
            timeView.setText(viewTime);
        }
    }
}

