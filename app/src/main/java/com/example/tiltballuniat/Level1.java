package com.example.tiltballuniat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Level1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.level1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button_menu = findViewById(R.id.button_show_menu);
        RelativeLayout layout_menu = findViewById(R.id.menu);
        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_menu.setVisibility(View.VISIBLE);
                button_menu.setVisibility(View.GONE);
            }
        });
        Button button_continue = findViewById(R.id.button_continue);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_menu.setVisibility(View.GONE);
                button_menu.setVisibility(View.VISIBLE);
            }
        });
        Button button_restart = findViewById(R.id.button_restart);
        button_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        Button button_home = findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(Level1.this, MainActivity.class);
                startActivity(load);
            }
        });
    }

}