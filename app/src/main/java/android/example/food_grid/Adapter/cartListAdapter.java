package android.example.food_grid.Adapter;

import android.content.Context;
import android.example.food_grid.Domain.PopularDomain;
import android.example.food_grid.Helper.ManagementCart;
import android.example.food_grid.Interface.ChangeNumberItemsListener;
import android.example.food_grid.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class cartListAdapter extends RecyclerView.Adapter<cartListAdapter.ViewHolder> {

    ArrayList<PopularDomain> listFoodSelected;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;
    public cartListAdapter(ArrayList<PopularDomain> categoryDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener){
        this.listFoodSelected=categoryDomains;
        managementCart=new ManagementCart(context);
        this.changeNumberItemsListener=changeNumberItemsListener;
    }
    @NonNull
    @Override
    public cartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tittle.setText(listFoodSelected.get(position).getTittle());
        holder.feeEachItem.setText("$"+(listFoodSelected.get(position).getFee()));
        holder.totalEachItem.setText("$"+Math.round((listFoodSelected.get(position).getNumberICart()*listFoodSelected.get(position).getFee())));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberICart()));

        int drawableResorceId=holder.itemView.getContext().getResources().getIdentifier(listFoodSelected.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResorceId).into(holder.Pic);

        holder.plusItem.setOnClickListener(view -> managementCart.plusNumberFood(listFoodSelected, position, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                notifyDataSetChanged();
                changeNumberItemsListener.changed();
            }
        }));

        holder.minusItem.setOnClickListener(view -> managementCart.minusNumberFood(listFoodSelected, position, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                notifyDataSetChanged();
                changeNumberItemsListener.changed();
            }
        }));

    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tittle,feeEachItem;
        ImageView Pic,plusItem,minusItem;
        TextView totalEachItem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle=itemView.findViewById(R.id.tittleTxt);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            Pic=itemView.findViewById(R.id.pic);
            plusItem=itemView.findViewById(R.id.plusCardBtn);
            minusItem=itemView.findViewById(R.id.minusCardBtn);
            num=itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
