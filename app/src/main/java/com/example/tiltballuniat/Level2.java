package com.example.tiltballuniat;
// librerias
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.widget.ImageView;

// creamos la clase principal
public class Level2 extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ImageView player;
    private ImageView goal;
    private ImageView obstacle1;
    private ImageView obstacle2;
    private RelativeLayout menu;

    private float xAcceleration;
    private float yAcceleration;

    private boolean isPlaying = true;

    Button button_menu;
    Button button_continue;
    Button button_restart;
    Button button_home;
    Button button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.level2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar los elementos de la UI
        player = findViewById(R.id.player);
        goal = findViewById(R.id.goal);
        obstacle1 = findViewById(R.id.obstacle1);
        obstacle2 = findViewById(R.id.obstacle2);
        menu = findViewById(R.id.menu);
        button_menu = findViewById(R.id.button_show_menu);
        button_continue = findViewById(R.id.button_continue);
        button_restart = findViewById(R.id.button_restart);
        button_home = findViewById(R.id.button_home);
        button_next = findViewById(R.id.button_next);

        // Configurar el sensor de acelerómetro
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // uso del boton menu
        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setVisibility(View.VISIBLE);
                button_menu.setVisibility(View.GONE);
                isPlaying = false;
            }
        });

        // uso de boton continuar
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setVisibility(View.GONE);
                button_menu.setVisibility(View.VISIBLE);
                isPlaying = true;
            }
        });
        // uso de boton restart
        button_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        // uso de boton home
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(Level2.this, MainActivity.class);
                startActivity(load);
            }
        });
        // uso de boton next
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent load = new Intent(Level2.this,Level3.class);
                startActivity(load);
            }
        });
    }

    // continua la aplicacion cuando regresas y vuelve a tomar el sensor
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    // pausa la aplicacion cuando te sales y libera el sensor
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    // revisa si cambio algo en el sensor
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && isPlaying) {
            xAcceleration = event.values[0];
            yAcceleration = event.values[1];

            updatePlayerPosition();
            checkCollisions();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No se necesita implementar nada aquí para este ejemplo
    }

    // actualiza la posicion del jugador
    private void updatePlayerPosition() {
        float x = player.getX() - xAcceleration * 2;
        float y = player.getY() + yAcceleration * 2;

        // Asegurarse de que el jugador no salga de los límites de la pantalla
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > ((View) player.getParent()).getWidth() - player.getWidth()) x = ((View) player.getParent()).getWidth() - player.getWidth();
        if (y > ((View) player.getParent()).getHeight() - player.getHeight()) y = ((View) player.getParent()).getHeight() - player.getHeight();

        player.setX(x);
        player.setY(y);
    }

    // revisa las colisiones
    private void checkCollisions() {
        // Rectángulos de colisión para el jugador, meta y obstáculos
        Rect playerRect = new Rect((int) player.getX(), (int) player.getY(), (int) player.getX() + player.getWidth(), (int) player.getY() + player.getHeight());
        Rect goalRect = new Rect((int) goal.getX(), (int) goal.getY(), (int) goal.getX() + goal.getWidth(), (int) goal.getY() + goal.getHeight());
        Rect obstacle1Rect = new Rect((int) obstacle1.getX(), (int) obstacle1.getY(), (int) obstacle1.getX() + obstacle1.getWidth(), (int) obstacle1.getY() + obstacle1.getHeight());
        Rect obstacle2Rect = new Rect((int) obstacle2.getX(), (int) obstacle2.getY(), (int) obstacle2.getX() + obstacle2.getWidth(), (int) obstacle2.getY() + obstacle2.getHeight());

        // Verificar colisión con el obstáculo1
        if (Rect.intersects(playerRect, obstacle1Rect)) {
            // Detener el movimiento del jugador
            player.setX(player.getX() + xAcceleration * 2);
            player.setY(player.getY() - yAcceleration * 2);
        }

        // Verificar colisión con el obstáculo2
        if (Rect.intersects(playerRect, obstacle2Rect)) {
            // Detener el movimiento del jugador
            player.setX(player.getX() + xAcceleration * 2);
            player.setY(player.getY() - yAcceleration * 2);
        }

        // Verificar colisión con la meta
        if (Rect.intersects(playerRect, goalRect)) {
            // Mostrar el menú
            // Mostrar el menú
            button_continue.setVisibility(View.GONE);
            button_next.setVisibility(View.VISIBLE);
            menu.setVisibility(View.VISIBLE);
            button_menu.setVisibility(View.GONE);
            isPlaying = false;
        }
    }
}