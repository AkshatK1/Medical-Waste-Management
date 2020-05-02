package com.example.request;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class requestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView name,address,status,zip,city,state,landmark,phone,wastetype;
    public listener listener;

    public requestViewHolder(@NonNull View itemView) {
        super(itemView);
        name= itemView.findViewById(R.id.name);
        address=itemView.findViewById(R.id.address);
        status=itemView.findViewById(R.id.Status);
        zip=itemView.findViewById(R.id.zip);
        city=itemView.findViewById(R.id.city);
        state=itemView.findViewById(R.id.state);
        landmark=itemView.findViewById(R.id.landmark);
        phone=itemView.findViewById(R.id.phone);
        wastetype=itemView.findViewById(R.id.wastetype);
    }
    public void setListener(listener listener)
    {
        this.listener=listener;
    }


    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
