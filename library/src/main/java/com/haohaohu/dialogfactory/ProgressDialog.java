package com.haohaohu.dialogfactory;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * 圆形进度dialog
 *
 * @author haohao(ronghao3508@gmail.com) on 2017/11/20 上午 11:10
 * @version v1.0
 */
public class ProgressDialog extends Dialog {
    private Animation anim;
    private ImageView progressLoading;
    private int width;
    private int height;
    private View view;

    public static ProgressDialog.Builder newBuilder(Context val) {
        return new ProgressDialog.Builder(val);
    }

    private ProgressDialog(Builder builder) {
        super(builder.context, R.style.dialog_pay);
        width = builder.width;
        height = builder.height;
        initView();
    }

    private void initView() {
        view = View.inflate(getContext(), R.layout.dialog_progress, null);
        setContentView(view);

        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = width;
        lp.height = height;
        window.setAttributes(lp);

        anim = AnimationUtils.loadAnimation(getContext(), R.anim.progress_rotate);
        anim.setInterpolator(new LinearInterpolator());
        progressLoading = (ImageView) view.findViewById(R.id.dialog_progress_loading);
    }

    @Override
    public void show() {
        super.show();
        if (progressLoading == null) {
            progressLoading = (ImageView) view.findViewById(R.id.dialog_progress_loading);
        }
        if (anim == null) {
            anim = AnimationUtils.loadAnimation(getContext(), R.anim.progress_rotate);
            anim.setInterpolator(new LinearInterpolator());
        }
        progressLoading.startAnimation(anim);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (anim != null) {
            progressLoading.clearAnimation();
        }
    }

    public static final class Builder {
        private Context context;
        private int width;
        private int height;

        private Builder(Context val) {
            context = val;
            width = SizeUtil.dp2px(val, 70);
            height = SizeUtil.dp2px(val, 70);
        }

        public ProgressDialog build() {
            return new ProgressDialog(this);
        }
    }
}
