package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity
{
    private static final String EXTRA_USER_NAME = "UserName";

    private TextView greetings;
    private TextView textViewAdditives;

    private RadioGroup drinks;
    private RadioButton tea;
    private RadioButton coffee;

    private CheckBox sugar;
    private CheckBox milk;
    private CheckBox lemon;

    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private Button makeOrder;

    private String drink;

    private String UserName;

    private ArrayList<String> adds = new ArrayList<>();

    private String drinkType = "";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        initViews();
        setUpUserName();

        drinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id)
            {
                if (id == tea.getId())
                {
                    onUserChoseTea();
                }
                else if (id == coffee.getId())
                {
                    onUserChoseCoffee();
                }
            }
        });

        tea.setChecked(true); // изначально будет выбран чекбокс чая

        makeOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onUserMadeOrder();
            }
        });


    }

    private void onUserMadeOrder()
    {
        if (sugar.isChecked())
        {
            adds.add(sugar.getText().toString());
        }

        if (milk.isChecked())
        {
            adds.add(milk.getText().toString());
        }

        if (tea.isChecked() && lemon.isChecked())
        {
            adds.add(lemon.getText().toString());
        }


        if(tea.isChecked())
        {
            drinkType = spinnerTea.getSelectedItem().toString();
        }
        else if (coffee.isChecked())
        {
            drinkType = spinnerCoffee.getSelectedItem().toString();
        }

        startWindowOrderDetail();
    }


    private void onUserChoseTea()
    {
        drink = getString(R.string.tea);
        String additives = getString(R.string.question_about_additives, drink);
        lemon.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
        spinnerTea.setVisibility(View.VISIBLE);
        textViewAdditives.setText(additives);
    }



    private void onUserChoseCoffee()
    {
        drink = getString(R.string.coffee);
        String additives = getString(R.string.question_about_additives, drink);
        lemon.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
        textViewAdditives.setText(additives);
    }


    private void setUpUserName()
    {
        UserName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greeting = getString(R.string.greeting, UserName);
        greetings.setText(greeting);
    }


    private void initViews()
    {
        greetings = findViewById(R.id.textViewGreetings);
        drinks = findViewById(R.id.radioGroupDrinks);
        tea = findViewById(R.id.radioButtonTea);
        coffee = findViewById(R.id.radioButtonCoffee);
        textViewAdditives = findViewById(R.id.textViewAdditives);
        sugar = findViewById(R.id.CheckBoxSugar);
        milk = findViewById(R.id.CheckBoxMilk);
        lemon = findViewById(R.id.CheckBoxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        makeOrder = findViewById(R.id.makeOrderButton);
    }

    public static Intent newIntent(
            Context context,
            String name,
            String pass
    ) // здесь передача параметров в новое окно передано дочернему окну от мэинАктивити. Такой подход называется фабричным методом

    {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, name);
        intent.putExtra("UserPassword", pass);
        return intent;
    }

    private void startWindowOrderDetail()
    {
        Intent intent = OrderDetailInformation.newIntent(
                this,
                UserName,
                drink,
                adds.toString(),
                drinkType);

        startActivity(intent);
    }
}