package fr.gramatiik.BallInMaze.Work;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import fr.gramatiik.BallInMaze.GameComponents.Wall;
import fr.gramatiik.BallInMaze.GameComponents.Ball;

public class MazeDesign extends SurfaceView implements SurfaceHolder.Callback {
    Ball mBall;
    final SurfaceHolder mSurfaceHolder;
    DrawTread mTread;
    private int mColor = Color.WHITE;
    private List<Wall> mWall = null;

    public void setWall(List<Wall> pWall) {
        this.mWall = pWall;
    }

    public void setBall(Ball pBall) {
        this.mBall = pBall;
    }

    Paint mPaint;

    public MazeDesign(Context pContext) {
        super(pContext);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mTread = new DrawTread();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        mBall = new Ball(20);
    }

    @Override
    protected void onDraw(Canvas pCanvas) {
        pCanvas.drawColor(mColor);
        if(mWall != null) {
            for(Wall b : mWall) {
                switch(b.getType()) {
                    case START:
                        mPaint.setColor(Color.WHITE);
                        break;
                    case END:
                        mPaint.setColor(Color.BLUE);
                        break;
                    case HOLE:
                        mPaint.setColor(Color.BLACK);
                        break;
                }
                pCanvas.drawRect(b.getRectangle(), mPaint);
            }
        }
        if(mBall != null) {
            mPaint.setColor(mBall.getColor());
            pCanvas.drawCircle(mBall.getX(), mBall.getY(), mBall.getRadius(), mPaint);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder pHolder, int pFormat, int pWidth, int pHeight) {}

    @Override
    public void surfaceCreated(SurfaceHolder pHolder) {
        mTread.keepDrawing = true;
        mTread.start();
        if(mBall != null ) {
            this.mBall.setHeight(getHeight());
            this.mBall.setWidth(getWidth());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder pHolder) {
        mTread.keepDrawing = false;
        boolean retry = true;
        while (retry) {
            try {
                mTread.join();
                retry = false;
            } catch (InterruptedException e) {
                Log.e("MAZE_VIEW", e.getMessage());
            }
        }

    }

    private class DrawTread extends Thread {
        boolean keepDrawing = true;

        @SuppressLint("WrongCall")
        @Override
        public void run() {
            Canvas canvas;
            while (keepDrawing) {
                canvas = null;

                try {
                    canvas = mSurfaceHolder.lockCanvas();
                    synchronized (mSurfaceHolder) {
                        onDraw(canvas);
                    }
                }
                finally {
                    if (canvas != null)
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Log.e("MAZE_VIEW", e.getMessage());
                }
            }
        }
    }
}

