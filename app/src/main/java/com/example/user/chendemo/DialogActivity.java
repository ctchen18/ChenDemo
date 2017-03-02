package com.example.user.chendemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.user.chendemo.dialog.CustomDialog;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/2/13.
 */

public class DialogActivity extends BasicActivity {

    private int checkedID;
    private final int dialog =12345;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case dialog:
                    Bundle bundle = msg.getData();
                    String temp = bundle.getString("msg");
                    toastShort("Dialog Message"+temp);
                    break;
                default:
            }

            super.handleMessage(msg);
        }
    };

    @BindView(R.id.rbg) RadioGroup radioGroup;
    @OnClick(R.id.dialog_ok)
    public void onClick(){
        switch ((checkedID)){
            case R.id.rb1:
                normalDialog();
                break;
            case R.id.rb2:
                listDialog();
                break;
            case R.id.rb3:
                singleChoiceDialog();
                break;
            case R.id.rb4:
                multiChoiceDialog();
                break;
            case R.id.rb5:
                waitingDialog();
                break;
            case R.id.rb6:
                progressDialog();
                break;
            case R.id.rb7:
                inputDialog();
                break;
            case R.id.rb8:
                customDialog();
                break;
            default:
        }
    }
    private void customDialog(){
        CustomDialog dialog = new CustomDialog(this, new CustomDialog.ICustomDialogEventListener() {
            @Override
            public void onClickListener() {
                toastShort("OK clicked");
                Intent intent = new Intent();
                intent.putExtra("message2","Dialog");
                setResult(RESULT_OK,intent);
                onBackPressed();

            }
        });
        //dialog.setTitle("SystemTitle"); // try not to use it, confusing with style/xml
        dialog.show();

    }

    private void normalDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("AlertTitle");
        builder.setMessage("This is a normal dialog");
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                toastShort("You clicked Yes");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You clicked Cancel");
            }
        });

        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You clicked Neutral");
            }
        });

        builder.show();   // at most positive, negative neutral buttons can be set, total of 3


    }
    private void listDialog(){
        final String[] items = {"item1","item2","item3","item4"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("I'm list dialog");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, new DialogInterface.OnClickListener(){
           @Override
            public void onClick(DialogInterface dialog, int which){
               toastShort("You clicked"+items[which]);
           }
        });
        builder.show();
    }
    private int choice = 0;
    private void singleChoiceDialog(){
        final String[] items = {"item1","item2","item3","item4"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("I'm single choice dialog");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, choice, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                choice = which;
            }
        });
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                toastShort("You clicked" + choice);
            }
        });
        builder.show();
    }

    private ArrayList<Integer> choices = new ArrayList<>();
    private void multiChoiceDialog(){
        final String[] items = {"item1","item2","item3","item4"};
        final boolean initChoiceSets[] = {false,false,false,false};
        choices.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("I'm single choice dialog");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(items,initChoiceSets, new DialogInterface.OnMultiChoiceClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which,boolean isChecked){
                if(isChecked){
                    choices.add(which);
                }
                else{
                    choices.remove(which);
                }
            }
        });

        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                int size = choices.size();
                String str = "";
                for (int i=0;i<size;i++){
                    str+= items[choices.get(i)]+" ";
                }
                toastShort("You chose" +str);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort("You clicked Cancel");
            }
        });
        builder.show();
    }
    ProgressDialog waitingDialog; //declared outside for dismiss method
    private void waitingDialog(){
        waitingDialog = new ProgressDialog(this);
        waitingDialog.setTitle("I'm waiting dialog");
        waitingDialog.setMessage("Waiting");
        waitingDialog.setCancelable(true); // if false, this become not cancellable
        waitingDialog.show();
        waitingDialog.setOnDismissListener(new DialogInterface.OnDismissListener(){
            public void onDismiss(DialogInterface dialog){
                toastShort("Dialog was cancelled");
                //stop the download thread
            }
        });
    }

    private void progressDialog(){
        final int max = 100;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("Downloading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(max);
        progressDialog.show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress=0;
                while(progress<50){
                    try{
                        Thread.sleep(100);
                        progress++;
                        progressDialog.setProgress(progress);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                progressDialog.cancel();
                //toastShort("Download success");  // not allowed, toast restricted in threads
                Bundle bundle = new Bundle();
                bundle.putString("msg","Download completed");
                Message msg = Message.obtain();
                msg.what = dialog;
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    private void inputDialog(){
        final EditText editText=new EditText(this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(this);
        inputDialog.setTitle("I'm input dialog");
        inputDialog.setView(editText);
        inputDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toastShort(editText.getText().toString());
            }
        });
        inputDialog.setNegativeButton("Cancel",null).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                toastShort("You checked the radioButton"+checkedId);
                checkedID=checkedId;
            }
        });

    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();          //has to be last line
    }
}
