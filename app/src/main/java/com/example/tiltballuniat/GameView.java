//package com.example.tiltballuniat;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//
//import org.dyn4j.dynamics.Body;
//import org.dyn4j.world.World;
//import org.dyn4j.geometry.Circle;
//import org.dyn4j.geometry.MassType;
//import org.dyn4j.geometry.Vector2;
//
//public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
//    private Thread thread;
//    private boolean running = false;
//    private SurfaceHolder holder;
//    private Paint paint;
//
//    private World world;
//    private Body circleBody;
//    private Body obstacleBody;
//
//    public GameView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        holder = getHolder();
//        holder.addCallback(this);
//
//        paint = new Paint();
//        paint.setColor(Color.RED);
//
//        world = new World();
//        world.setGravity(new Vector2(0, 0)); // Sin gravedad para controlar con acelerómetro
//
//        Circle circleShape = new Circle(1.0); // Círculo de radio 1
//        circleBody = new Body();
//        circleBody.addFixture(circleShape);
//        circleBody.setMass(MassType.NORMAL);
//        circleBody.translate(0, 0); // Posición inicial en el centro
//        world.addBody(circleBody);
//
//        // Crear un obstáculo fijo
//        Circle obstacleShape = new Circle(1.0);
//        obstacleBody = new Body();
//        obstacleBody.addFixture(obstacleShape);
//        obstacleBody.setMass(MassType.INFINITE);
//        obstacleBody.translate(5, 5); // Posición inicial del obstáculo
//        world.addBody(obstacleBody);
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        if (thread == null) {
//            thread = new Thread(this);
//            running = true;
//            thread.start();
//        }
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        running = false;
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run() {
//        while (running) {
//            if (!holder.getSurface().isValid()) {
//                continue;
//            }
//
//            Canvas canvas = holder.lockCanvas();
//            canvas.drawColor(Color.WHITE); // Fondo blanco
//
//            // Actualizar el mundo físico
//            world.update(1.0 / 60.0);
//
//            // Dibujar el círculo
//            Vector2 position = circleBody.getWorldCenter();
//            float x = (float) position.x;
//            float y = (float) position.y;
//            canvas.drawCircle(x * 50, y * 50, 50, paint); // Escalar y dibujar
//
//            // Dibujar el obstáculo
//            position = obstacleBody.getWorldCenter();
//            x = (float) position.x;
//            y = (float) position.y;
//            canvas.drawCircle(x * 50, y * 50, 50, paint); // Escalar y dibujar
//
//            holder.unlockCanvasAndPost(canvas);
//        }
//    }
//
//    public void updateCircleVelocity(float ax, float ay) {
//        circleBody.setLinearVelocity(new Vector2(ax, ay));
//    }
//}
