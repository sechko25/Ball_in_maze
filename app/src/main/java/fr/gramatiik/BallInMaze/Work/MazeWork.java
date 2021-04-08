package fr.gramatiik.BallInMaze.Work;

import fr.gramatiik.BallInMaze.Main;
import fr.gramatiik.BallInMaze.GameComponents.Wall;
import fr.gramatiik.BallInMaze.GameComponents.Ball;
import android.app.Service;
import android.graphics.Point;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.List;

public class MazeWork {
    private Ball mBall = null;
    private List<Wall> mWalls = null;
    private Main mActiv = null;
    private MazeDesign mDesignWork = null;
    private SensorManager mManagement = null;
    private Sensor mAccelerometer = null;
    private Sensor mMagneticSens = null;
    private Sensor mLightSens = null;

    private SensorEventListener mSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent pSensEvent) {
            float x = pSensEvent.values[0];
            float y = pSensEvent.values[1];

            if(mBall != null) {
                RectF impactBox = mBall.putXAndY(x, y);

                for(Wall block : mWalls) {
                    RectF inter = new RectF(block.getRectangle());
                    if(inter.intersect(impactBox)) {
                        switch(block.getType()) {
                            case HOLE:
                                mActiv.showLoseMessage();
                                break;
                            case START:
                                break;
                            case END:
                                mActiv.showWinMessage();
                                break;
                        }
                        break;
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor pSens, int pCorrect) {}
    };

    public void setBall(Ball pBall) {
        this.mBall = pBall;
    }

    public MazeWork(Main pView, MazeDesign mazeDesign) {
        mActiv = pView;
        mDesignWork = mazeDesign;
        mManagement = (SensorManager) mActiv.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
        mAccelerometer = mManagement.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticSens = mManagement.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mLightSens = mManagement.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void reset() {
        mBall.reset();
    }

    public void stop() {
        mManagement.unregisterListener(mSensorEventListener, mAccelerometer);
    }

    public void resume() {
        mManagement.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public List<Wall> constructionMaze(Point screenSize) {
        float sizeX = screenSize.x / (float)20;
        float sizeY = screenSize.y / (float)14;
        mWalls = new ArrayList<>();
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 1, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 2, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 3, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 4, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 5, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 6, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 7, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 8, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 10, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 11, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 12, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 0, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 1, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 1, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 2, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 2, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 3, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 3, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 4, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 1, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 2, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 3, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 4, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 5, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 6, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 7, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 8, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 10, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 4, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 5, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 5, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 6, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 6, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 7, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 7, 7, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 7, 8, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 7, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 7, 10, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 7, 11, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 7, 12, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 7, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 8, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 8, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 8, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 9, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 9, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 9, 10, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 9, 11, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 9, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 10, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 10, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 10, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 11, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 11, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 11, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 12, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 12, 1, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 12, 2, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 12, 3, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 12, 4, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 12, 5, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 12, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 12, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 13, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 13, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 13, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 14, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 14, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 15, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 15, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 16, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 16, 4, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 16, 5, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 16, 6, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 16, 7, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 16, 8, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 16, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 16, 10, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 16, 11, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 16, 12, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 16, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 17, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 17, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 17, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 18, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 18, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 18, 13, sizeX, sizeY));

        mWalls.add(new Wall(Wall.Type.HOLE, 19, 0, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 1, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 2, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 3, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 4, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 5, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 6, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 7, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 8, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 9, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 10, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 11, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 12, sizeX, sizeY));
        mWalls.add(new Wall(Wall.Type.HOLE, 19, 13, sizeX, sizeY));

        Wall b = new Wall(Wall.Type.START, 2, 2, sizeX, sizeY);
        mBall.setStartRectangle(new RectF(b.getRectangle()));
        mWalls.add(b);

        mWalls.add(new Wall(Wall.Type.END, 8, 10, sizeX, sizeY));

        return mWalls;
    }

}
