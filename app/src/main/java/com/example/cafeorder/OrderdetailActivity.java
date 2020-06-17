package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderdetailActivity extends AppCompatActivity {
private TextView textViewOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        textViewOrder = findViewById(R.id.textViewOrderdetiel);
        Intent intent = getIntent();
        if(intent.hasExtra("order")){
            String order = intent.getStringExtra("order");
            textViewOrder.setText(order);

        }else{
            Intent backToLogin = new Intent(this,MainActivity.class);
            startActivity(backToLogin);
        }
    }
}
