package com.haohaohu.dialogfactory;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 带文字的圆形进度dialog
 *
 * @author haohao(ronghao3508@gmail.com) on 2017/11/20 上午 11:10
 * @version v1.0
 */
public class ProgressTextDialog extends Dialog {
    private Animation anim;
    private ImageView progressLoading;
    private TextView progressText;

    private String text;

    public static ProgressTextDialog.Builder newBuilder(Context val) {
        return new ProgressTextDialog.Builder(val);
    }

    private ProgressTextDialog(Builder builder) {
        super(builder.context, R.style.dialog_pay);
        text = builder.text;
        initView();
    }

    private void initView() {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_progress_text, null, false);
        setContentView(view);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = dp2px(70);
        lp.height = dp2px(70);
        window.setAttributes(lp);

        anim = AnimationUtils.loadAnimation(getContext(), R.anim.progress_rotate);
        anim.setInterpolator(new LinearInterpolator());
        progressLoading = (ImageView) view.findViewById(R.id.dialog_progress_text_loading);
        progressText = (TextView) view.findViewById(R.id.dialog_progress_text_text);
        progressLoading.startAnimation(anim);

        progressText.setText(text);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (anim != null) {
            progressLoading.clearAnimation();
        }
    }

    public int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static final class Builder {
        private Context context;
        private String text;

        public ProgressTextDialog build() {
            return new ProgressTextDialog(this);
        }

        private Builder(Context val) {
            context = val;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }
    }
}