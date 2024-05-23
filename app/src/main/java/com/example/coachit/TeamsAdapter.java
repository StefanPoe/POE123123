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

public class TeamsAdapter extends RecyclerView.Adapter<TeamsViewHolder> {
    private Context context;
    private List<DataClassTeams> dataListTeams;

    public TeamsAdapter(Context context, List<DataClassTeams> dataList) {
        this.context = context;
        this.dataListTeams = dataList;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_utem_team, parent, false);
        return new TeamsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        Glide.with(context).load(dataListTeams.get(position).getDataTeamImage()).into(holder.recTeamImage);
        holder.recTeamName.setText(dataListTeams.get(position).getDataTeamName());
        holder.recTeamCoach.setText(dataListTeams.get(position).getDataTeamCoach());


        holder.recTeamCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Detail_Teams_Activity.class);
                intent.putExtra("TeamImage" , dataListTeams.get(holder.getBindingAdapterPosition()).getDataTeamImage());
                intent.putExtra("TeamName" , dataListTeams.get(holder.getBindingAdapterPosition()).getDataTeamName());
                intent.putExtra("TeamCoach" , dataListTeams.get(holder.getBindingAdapterPosition()).getDataTeamCoach());


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataListTeams.size();
    }

    public void searchDataList(ArrayList<DataClassTeams> searchListTeams){

        dataListTeams = searchListTeams;
        notifyDataSetChanged();

    }

}


class TeamsViewHolder extends RecyclerView.ViewHolder{

    ImageView recTeamImage;
    TextView recTeamName, recTeamCoach;
    CardView recTeamCard;


    public TeamsViewHolder(@NonNull View itemView) {
        super(itemView);

        recTeamImage = itemView.findViewById(R.id.recTeamImage);
        recTeamCard = itemView.findViewById(R.id.recTeamCard);
        recTeamName = itemView.findViewById(R.id.recTeamName);
        recTeamCoach = itemView.findViewById(R.id.recTeamCoach);



    }
};





