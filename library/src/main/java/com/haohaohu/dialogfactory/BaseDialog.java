package com.haohaohu.dialogfactory;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 基础dialog
 *
 * @author haohao(ronghao3508@gmail.com) on 2018/1/15 上午10:35
 * @version v1.0
 */
public abstract class BaseDialog extends Dialog {

    protected View mView;

    public BaseDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable,
            @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        if (getLayout() == 0) return;
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(getLayout(), null, false);
        setContentView(mView);
        bindView();

        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = getWidth();
        lp.height = getHeight();
        window.setAttributes(lp);
    }

    /**
     * 绑定布局
     */
    public abstract void bindView();

    /**
     * 获取布局
     *
     * @return 布局id
     */
    public abstract int getLayout();

    /**
     * 获取宽度
     *
     * @return 宽度
     */
    public abstract int getWidth();

    /**
     * 获取高度
     *
     * @return 高度
     */
    public abstract int getHeight();
}
