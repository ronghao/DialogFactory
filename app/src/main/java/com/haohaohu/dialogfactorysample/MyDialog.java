package com.haohaohu.dialogfactorysample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.haohaohu.dialogfactory.BaseDialog;

/**
 * @author haohao(ronghao3508@gmail.com) on 2018/1/15 上午10:57
 * @version v1.0
 */
public class MyDialog extends BaseDialog {
    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable,
            @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void bindView() {

    }

    @Override
    public int getLayout() {
        return R.layout.dialog_my_layout;
    }

    @Override
    public int getWidth() {
        return 1000;
    }

    @Override
    public int getHeight() {
        return 300;
    }
}
