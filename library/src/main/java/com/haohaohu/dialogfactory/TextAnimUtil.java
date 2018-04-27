package com.haohaohu.dialogfactory;

import android.support.annotation.NonNull;
import android.widget.TextView;

/**
 * 文字动画类
 *
 * @author haohao(ronghao3508@gmail.com) on 2017/11/23 上午 10:40
 * @version v1.0
 */
public class TextAnimUtil {
    public TextAnimUtil() {
    }

    public static void showTextLoadingAnim(@NonNull final TextView textView, final String str) {
        textView.setTag(R.id.tag_text_loading, true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (textView.getHandler() != null) {
                    String textStr = textView.getText().toString();
                    int index = TextAnimUtil.getEndSize(textStr);
                    ++index;
                    if (index == 4) {
                        index = 0;
                    }

                    String tmpStr = str;

                    for (int i = 0; i < index; ++i) {
                        tmpStr = tmpStr + ".";
                    }

                    synchronized (this) {
                        if (textView.getHandler() != null) {
                            textView.setText(tmpStr);
                            textView.getHandler().postDelayed(this, 500L);
                        }
                    }
                }
            }
        };
        textView.setTag(R.id.tag_text_loading_run, runnable);
        if (textView.getHandler() != null) {
            textView.getHandler().post(runnable);
        }
    }

    private static int getEndSize(String str) {
        return str.endsWith("...") ? 3 : (str.endsWith("..") ? 2 : (str.endsWith(".") ? 1 : 0));
    }

    public static void stopTextLoadingAnim(@NonNull TextView textView) {
        if (textView.getTag(R.id.tag_text_loading) != null) {
            boolean is = (Boolean) textView.getTag(R.id.tag_text_loading);
            if (is) {
                Runnable runnable = (Runnable) textView.getTag(R.id.tag_text_loading_run);
                synchronized (textView) {
                    if (textView.getHandler() != null) {
                        textView.getHandler().removeCallbacks(runnable);
                    }
                }

                textView.setTag(R.id.tag_text_loading, false);
            }
        }
    }
}

