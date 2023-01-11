package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private EditText userName;
    private EditText userPass;
    private Button entryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        entryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name = userName.getText().toString().trim();
                String pass = userPass.getText().toString().trim();

                if (name.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, getString(R.string.alert), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    startNewWindow(name, pass);
                }


            }
        });

    }


    private void startNewWindow(String name, String pass)
    {
        Intent intent = MakeOrderActivity.newIntent(this, name, pass);
        startActivity(intent);
    }


    private void initViews()
    {
        userName = findViewById(R.id.EditTextUserName);
        userPass = findViewById(R.id.EditTextPassword);
        entryButton = findViewById(R.id.buttonEnter);
    }
}