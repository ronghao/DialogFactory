package com.haohaohu.dialogfactorysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.haohaohu.dialogfactory.IOSDialog;
import com.haohaohu.dialogfactory.ProgressDialog;
import com.haohaohu.dialogfactory.ProgressTextDialog;

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
        ProgressTextDialog.newBuilder(MainActivity.this).setText("加载中").build().show();
    }
}
