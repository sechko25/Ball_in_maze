package fr.gramatiik.BallInMaze.GameComponents;

import android.graphics.RectF;


public class Wall {

    public enum  Type {HOLE, START, END}

    private float mWidth;
    private float mHeight;

    private Type mType = null;
    private RectF mRectangle = null;

    public Type getType() {
        return mType;
    }

    public RectF getRectangle() {
        return mRectangle;
    }

    public Wall(Type pType, int pX, int pY, float width, float height) {
        mWidth = width;
        mHeight = height;
        mType = pType;
        mRectangle = new RectF(pX * mWidth, pY * mHeight, (pX + 1) * mWidth, (pY + 1) * mHeight);
    }
}
