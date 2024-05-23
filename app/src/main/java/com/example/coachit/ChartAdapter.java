package com.example.coachit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ChartAdapter extends RecyclerView.Adapter<ChartViewHolder> {

    private Context context;
    private List<DataClass>dataList;

    public ChartAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ChartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartViewHolder holder, int position) {

        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recName.setText(dataList.get(position).getDataName());
        holder.recSurname.setText(dataList.get(position).getDataSurname());
        holder.recNickname.setText(dataList.get(position).getDataNickname());
        holder.recAge.setText(dataList.get(position).getDataAge());
        holder.recCity.setText(dataList.get(position).getDataCity());
        holder.recSex.setText(dataList.get(position).getDataSex());
        holder.recCoach.setText(dataList.get(position).getDataCoach());
        holder.recTeam.setText(dataList.get(position).getDataTeam());
        holder.recPosition.setText(dataList.get(position).getDataPosition());
        holder.recHeight.setText(dataList.get(position).getDataHeight());
        holder.recWeight.setText(dataList.get(position).getDataWeight());
        holder.recDomhand.setText(dataList.get(position).getDataDomhand());
        holder.recWingspan.setText(dataList.get(position).getDataWingspan());
        holder.recVertical.setText(dataList.get(position).getDataVertical());
        holder.recContact1.setText(dataList.get(position).getDataContact1());
        holder.recContact2.setText(dataList.get(position).getDataContact2());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChartActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class ChartViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recName, recSurname,recNickname,recAge,recCity,recSex,recTeam,recCoach,recPosition,recHeight,recWeight,recDomhand,recWingspan,recVertical,recContact1,recContact2;
    CardView recCard;


    public ChartViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);

        recName = itemView.findViewById(R.id.recName);
        recSurname = itemView.findViewById(R.id.recSurname);
        recNickname = itemView.findViewById(R.id.recNickname);
        recAge = itemView.findViewById(R.id.recAge);
        recSex = itemView.findViewById(R.id.recSex);
        recCity = itemView.findViewById(R.id.recCity);
        recTeam = itemView.findViewById(R.id.recTeam);
        recCoach = itemView.findViewById(R.id.recCoach);
        recPosition = itemView.findViewById(R.id.recPosition);
        recHeight = itemView.findViewById(R.id.recHeight);
        recWeight = itemView.findViewById(R.id.recWeight);
        recDomhand = itemView.findViewById(R.id.recDomhand);
        recWingspan = itemView.findViewById(R.id.recWingspan);
        recVertical = itemView.findViewById(R.id.recVertical);
        recContact1 = itemView.findViewById(R.id.recContact1);
        recContact2 = itemView.findViewById(R.id.recContact2);

        recCard = itemView.findViewById(R.id.recCard);
    }
}