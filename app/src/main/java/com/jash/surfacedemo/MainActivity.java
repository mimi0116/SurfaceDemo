package com.jash.surfacedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class MainActivity extends ActionBarActivity implements SurfaceHolder.Callback {
    private SurfaceView surface;
    private SurfaceHolder surfaceHolder;
    private int width;
    private int height;
    private boolean isRun;
    private Bitmap bitmap;

    //    private SurfaceView surface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surface = ((SurfaceView) findViewById(R.id.surfaceView));
        surface.setZOrderOnTop(true);
        SurfaceHolder holder = surface.getHolder();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a7e5b1b1ccc3c3f7ff02);
        holder.addCallback(this);
    }

    /**
     * 创建时调用
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.surfaceHolder = holder;
        isRun = true;
        new Thread(){
            @Override
            public void run() {
                while (isRun){
                    Canvas canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        canvas.drawColor(Color.RED);
                        canvas.drawBitmap(bitmap, 0, 0, new Paint());
//                        Rect src = new Rect(0, 0, 50, 50);
//                        Rect dst = new Rect(100, 100, 200, 150);
//                        canvas.drawBitmap(bitmap, src, dst, new Paint());
                        Matrix matrix = new Matrix();
                        matrix.postTranslate(50, 50);
                        matrix.postRotate(45, 50, 50);
                        canvas.drawBitmap(bitmap, matrix, new Paint());
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }.start();
    }


    /**
     * 改变时调用
     * @param holder
     * @param format 像素显示
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 销毁时调用
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRun = false;
    }
}
