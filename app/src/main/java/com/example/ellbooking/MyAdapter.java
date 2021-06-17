package com.example.ellbooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<FetchData> fetchData;

    public MyAdapter(List<FetchData> fetchData) {
        this.fetchData = fetchData;
    }

    String timeSlots[], status[];
    Boolean booked[];
    Context context;
    private ArrayList<Integer> selectCheck = new ArrayList<>();
    private int selectedPosition;


    public MyAdapter(Context ct, String data1[], String data2[], Boolean data3[]) {
        context = ct;
        timeSlots = data1;
        status = data2;
        booked = data3;

        for (int i = 0; i < timeSlots.length; i++) {
            selectCheck.add(0);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(timeSlots[position]);
        holder.myText2.setText(status[position]);
        holder.checkBox1.setChecked(booked[position]);

        if(holder.myText2.getText().toString().equals("Booked")) {
            holder.checkBox1.setClickable(false);
            holder.checkBox1.setEnabled(false);
        }

        if (selectCheck.get(position) == 1) {
            holder.checkBox1.setChecked(true);
            selectedPosition = position;

            Intent intent = new Intent("CheckBox_Position");
            intent.putExtra("position" , String.valueOf(position));
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        } else {
            holder.checkBox1.setChecked(false);
        }

        holder.checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int k=0; k<selectCheck.size(); k++) {
                    if(k==position) {
                        selectCheck.set(k,1);
                    } else {
                        selectCheck.set(k,0);
                    }
                }
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return timeSlots.length;
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        TextView myText1, myText2;
        CheckBox checkBox1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            myText1 = itemView.findViewById(R.id.timeSlot);
            myText2 = itemView.findViewById(R.id.status);
            checkBox1 = itemView.findViewById(R.id.booked);

            if(myText2.getText().toString().equals("Booked")) {
                checkBox1.setClickable(false);
                checkBox1.setEnabled(false);
            }
        }

    }

}
