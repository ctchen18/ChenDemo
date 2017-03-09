package com.example.user.chendemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.user.chendemo.MainActivity;
import com.example.user.chendemo.R;
import com.example.user.chendemo.dialog.CustomDialog;
import com.example.user.chendemo.util.UtilLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class CustomDialogForQuizz extends Dialog {

    private int checkedID;

    @BindView(R.id.quizz_radio_group)
    RadioGroup rg;



    @OnClick(R.id.custom_dialog_cancel)
    public void clickCancel(){
        listener.onClickListener();
        listener.msgChannel("Cancel");
        dismiss();
    }



    @OnClick(R.id.custom_dialog_ok)
    public void clickOK(){
        listener.onClickListener();

        switch ((checkedID)) {
            case R.id.quizz_button1:
                UtilLog.logD("Test1","Test");
                listener.msgChannel("Center");
                break;
            case R.id.quizz_button2:
                UtilLog.logD("Test2","Test");
                listener.msgChannel("Right");
                break;
            default:
                dismiss();
        }
        }

    private ICustomDialogEventListener listener;

    public interface ICustomDialogEventListener{
        void msgChannel(String activityName);
        void onClickListener();
    }
    public void msgChannel(){

    }

    public CustomDialogForQuizz(Context context, ICustomDialogEventListener listener) {
        super(context, R.style.dialog);
        this.listener=listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog_for_quizz);
        ButterKnife.bind(this);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                UtilLog.logD("Text","You checked the radioButton" + checkedId);
                checkedID=checkedId;
            }
        });
    }
}
