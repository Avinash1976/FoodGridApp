package android.example.food_grid.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.food_grid.R;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private TextView textView;
    FirebaseAuth mAuth;
    TextInputEditText etRegEmail;
    TextInputEditText etRegPassword;
    Button btnRegister;
    TextView etRegname;
    EditText etRegPhone;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodgrid-174f5-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegEmail=findViewById(R.id.RegEmail);
        etRegPhone=findViewById(R.id.RegPhone);
        etRegPassword=findViewById(R.id.RegPassword);
        btnRegister=findViewById(R.id.btn_register);
        etRegname=findViewById(R.id.RegName);

        mAuth=FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view -> {
            createUser();
        });

        textView=(TextView) findViewById(R.id.txt_alreadyLog);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this, LogIn.class);
                startActivity(intent);
            }
        });
    }

    private void createUser() {
        final String email=etRegEmail.getText().toString();
        final String password=etRegPassword.getText().toString();
        final String userName=etRegname.getText().toString();
        final String phone=etRegPhone.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(userName) || TextUtils.isEmpty(phone)){
            etRegname.setError("Fill all the blanks");
            etRegname.requestFocus();
        }else{
            databaseReference.child("users").child(phone).child("Full name").setValue(userName);
            databaseReference.child("users").child(phone).child("Email").setValue(email);
            databaseReference.child("users").child(phone).child("Password").setValue(password);

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this, "User Register Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this,LogIn.class));
                    }else{
                        Toast.makeText(Register.this, "Registration Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}