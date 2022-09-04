package android.example.food_grid.Adapter;

import android.example.food_grid.Domain.CategoryDomain;
import android.example.food_grid.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<CategoryDomain> categoryDomains;
    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains){
        this.categoryDomains=categoryDomains;
    }
    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDomains.get(position).getTittle());
        String picUrl="";
        switch (position){
            case 0: {
                picUrl = "pizza";
                break;
            }
            case 1: {
                picUrl = "hamburger";
                break;
            }
            case 2: {
                picUrl = "softdrink";
                break;
            }
            case 3: {
                picUrl = "bibimbap";
                break;
            }
            case 4: {
                picUrl = "friedpotatoes";
                break;
            }
        }
        int drawableResorceId=holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResorceId).into(holder.categoryPic);
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName=itemView.findViewById(R.id.category_text);
            categoryPic=itemView.findViewById(R.id.category_Pic);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
