package android.example.food_grid.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.example.food_grid.Domain.PopularDomain;
import android.example.food_grid.R;
import android.example.food_grid.project.ShowDetailActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class popularAdapter extends RecyclerView.Adapter<popularAdapter.ViewHolder> {

    ArrayList<PopularDomain> popularDomains;
    public popularAdapter(ArrayList<PopularDomain> categoryDomains){
        this.popularDomains=categoryDomains;
    }
    @NonNull
    @Override
    public popularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tittle.setText(popularDomains.get(position).getTittle());
        holder.fee.setText(String.valueOf(popularDomains.get(position).getFee()));

        int drawableResorceId=holder.itemView.getContext().getResources().getIdentifier(popularDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResorceId).into(holder.Pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", popularDomains.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return popularDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tittle,fee;
        ImageView Pic;
        ImageView addBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle=itemView.findViewById(R.id.tittleTxt);
            fee=itemView.findViewById(R.id.fee);
            addBtn=itemView.findViewById(R.id.addBtn);
            Pic=itemView.findViewById(R.id.pic);
        }
    }
}
