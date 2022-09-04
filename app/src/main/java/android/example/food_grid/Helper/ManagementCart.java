package android.example.food_grid.Helper;

import android.content.Context;
import android.example.food_grid.Domain.PopularDomain;
import android.example.food_grid.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(PopularDomain item){
        ArrayList<PopularDomain> listFood=getListCart();
        boolean existAlready=false;
        int n=0;
        for(int i=0;i<listFood.size();i++){
            if(listFood.get(i).getTittle().equals(item.getTittle())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if(existAlready){
            listFood.get(n).setNumberICart(item.getNumberICart());
        }else{
            listFood.add(item);
        }
        tinyDB.putListObject("CardList",listFood);

    }

    public ArrayList<PopularDomain> getListCart() {
        return tinyDB.getListObject("CardList");
    }
    public void minusNumberFood(ArrayList<PopularDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(listfood.get(position).getNumberICart()==1){
            listfood.remove(position);
        }else{
            listfood.get(position).setNumberICart(listfood.get(position).getNumberICart()-1);
        }
        tinyDB.putListObject("CardList",listfood);
        changeNumberItemsListener.changed();
    }
    public void plusNumberFood(ArrayList<PopularDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listfood.get(position).setNumberICart(listfood.get(position).getNumberICart()+1);
        tinyDB.putListObject("CardList",listfood);
        changeNumberItemsListener.changed();
    }
    public Double getTotalFee(){
        ArrayList<PopularDomain>listfood2=getListCart();
        double fee=0;
        for(int i=0;i<listfood2.size();i++){
            fee=fee+(listfood2.get(i).getFee()*listfood2.get(i).getNumberICart());
        }
        return fee;
    }
}
