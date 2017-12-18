package com.haohaohu.dialogfactory;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 类似ios的dialog，只有确认
 *
 * @author haohao(ronghao3508@gmail.com) on 2017/10/31 上午 11:10
 * @version v1.0
 */
public class VerifyDialog extends Dialog implements View.OnClickListener {

    private final OnButtonListener onButtonListener;

    private TextView dialogVerifyOk;
    private TextView dialogVerifyText;

    private String text;
    private String okText;

    private int width;
    private int height;
    private boolean isCancel = true;

    public static Builder newBuilder(Context val) {
        return new Builder(val);
    }

    private VerifyDialog(Builder builder) {
        super(builder.context, R.style.dialog_pay);
        text = builder.text;
        okText = builder.okText;
        onButtonListener = builder.onButtonListener;
        width = builder.width;
        height = builder.height;
        isCancel = builder.isCancel;
        initView();
    }

    private void initView() {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_verify, null, false);
        dialogVerifyOk = (TextView) view.findViewById(R.id.dialog_verify_ok);
        dialogVerifyText = (TextView) view.findViewById(R.id.dialog_verify_text);
        setContentView(view);

        dialogVerifyOk.setOnClickListener(this);
        setCancelable(isCancel);
        setCanceledOnTouchOutside(isCancel);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = width;
        lp.height = height;
        window.setAttributes(lp);

        if (!TextUtils.isEmpty(text)) {
            dialogVerifyText.setText(text);
        }
        if (!TextUtils.isEmpty(okText)) {
            dialogVerifyOk.setText(okText);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.dialog_verify_ok) {
            if (onButtonListener != null) {
                onButtonListener.onOK();
            }
            if (isCancel) {
                VerifyDialog.this.dismiss();
            }
        }
    }

    public interface OnButtonListener {
        void onOK();
    }

    public static final class Builder {
        private Context context;
        private String text;
        private String okText;

        private int width;
        private int height;
        private boolean isCancel = true;
        private OnButtonListener onButtonListener;

        private Builder(Context val) {
            context = val;
            width = SizeUtil.dp2px(val, 260);
            height = SizeUtil.dp2px(val, 140);
        }

        public Builder setText(String val) {
            text = val;
            return this;
        }

        public Builder setOkText(String val) {
            okText = val;
            return this;
        }

        public Builder setOnButtonListener(OnButtonListener listener) {
            onButtonListener = listener;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setCancel(boolean cancel) {
            isCancel = cancel;
            return this;
        }

        public VerifyDialog build() {
            return new VerifyDialog(this);
        }
    }
}
