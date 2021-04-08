package fr.gramatiik.BallInMaze;

import android.graphics.Point;
import android.os.Bundle;
import java.util.List;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

import fr.gramatiik.BallInMaze.Work.MazeWork;
import fr.gramatiik.BallInMaze.Work.MazeDesign;
import fr.gramatiik.BallInMaze.GameComponents.Wall;
import fr.gramatiik.BallInMaze.GameComponents.Ball;

public class Main extends AppCompatActivity {

    private MazeWork mWork = null;

    @Override
    public void onCreate(Bundle saveState) {
        super.onCreate(saveState);

        MazeDesign mDesign = new MazeDesign(this);
        setContentView(mDesign);

        mWork = new MazeWork(this, mDesign);

        Ball ball = new Ball(20);
        mDesign.setBall(ball);
        mWork.setBall(ball);

        Point screenSizes = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSizes);

        List<Wall> mList = mWork.constructionMaze(screenSizes);
        mDesign.setWall(mList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWork.resume();
    }

    public void showWinMessage() {
        mWork.stop();
        DialogBox.newInstance(DialogBox.TypeDialog.WIN_DIALOG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mWork.reset();
                mWork.resume();
            }
        }).show(getFragmentManager(), "GameDialog");
    }

    public void showLoseMessage() {
        mWork.stop();
        DialogBox.newInstance(DialogBox.TypeDialog.LOSE_DIALOG, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mWork.reset();
                mWork.resume();
            }
        }).show(getFragmentManager(), "GameDialog");
    }

}

