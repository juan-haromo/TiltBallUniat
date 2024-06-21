package com.example.tiltballuniat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LevelSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.level_select);

        CardView level1 = findViewById(R.id.level1);
        CardView level2 = findViewById(R.id.level2);
        CardView level3 = findViewById(R.id.level3);
        CardView level4 = findViewById(R.id.level4);
        CardView level5 = findViewById(R.id.level5);
        CardView level6 = findViewById(R.id.level6);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(LevelSelectionActivity.this, Level1.class);
                startActivity(load);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(LevelSelectionActivity.this, Level2.class);
                startActivity(load);
            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(LevelSelectionActivity.this, Level3.class);
                startActivity(load);
            }
        });
        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nivel bloqueado",Toast.LENGTH_LONG).show();
            }
        });
        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nivel bloqueado",Toast.LENGTH_LONG).show();
            }
        });
        level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nivel bloqueado",Toast.LENGTH_LONG).show();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}