package com.haohaohu.dialogfactory;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 文字更新。。。
 *
 * @author haohao(ronghao3508 gmail.com) on 2017/10/10 下午 02:34
 * @version v1.0
 */
public class TextLoadDialog extends Dialog {

    private TextView mTextView;

    private String text;
    private int width;
    private int height;

    public static Builder newBuilder(Context val) {
        return new Builder(val);
    }

    private TextLoadDialog(Builder builder) {
        super(builder.context, R.style.dialog_pay);
        width = builder.width;
        height = builder.height;
        text = builder.text;
        View view = View.inflate(getContext(), R.layout.dialog_text_load, null);
        setContentView(view);
        mTextView = (TextView) view.findViewById(R.id.dialog_text_text);
        setText(text);
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = width;
        lp.height = height;
        window.setAttributes(lp);
    }

    public void setText(String str) {
        mTextView.setText(str);
    }

    @Override
    public void show() {
        super.show();
        startAnim();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        stopAnim();
    }

    private void startAnim() {
        mTextView.post(new Runnable() {
            @Override
            public void run() {
                TextAnimUtil.showTextLoadingAnim(mTextView, text);
            }
        });
    }

    private void stopAnim() {
        mTextView.post(new Runnable() {
            @Override
            public void run() {
                TextAnimUtil.stopTextLoadingAnim(mTextView);
            }
        });
    }

    public static final class Builder {
        private Context context;

        private String text;
        private int width;
        private int height;

        private Builder(Context val) {
            context = val;
            width = SizeUtil.dp2px(val, 172);
            height = SizeUtil.dp2px(val, 66);
        }

        public Builder setText(String val) {
            text = val;
            return this;
        }

        public Builder width(int val) {
            width = val;
            return this;
        }

        public Builder height(int val) {
            height = val;
            return this;
        }

        public TextLoadDialog build() {
            return new TextLoadDialog(this);
        }
    }
}
