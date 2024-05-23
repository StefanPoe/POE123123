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

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerViewHolder> {
    private Context context;
    private List<DataClass> dataList;

    public PlayerAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
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
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image" , dataList.get(holder.getBindingAdapterPosition()).getDataImage());
                intent.putExtra("Name" , dataList.get(holder.getBindingAdapterPosition()).getDataName());
                intent.putExtra("Surname" , dataList.get(holder.getBindingAdapterPosition()).getDataSurname());
                intent.putExtra("Nickname" , dataList.get(holder.getBindingAdapterPosition()).getDataNickname());
                intent.putExtra("Age" , dataList.get(holder.getBindingAdapterPosition()).getDataAge());
                intent.putExtra("Sex" , dataList.get(holder.getBindingAdapterPosition()).getDataSex());
                intent.putExtra("City" , dataList.get(holder.getBindingAdapterPosition()).getDataCity());
                intent.putExtra("Team" , dataList.get(holder.getBindingAdapterPosition()).getDataTeam());
                intent.putExtra("Coach" , dataList.get(holder.getBindingAdapterPosition()).getDataCoach());
                intent.putExtra("Position" , dataList.get(holder.getBindingAdapterPosition()).getDataPosition());
                intent.putExtra("Height" , dataList.get(holder.getBindingAdapterPosition()).getDataHeight());
                intent.putExtra("Weight" , dataList.get(holder.getBindingAdapterPosition()).getDataWeight());
                intent.putExtra("Domhand" , dataList.get(holder.getBindingAdapterPosition()).getDataDomhand());
                intent.putExtra("Wingspan" , dataList.get(holder.getBindingAdapterPosition()).getDataWingspan());
                intent.putExtra("Vertical" , dataList.get(holder.getBindingAdapterPosition()).getDataVertical());
                intent.putExtra("Contact1" , dataList.get(holder.getBindingAdapterPosition()).getDataContact1());
                intent.putExtra("Contact2" , dataList.get(holder.getBindingAdapterPosition()).getDataContact2());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){

        dataList = searchList;
        notifyDataSetChanged();

    }

}


    class PlayerViewHolder extends RecyclerView.ViewHolder{

        ImageView recImage;
        TextView recName, recSurname,recNickname,recAge,recCity,recSex,recTeam,recCoach,recPosition,recHeight,recWeight,recDomhand,recWingspan,recVertical,recContact1,recContact2;
        CardView recCard;


        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            recImage = itemView.findViewById(R.id.recImage);
            recCard = itemView.findViewById(R.id.recCard);
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


        }
    };




