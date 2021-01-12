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
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 带文字的圆形进度dialog
 *
 * @author haohao(ronghao3508 @ gmail.com) on 2017/11/20 上午 11:10
 * @version v1.0
 */
public class ProgressTextDialog extends Dialog {
    private Animation anim;
    private ImageView progressLoading;
    private TextView progressText;
    private int width;
    private int height;
    private int textSize;
    private int progressSize;

    private String text;
    private View view;

    public static ProgressTextDialog.Builder newBuilder(Context val) {
        return new ProgressTextDialog.Builder(val);
    }

    private ProgressTextDialog(Builder builder) {
        super(builder.context, R.style.dialog_pay);
        text = builder.text;
        width = builder.width;
        height = builder.height;
        textSize = builder.textSize;
        progressSize = builder.progressSize;
        initView();
    }

    private void initView() {
        view = View.inflate(getContext(), R.layout.dialog_progress_text, null);
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
        progressLoading = (ImageView) view.findViewById(R.id.dialog_progress_text_loading);
        progressText = (TextView) view.findViewById(R.id.dialog_progress_text_text);

        if (progressSize != 0) {
            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(progressSize, progressSize);
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            layoutParams.setMargins(0, width / 5, 0, 0);
            progressLoading.setLayoutParams(layoutParams);
        }

        progressText.setText(text);
        if (textSize != 0) {
            progressText.setTextSize(textSize);
        }
    }

    @Override
    public void show() {
        super.show();
        if (progressLoading == null) {
            progressLoading = (ImageView) view.findViewById(R.id.dialog_progress_text_loading);
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
        private String text;
        private int width = 0;
        private int height = 0;
        private int textSize = 0;
        private int progressSize = 0;

        public ProgressTextDialog build() {
            return new ProgressTextDialog(this);
        }

        private Builder(Context val) {
            context = val;
            if (width == 0) {
                width = SizeUtil.dp2px(val, 70);
            }
            if (height == 0) {
                height = SizeUtil.dp2px(val, 70);
            }
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = SizeUtil.dp2px(context, width);
            return this;
        }

        public Builder setHeight(int height) {
            this.height = SizeUtil.dp2px(context, height);
            return this;
        }

        public Builder setTextSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder setProgressSize(int progressSize) {
            this.progressSize = SizeUtil.dp2px(context, progressSize);
            return this;
        }
    }
}
