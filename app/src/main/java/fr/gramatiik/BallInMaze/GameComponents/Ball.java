package fr.gramatiik.BallInMaze.GameComponents;

import android.graphics.Color;
import android.graphics.RectF;

public class Ball {

    private static final float MAX_SPEED = 20.0f;
    private static final float COMPENSATOR = 8.0f;
    private static final float RETURN = 1.75f;

    private int mRadius = 30;
    private int mColor = Color.RED;

    private RectF mStartRectangle = null;
    private RectF mRectangle = null;

    private float mX;
    private float mY;

    private float mSpeedX = 0;
    private float mSpeedY = 0;

    private int mHeight = -1;
    private int mWidth = -1;

    public int getRadius() {
        return mRadius;
    }
    public int getColor() {
        return mColor;
    }

    public void setStartRectangle(RectF pStartRectangle) {
        this.mStartRectangle = pStartRectangle;
        this.mX = pStartRectangle.left + mRadius;
        this.mY = pStartRectangle.top + mRadius;
    }

    public float getX() {
        return mX;
    }
    public void setPosX(float pPosX) {
        mX = pPosX;

        if(mX < mRadius) {
            mX = mRadius;
            mSpeedY = -mSpeedY / RETURN;
        } else if(mX > mWidth - mRadius) {
            mX = mWidth - mRadius;
            mSpeedY = -mSpeedY / RETURN;
        }
    }

    public float getY() {
        return mY;
    }
    public void setPosY(float pPosY) {
        mY = pPosY;
        if(mY < mRadius) {
            mY = mRadius;
            mSpeedX = -mSpeedX / RETURN;
        } else if(mY > mHeight - mRadius) {
            mY = mHeight - mRadius;
            mSpeedX = -mSpeedX / RETURN;
        }
    }

    public void setHeight(int pHeight) {
        this.mHeight = pHeight;
    }
    public void setWidth(int pWidth) {
        this.mWidth = pWidth;
    }

    public Ball(int radius) {
        mRadius = radius;
        mRectangle = new RectF();
    }

    public RectF putXAndY(float pX, float pY) {
        mSpeedX += pX / COMPENSATOR;
        if(mSpeedX > MAX_SPEED)
            mSpeedX = MAX_SPEED;
        if(mSpeedX < -MAX_SPEED)
            mSpeedX = -MAX_SPEED;

        mSpeedY += pY / COMPENSATOR;
        if(mSpeedY > MAX_SPEED)
            mSpeedY = MAX_SPEED;
        if(mSpeedY < -MAX_SPEED)
            mSpeedY = -MAX_SPEED;

        setPosX(mX + mSpeedY);
        setPosY(mY + mSpeedX);

        mRectangle.set(mX - mRadius, mY - mRadius, mX + mRadius, mY + mRadius);

        return mRectangle;
    }

    public void reset() {
        mSpeedX = 0;
        mSpeedY = 0;
        this.mX = mStartRectangle.left + mRadius;
        this.mY = mStartRectangle.top + mRadius;
    }
}
