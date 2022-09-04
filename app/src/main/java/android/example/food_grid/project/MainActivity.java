package android.example.food_grid.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.example.food_grid.Adapter.CategoryAdapter;
import android.example.food_grid.Adapter.popularAdapter;
import android.example.food_grid.Domain.CategoryDomain;
import android.example.food_grid.Domain.PopularDomain;
import android.example.food_grid.R;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView viewCategoryList,viewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        recylerViewCategory();
        recylerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.home_Btn);
        LinearLayout cartBtn=findViewById(R.id.cart_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private void recylerViewPopular() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        viewPopularList=findViewById(R.id.viewPopular);
        viewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<PopularDomain> popularList=new ArrayList<>();
        popularList.add(new PopularDomain("Pasta Pizza","pitza","slices pasta, mozerale cheese, tomto sauce, fresh organo",500.0,5,20,1200));
        popularList.add(new PopularDomain("Roasgulla","rasgula","sweet falvour, cheese, cotton cheese, Elachi",300.0,5,25,200));
        popularList.add(new PopularDomain("Briyani","chickenbiryani","rice, chicken, tomto sauce, fresh organo",1000.0,5,19,1300));
        popularList.add(new PopularDomain("Veg Thali","vegthali","Daal, Roti, Paneer, palak",1000.0,5,20,500));

        adapter2=new popularAdapter(popularList);
        viewPopularList.setAdapter(adapter2);
    }

    private void recylerViewCategory() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        viewCategoryList=findViewById(R.id.viewCategories);
        viewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList=new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza","pizza"));
        categoryList.add(new CategoryDomain("Burger","hamburger"));
        categoryList.add(new CategoryDomain("Drinks","softdrink"));
        categoryList.add(new CategoryDomain("Veg Thali","bibimbap"));
        categoryList.add(new CategoryDomain("Fries","friedpotatoes"));

        adapter=new CategoryAdapter(categoryList);
        viewCategoryList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(MainActivity.this,Register.class));
        }
    }
}