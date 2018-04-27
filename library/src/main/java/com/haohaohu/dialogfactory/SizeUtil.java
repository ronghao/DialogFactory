package com.haohaohu.dialogfactory;

import android.content.Context;

/**
 * 工具类
 *
 * @author haohao(ronghao3508@gmail.com) on 2017/11/23 上午 10:45
 * @version v1.0
 */
public class SizeUtil {
    public static int dp2px(Context val, float dpValue) {
        final float scale = val.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
