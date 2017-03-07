package com.example.user.chendemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.example.user.chendemo.util.UtilLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatorActivity extends BasicActivity {


    private ValueAnimator repeatAnimator;

    //moves horizontally back and forth with -200, and 0 to original position, translationY goes vertically
    @OnClick(R.id.animator_trans)
    public void trans(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv, "translationX", 0, 200, -200,0,100); //translationY
        animator.setDuration(2000);
        animator.start();
    }
    //go to 3 times bigger, then back to 1 times bigger and not disappear, scaleY grows vertically,
    @OnClick(R.id.animator_scale)
    public void scale(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv, "scaleX", 0, 3, 1); //scaleY
        animator.setDuration(2000);
        animator.start();
    }

    //in hex decimal, 0xff = not transparent, then RGB, evaluator = custome made for this class
    @OnClick(R.id.animator_color)
    public void color(){
        ObjectAnimator animator = ObjectAnimator.ofInt(tv, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        animator.setDuration(8000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }

    //ofObject can be viewed to understand what an evaluator requires if it is custom made
    @OnClick(R.id.animator_char)
    public void charFAnim(){
        ValueAnimator animator = ValueAnimator.ofObject(new CharEvaluator(),new Character('A'),new Character('Z'));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char text = (char)animation.getAnimatedValue();
                tv.setText(String.valueOf(text));
            }
        });
        animator.setDuration(10000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    //from transparent to none to transparent in two seconds
    @OnClick(R.id.animator_alpha)
    public void alpha(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"alpha",1,0,1);
        animator.setDuration(2000);
        animator.start();
    }

    //how many degree to torque around the center, rotation X = rotate based on x axis only
    //interpolator can also be implemented together
    @OnClick(R.id.animator_rotation)
    public void rotation(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"rotation",0,180,0);//rotationX   rotationY
        animator.setDuration(2000);
        animator.start();
    }



    @OnClick(R.id.animator_bt_start)
    public void startAnimator(){
        repeatAnimator = doAnimatorListener();
//        repeatAnimator.setStartDelay(3000);
        repeatAnimator.start();
    }
    //need to remove all listener/objects on stop for garbage collection
    @OnClick(R.id.animator_bt_cancel)
    public void cancelAnimator(){
        repeatAnimator.removeAllListeners();
        repeatAnimator.removeAllUpdateListeners();
        repeatAnimator.cancel();
    }

    @BindView(R.id.animator_tv)
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.bind(this);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastShort("Click");
            }
        });
    }

    private ValueAnimator doAnimatorListener(){
        ValueAnimator animator = ValueAnimator.ofInt(0,400);   //system divides animation duration  into 400 time slots, each slot has 0,1,2...400

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int)animation.getAnimatedValue();   //based on this, update the position of text view
                tv.layout(tv.getLeft(),curValue,tv.getRight(),curValue+tv.getHeight());
            }
        });
        //when animation start/stop/repeat
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                UtilLog.logD("Chen","animation start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                UtilLog.logD("Chen","animation end");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                UtilLog.logD("Chen","animation cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                UtilLog.logD("Chen","animation repeat");
            }
        });
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new BounceInterpolator()); //
        animator.setDuration(1000);
        return animator;
    }

    private class CharEvaluator implements TypeEvaluator<Character> {
        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt  = (int)startValue;
            int endInt = (int)endValue;
            int curInt = (int)(startInt + fraction *(endInt - startInt));
            char result = (char)curInt;
            return result;
        }
    }

    public class ArgbEvaluator implements TypeEvaluator {
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startInt = (Integer) startValue;
            int startA = (startInt >> 24);
            int startR = (startInt >> 16) & 0xff;
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = (Integer) endValue;
            int endA = (endInt >> 24);
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;

            return (startA + (int)(fraction * (endA - startA))) << 24 |
                    (startR + (int)(fraction * (endR - startR))) << 16 |
                    (startG + (int)(fraction * (endG - startG))) << 8 |
                    (startB + (int)(fraction * (endB - startB)));
        }
    }
}
