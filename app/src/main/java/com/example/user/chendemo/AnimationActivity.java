package com.example.user.chendemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends BasicActivity {
    private Animation alphaAnimation;
    private Animation scaleAnimation;
    private Animation rotateAnimation;
    private Animation transAnimation;
    private Animation setAnimation;

    @BindView(R.id.animation_tv)
    TextView tv;

    @OnClick(R.id.animation_alpha)
    public void clickAlpha(){
        tv.startAnimation(alphaAnimation);

    }
    @OnClick(R.id.animation_rotate)
    public void clickRotate(){
        tv.startAnimation(rotateAnimation);
    }
    @OnClick(R.id.animation_scale)
    public void clickScale(){
        tv.startAnimation(scaleAnimation);

    }
    @OnClick(R.id.animation_trans)
    public void clickTrans(){
        tv.startAnimation(transAnimation);
    }
    @OnClick(R.id.animation_set)
    public void clickSet(){
        tv.startAnimation(setAnimation);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        initialAnimation();
    }

    private void initialAnimation(){
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        scaleAnimation = AnimationUtils.loadAnimation(this,R.anim.anim_scale);
        rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        transAnimation = AnimationUtils.loadAnimation(this,R.anim.anim_trans);
        setAnimation = AnimationUtils.loadAnimation(this,R.anim.anim_set);
    }
}
