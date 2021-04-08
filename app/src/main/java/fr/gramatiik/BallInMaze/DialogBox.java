package fr.gramatiik.BallInMaze;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DialogBox extends DialogFragment {

    public DialogInterface.OnClickListener mRestart;
    enum TypeDialog {WIN_DIALOG, LOSE_DIALOG}
    private TypeDialog mTypeDialog;

    static public DialogBox newInstance(TypeDialog type, DialogInterface.OnClickListener restart) {
        DialogBox dialogBox = new DialogBox();
        dialogBox.setType(type);
        dialogBox.mRestart = restart;
        return dialogBox;
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        String title = "", message = "";

        switch(mTypeDialog) {
            case WIN_DIALOG:
                title = "Win!!";
                message = "Bro, you completed the game <3<3";
                break;
            case LOSE_DIALOG:
                title = "Lose";
                message = "Bro, the game is tough, try again ^_^";
                break;
        }

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton("Restart", this.mRestart)
                .create();
    }

    private void setType(TypeDialog type) {
        this.mTypeDialog = type;
    }
}
