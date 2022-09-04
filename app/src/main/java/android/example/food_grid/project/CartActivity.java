package android.example.food_grid.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.example.food_grid.Adapter.cartListAdapter;
import android.example.food_grid.Helper.ManagementCart;
import android.example.food_grid.Interface.ChangeNumberItemsListener;
import android.example.food_grid.R;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {
    RecyclerView.Adapter adapter;
    RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt;
    private Double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart=new ManagementCart(this);

        initList();
        initView();
        calculateCard();
        bottomNavigation();

    }
    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.home_Btn);
        LinearLayout cartBtn=findViewById(R.id.cart_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,CartActivity.class));
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        adapter=new cartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double percentTax=0.02;
        double delivery=10;
        tax=Math.round((managementCart.getTotalFee()*percentTax)*100.0)/100.0;
        double total=Math.round((managementCart.getTotalFee()+tax+delivery)*100.0)/100.0;
        double itemTotal=Math.round(managementCart.getTotalFee() *100.0)/100.0;
        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }

    private void initView() {
        totalFeeTxt=findViewById(R.id.totalFeetxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totaltxt);
        recyclerViewList=findViewById(R.id.view);
        scrollView=findViewById(R.id.scrollView);

    }
}