package com.haohaohu.dialogfactorysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.haohaohu.dialogfactory.IOSDialog;
import com.haohaohu.dialogfactory.ProgressDialog;
import com.haohaohu.dialogfactory.ProgressTextDialog;
import com.haohaohu.dialogfactory.TextDialog;
import com.haohaohu.dialogfactory.TextLoadDialog;
import com.haohaohu.dialogfactory.VerifyDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View view) {
        IOSDialog.newBuilder(MainActivity.this)
                .setText("自定义标题")
                .setOkText("自定义确定")
                .setCancelText("自定义取消")
                .setOnButtonListener(new IOSDialog.OnButtonListener() {
                    @Override
                    public void onOK() {
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .show();
    }

    public void onClick2(View view) {
        ProgressDialog.newBuilder(MainActivity.this).build().show();
    }

    public void onClick3(View view) {
        ProgressTextDialog.newBuilder(MainActivity.this).setText("网络加载中").build().show();
    }

    public void onClick4(View view) {
        TextDialog.newBuilder(MainActivity.this).setText("网络加载中").build().show();
    }

    public void onClick5(View view) {
        VerifyDialog.newBuilder(MainActivity.this)
                .setText("网络加载中")
                .setCancel(true)
                .setOnButtonListener(new VerifyDialog.OnButtonListener() {
                    @Override
                    public void onOK() {
                        Toast.makeText(MainActivity.this, "确认", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .show();
    }

    public void onClick6(View view) {
        MyDialog dialog = new MyDialog(MainActivity.this);
        dialog.show();
    }

    public void onClick7(View view) {
        TextLoadDialog.newBuilder(MainActivity.this).setText("网络加载中").build().show();
    }
}
