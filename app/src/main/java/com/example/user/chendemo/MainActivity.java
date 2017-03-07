package com.example.user.chendemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;

import com.example.user.chendemo.bean.Book;
import com.example.user.chendemo.util.UtilLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BasicActivity implements View.OnTouchListener{
    private ImageButton btn1;
    //private ImageButton btn2;
    private ImageButton btn3;

    private GestureDetector mGestureDetector;

    @BindView(R.id.frame_layout)
        FrameLayout fl;
    @OnClick(R.id.btn2)
    public void button2Click(){
        Intent intent= new Intent(this,DialogActivity.class);
        //toActivity(DialogActivity.class);
        startActivityForResult(intent,2);
    }
    @OnClick(R.id.launch_button)
    public void launchButtonClick(){
        Intent intent = new Intent(this,launch_mode_a.class);
        startActivity(intent);

    }
    @OnClick(R.id.main_timer_bt)
    public void timerCLick(){
        Intent intent = new Intent(this,TimerActivity.class);
                startActivity(intent);
    }
    @OnClick(R.id.main_animation_bt)
    public void animationCLick(){
        Intent intent = new Intent(this,AnimationActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.main_animator)
    public void animatorCLick(){
        Intent intent = new Intent(this,AnimatorActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialView();
        initialListener();
        ButterKnife.bind(this);   //how to use butterknife
        mGestureDetector = new GestureDetector(this,new simpleGestureListener());
        fl.setOnTouchListener(this);
    }
    private void initialView(){
        btn1 = (ImageButton) findViewById(R.id.btn1);
        btn3 = (ImageButton) findViewById(R.id.btn3);

    }

/*    private void View.OnClickListener btnListener1(){

    }*/

    private void initialListener(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"Button 1 Clicked", Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(v.getContext(), ViewPagerActivity.class);
                Intent intent = new Intent(v.getContext(), ViewPagerActivity.class);
                //passing information from one intent to another
                intent.putExtra("key","value");
                Bundle bundle = new Bundle();
                bundle.putInt("Integer",12345);
                intent.putExtras(bundle);
                Book book = new Book();
                book.setName("Android");
                book.setAuthor("Chen");
                bundle.putSerializable("book",book);
                intent.putExtras(bundle);

                //startActivity(intent);
                startActivityForResult(intent,1);  //request code 1,
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toActivity(ListViewActivity.class);
                Intent intent = new Intent(v.getContext(),ListViewActivity.class);
                //startActivity(intent);
                startActivityForResult(intent,3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                    toastShort("From ViewPager");
                    String msg = data.getStringExtra("message");
                    toastShort("Message: "+msg);
                    break;
            case 2:
                    toastShort("From Dialog");
                    msg = data.getStringExtra("message2");
                    toastShort(msg);
                    break;
            case 3:
                    toastShort("From List View");
                    msg = data.getStringExtra("message3");
                    toastShort(msg);
                    break;
        }

    }

    public void onBtnTwoCLick(View v){
        //Toast.makeText(this,"Button 2 Clicked",Toast.LENGTH_LONG).show();
        toastLong("Button 2 clicked");
        UtilLog.logD("testD","Toast");
        //Log.d("testD","Toast");

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);  //returns true/false on whether button has been touched
        //return false;
    }

    private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener{
            public boolean onDown(MotionEvent e){
                UtilLog.logD("MyGesture","OnDown");
                toastShort("On Down");
                return true;
            }

        public void onShowPress(MotionEvent e){
            UtilLog.logD("MyGesture","OnDown");
            toastShort("On ShowPress");
            //return false;
        }
        public void onLongPress(MotionEvent e){
            UtilLog.logD("MyGesture","OnDown");
            toastShort("On LongPress");
            //return false;
        }
        public boolean onSingleTapUp(MotionEvent e){
            toastShort("On SingleTapUp");
            return true;
        }
        public boolean onSingleTapConfirmed(MotionEvent e){
            toastShort("On SingleTapConfirmed");
            return true;
        }
        //detect how much is scrolled, and direction, add textView with animation to show new text view based on scroll direction
        //use the top left button to make text view disappear, or use scroll backwards to make the text view animation disappear.
        //use distance calculations can get the scroll directions
        public boolean onScroll(MotionEvent e1, MotionEvent e2,float distanceX, float distanceY){
            UtilLog.logD("My Gesture","On Scroll: "+(e2.getX()-e1.getX()+" "+distanceX));
            toastShort("On Scroll");
            return true;
        }
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
            toastShort("On Fling");
            return true;
        }

        public boolean onDoubleTap(MotionEvent e){
            toastShort("On Double Tap");
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent e){
            toastShort("On Double Tap Event");
            return true;
        }

    }
}
