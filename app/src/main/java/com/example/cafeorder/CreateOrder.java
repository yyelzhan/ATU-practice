package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrder extends AppCompatActivity {
    private TextView textViewHello;
    private TextView textViewAdd;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffe;


    private String drink;
    private String name;
    private String password;
    private StringBuilder builderAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.defult_password);
        }
        drink = getString(R.string.tea);
        textViewHello = findViewById(R.id.textViewHello);
        String hello = String.format(getString(R.string.hello_user), name);
        textViewHello.setText(hello);
        textViewAdd = findViewById(R.id.textViewAdd);
        String additions = String.format(getString(R.string.Add), drink);
        textViewAdd.setText(additions);


        checkBoxMilk = findViewById(R.id.checkboxMilk);
        checkBoxLemon = findViewById(R.id.checkboxLemon);
        checkBoxSugar = findViewById(R.id.checkboxSugar);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffe = findViewById(R.id.spinnerCoffe);
         builderAdd = new StringBuilder();
    }

    public void onClikcChangeDrink(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        if (id == R.id.radioButtonTea) {
            drink = getString(R.string.tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffe.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        } else if (id == R.id.radioButtonCoffe) {
            drink = getString(R.string.Coffe);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffe.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }
        String additions = String.format(getString(R.string.Add), drink);
        textViewAdd.setText(additions);

    }

    public void onClikcSendOrder(View view) {
        builderAdd.setLength(0);
        if (checkBoxMilk.isChecked()) {
            builderAdd.append(getString(R.string.Milk)).append(" ");
        }
        if (checkBoxSugar.isChecked()) {
            builderAdd.append(getString(R.string.Sugar)).append(" ");
        }
        if (checkBoxLemon.isChecked() && drink.equals(getString(R.string.tea))) {
            builderAdd.append(getString(R.string.Limon)).append(" ");
        }

        String optionOFDrink = "";
        if (drink.equals(getString(R.string.tea))) {
            optionOFDrink = spinnerTea.getSelectedItem().toString();
        } else {
            optionOFDrink = spinnerCoffe.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order), name, password, drink, optionOFDrink);
        String additions;
        if (builderAdd.length() > 0) {
            additions = getString(R.string.need_add) + builderAdd.toString();
        } else {
            additions = "";
        }
        String fullOrder = order + additions;
        Intent intent = new Intent(this, OrderdetailActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);
    }

}
