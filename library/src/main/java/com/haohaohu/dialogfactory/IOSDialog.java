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
 * 类似ios的dialog
 *
 * @author haohao(ronghao3508@gmail.com) on 2017/10/31 上午 11:10
 * @version v1.0
 */
public class IOSDialog extends Dialog implements View.OnClickListener {

    private final OnButtonListener onButtonListener;

    private TextView dialogBaseCancel;
    private TextView dialogBaseOk;
    private TextView dialogBaseText;

    private String text;
    private String okText;
    private String cancelText;

    public static Builder newBuilder(Context val) {
        return new Builder(val);
    }

    private IOSDialog(Builder builder) {
        super(builder.context, R.style.dialog_pay);
        text = builder.text;
        okText = builder.okText;
        cancelText = builder.cancelText;
        onButtonListener = builder.onButtonListener;

        initView();
    }

    private void initView() {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_base, null, false);
        dialogBaseCancel = (TextView) view.findViewById(R.id.dialog_base_cancel);
        dialogBaseOk = (TextView) view.findViewById(R.id.dialog_base_ok);
        dialogBaseText = (TextView) view.findViewById(R.id.dialog_base_text);
        setContentView(view);

        dialogBaseCancel.setOnClickListener(this);
        dialogBaseOk.setOnClickListener(this);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = dp2px(260);
        lp.height = dp2px(140);
        window.setAttributes(lp);

        if (!TextUtils.isEmpty(text)) {
            dialogBaseText.setText(text);
        }
        if (!TextUtils.isEmpty(okText)) {
            dialogBaseOk.setText(okText);
        }
        if (!TextUtils.isEmpty(cancelText)) {
            dialogBaseCancel.setText(cancelText);
        }
    }

    public int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.dialog_base_cancel) {
            if (onButtonListener != null) {
                onButtonListener.onCancel();
            }
            IOSDialog.this.dismiss();
        } else if (i == R.id.dialog_base_ok) {
            if (onButtonListener != null) {
                onButtonListener.onOK();
            }
            IOSDialog.this.dismiss();
        } else {
        }
    }

    public interface OnButtonListener {
        void onOK();

        void onCancel();
    }

    public static final class Builder {
        private Context context;
        private String text;
        private String okText;
        private String cancelText;
        private OnButtonListener onButtonListener;

        private Builder(Context val) {
            context = val;
        }

        public Builder setText(String val) {
            text = val;
            return this;
        }

        public Builder setOkText(String val) {
            okText = val;
            return this;
        }

        public Builder setCancelText(String val) {
            cancelText = val;
            return this;
        }

        public Builder setOnButtonListener(OnButtonListener listener) {
            onButtonListener = listener;
            return this;
        }

        public IOSDialog build() {
            return new IOSDialog(this);
        }
    }
}