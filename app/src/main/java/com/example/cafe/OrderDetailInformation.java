package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderDetailInformation extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "UserName";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_ARR_ADDITIVES = "addititves";
    private static final String EXTRA_DRINK_TYPE = "drinkType";

    private TextView UserName;
    private TextView viewDrink;
    private TextView viewDrinkType;
    private TextView viewAdditives;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_information);
        initViews();
        setElementsOnViews();
    }


    private void initViews()
    {
        UserName = findViewById(R.id.textViewName);
        viewDrink = findViewById(R.id.textViewDrink);
        viewDrinkType = findViewById(R.id.textViewDrinkType);
        viewAdditives = findViewById(R.id.textViewAdditives);
    }

    private void setElementsOnViews()
    {
        String name;
        String drink;
        String additives;
        String drinkType;

        name = getIntent().getStringExtra(EXTRA_USER_NAME);
        UserName.setText(name);

        drink = getIntent().getStringExtra(EXTRA_DRINK);
        viewDrink.setText(drink);

        additives = getIntent().getStringExtra(EXTRA_ARR_ADDITIVES);
        viewAdditives.setText(additives);

        drinkType = getIntent().getStringExtra(EXTRA_DRINK_TYPE);
        viewDrinkType.setText(drinkType);

    }

    public static Intent newIntent(
            Context context,
            String UserName,
            String drink,
            String adds,
            String drinkType
    )

    {
        Intent intent = new Intent(context, OrderDetailInformation.class);
        intent.putExtra(EXTRA_USER_NAME, UserName);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_ARR_ADDITIVES, adds);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        return intent;
    }
}