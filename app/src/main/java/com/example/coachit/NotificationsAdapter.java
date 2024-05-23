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

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotViewHolder> {

    private Context context;
    private List<DataClassNotifications> dataList;

    public NotificationsAdapter(Context context, List<DataClassNotifications> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_not , parent , false);

        return new NotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotViewHolder holder, int position) {
    holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recFrom.setText(dataList.get(position).getDataFrom());
        holder.recMessage.setText(dataList.get(position).getDataMessage());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailNotification.class);
                intent.putExtra("Title", dataList.get(holder.getBindingAdapterPosition()).getDataTitle());
                intent.putExtra("Message", dataList.get(holder.getBindingAdapterPosition()).getDataMessage());
                intent.putExtra("From", dataList.get(holder.getBindingAdapterPosition()).getDataFrom());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class NotViewHolder extends RecyclerView.ViewHolder{

    TextView recTitle , recMessage, recFrom;
    CardView recCard;
    public NotViewHolder(@NonNull View itemView){
        super(itemView);

        recFrom = itemView.findViewById(R.id.recFrom);
        recMessage = itemView.findViewById(R.id.recMessage);
        recTitle = itemView.findViewById(R.id.recTitle);
        recCard =itemView.findViewById(R.id.recCardNot);
    }
}
