package com.example.tiltballuniat;
// librerias
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// clase principal
public class LevelSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.level_select);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        CardView level1 = findViewById(R.id.level1);
        CardView level2 = findViewById(R.id.level2);
        CardView level3 = findViewById(R.id.level3);
        CardView level4 = findViewById(R.id.level4);
        CardView level5 = findViewById(R.id.level5);
        CardView level6 = findViewById(R.id.level6);

        // al selecionar lvl 1 te manda a el
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(LevelSelectionActivity.this, Level1.class);
                startActivity(load);
            }
        });
        // al selecionar lvl 2 te manda a el
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(LevelSelectionActivity.this, Level2.class);
                startActivity(load);
            }
        });
        // al selecionar lvl 3 te manda a el
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(LevelSelectionActivity.this, Level3.class);
                startActivity(load);
            }
        });
        // al selecionar lvl 4 te manda a el
        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Proximamente",Toast.LENGTH_LONG).show();
            }
        });
        // al selecionar lvl 5 te manda a el
        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Proximamente",Toast.LENGTH_LONG).show();
            }
        });
        // al selecionar lvl 6 te manda a el
        level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Proximamente",Toast.LENGTH_LONG).show();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}