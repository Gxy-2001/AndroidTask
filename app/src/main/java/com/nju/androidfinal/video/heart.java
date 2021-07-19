package com.nju.androidfinal.video;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.nju.androidfinal.R;

public class heart extends RelativeLayout {
    private int defoutWidth = 200;//默认控件宽度
    private long mDuration = 3000;//默认动画时间
    //颜色集合 从中获取颜色
    private int[] color = {
            0xFFFF34B3, 0xFF9ACD32, 0xFF9400D3, 0xFFEE9A00,
            0xFFFFB6C1, 0xFFDA70D6, 0xFF8B008B, 0xFF4B0082,
            0xFF483D8B, 0xFF1E90FF, 0xFF00BFFF, 0xFF00FF7F
    };


    public heart(Context context) {
        super(context);
        initFrameLayout();
    }


    public heart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFrameLayout();
    }

    private void initFrameLayout() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(defoutWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ImageView createHeartView() {
        //创建心形的ImageView
        ImageView imageView = new ImageView(getContext());
        LayoutParams params = new LayoutParams(defoutWidth / 2, defoutWidth / 2);
        //设置位置
        //params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        imageView.setLayoutParams(params);
        imageView.setImageResource(R.drawable.ic_red_heart);

        //改变颜色
        imageView.setImageTintList(
                ColorStateList.valueOf(color[(int) (color.length * Math.random())])
        );
        //返回
        return imageView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void start() {
        //获取心形ImageView
        final ImageView heartView = createHeartView();
        //添加到布局中
        addView(heartView);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(createTranslationX(heartView))
                .with(createTranslationY(heartView))
                .with(createScale(heartView))
                .with(createRotation(heartView))
                .with(createAlpha(heartView));
        //执行动画
        animatorSet.start();

        //在动画结束的时候 销毁view
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(heartView);
            }
        });
    }

    private Animator createAlpha(ImageView heartView) {
        //透明
        ObjectAnimator animator = ObjectAnimator.ofFloat(heartView, "alpha", 1, 0.1f);
        animator.setDuration(mDuration);
        //加速
        animator.setInterpolator(new AccelerateInterpolator());
        return animator;
    }

    private Animator createRotation(ImageView heartView) {
        //抖动的动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(heartView, "rotation", 0, (float) (25f * Math.random()));
        animator.setDuration(mDuration);
        animator.setInterpolator(new CycleInterpolator((float) (6 * Math.random())));
        return animator;
    }

    private Animator createScale(ImageView heartView) {
        //放大动画
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(heartView, "scaleX", 1, 1.5f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(heartView, "scaleY", 1, 1.5f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(mDuration);
        //加速
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.play(animatorX).with(animatorY);
        return animatorSet;
    }

    private Animator createTranslationY(ImageView heartView) {
        //纵向平移
        ObjectAnimator animator = ObjectAnimator.ofFloat(heartView, "translationY", 0, -1000);
        animator.setDuration(mDuration);
        //加速
        animator.setInterpolator(new AccelerateInterpolator());
        return animator;
    }

    private Animator createTranslationX(ImageView heartView) {
        //横向平移
        ObjectAnimator animator = ObjectAnimator.ofFloat(heartView, "translationX", 0, (float) (defoutWidth * Math.random() / 4));
        animator.setDuration(mDuration);
        //CycleInterpolator cycles 正弦曲线数
        animator.setInterpolator(new CycleInterpolator((float) (3 * Math.random())));
        return animator;
    }

}