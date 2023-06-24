package com.qfedu.firstapp.dialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qfedu.firstapp.R;

public class LoadingDialog extends Dialog {
    private ObjectAnimator objectAnimator;

    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    //生命周期
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
    }

    @Override
    public void show() {
        super.show();
        //使用动画的技术手段，让图片不断的旋转起来，变成动态的
        startAnimator();
        //监听对话框的关闭事件
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //当对话框要关闭时，停止动画
                stopAnimator();
            }
        });
    }

    /**
     * 启动执行动画，让图片转起来
     */
    private void startAnimator() {
        //设置，用户点击对话框之外的区域，对话框不会消失
        setCanceledOnTouchOutside(false);
        ImageView loading = findViewById(R.id.loading);
        objectAnimator = ObjectAnimator.ofFloat(loading, "ratation", 0.0f, 360.0f);
        objectAnimator.setDuration(2000);//2000毫秒 2秒
        objectAnimator.setRepeatCount(Animation.INFINITE);//无限循环
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.start();//启动动画
    }

    /**
     * 该方法用于将动画进行停止
     */
    private void stopAnimator() {
        objectAnimator.end();//设置动画停止
        objectAnimator = null;
    }
}
