package com.qfedu.firstapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qfedu.firstapp.R;

public class DialogActivity extends AppCompatActivity {
    private Button btnGeneral, btnSubmit, btnProgress, btnDiy;
    private TextView tvNickname;
    private String nickname;

    private Button button;

    private static final int UPDATE_NICK_REQCODE = 10086;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        btnGeneral = findViewById(R.id.btn_general);
        btnGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGeneralDialog();
            }
        });

        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSubmitDialog();
            }
        });

        btnProgress = findViewById(R.id.btn_progress);
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProgressDialog();
            }
        });

        btnDiy = findViewById(R.id.btn_diy);
        btnDiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDiyDialog();
            }
        });

        nickname = "大卫";
        tvNickname = findViewById(R.id.tv_nickname);
        tvNickname.setText("昵称：" + nickname);

        button = findViewById(R.id.btn_update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到B界面（修改界面）
                openUpdateNickPage();
            }
        });
    }

    private void openUpdateNickPage() {
        Intent intent = new Intent(this, UpdateNickNameActivity.class);
        intent.putExtra("nick", nickname);//把当前的昵称的内容携带至B界面
        startActivityForResult(intent, UPDATE_NICK_REQCODE);
    }

    /**
     * 该方法写在A界面，用于接收B界面回传回来的数据内容
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_NICK_REQCODE) {//用reqeustCode来判断数据是哪个界面回来的
            if (resultCode == RESULT_OK) {
                String newNick = data.getStringExtra("nick");
                tvNickname.setText("昵称:" + newNick);
            }
        }
    }

    /**
     * 该方法用于在界面上弹出一个 自定义效果的 提示框
     */
    private void openDiyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialog_diy = LayoutInflater.from(this).inflate(R.layout.dialog_alert, null);
        builder.setView(dialog_diy);//该setView方法，即可以接收一个自定义的View视图
//        默认 点击dialog区域外的区域，dialog自动消失，即默认为true
//        builder.setCancelable(false);//设置点击dialog区域外，自动关闭dialog的开关
        builder.show();
    }

    /**
     * 该方法用于在界面上弹出一个带有进度条的对话框
     */
    private void openProgressDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("下载进度");
        //设置进度条的样式
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.setSecondaryProgress(23);
        dialog.show();
        //开启一个线程 不断的更新进度条的进度
        new Thread(new Runnable() {
            @Override
            public void run() {
                //这里是子线程中
                try {
                    Thread.sleep(500);
                    //runOnUiThread：提供在子线程当中，切换回主线程的一种方式（渠道）
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //获取到当前的进度是多少
                            int progress = dialog.getSecondaryProgress();
                            while (progress < 100) {
                                progress++;
                                Log.i("TAG", "进度:" + progress);
                                //操作UI界面的语句：UI界面只能在主线程当中操作
                                dialog.setSecondaryProgress(progress);
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        //一个进程 至少有一个线程（主线程）
        //android当中规定，耗时操作不能在主线程当中，必须在子线程当中

    }

    /**
     * 该方法用于在界面上弹出一个可以供用户点击 对应选项的 对话框
     */
    private void openSubmitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("删除确认");

        builder.setMessage("您确认要删除此条数据吗?");
        //确认按钮、取消按钮
        //Positive: 积极地 =》 确认
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //当用户点击了 删除按钮以后，要执行什么操作，程序会到此处执行
                Toast.makeText(DialogActivity.this, "用户点击了Positive按钮", Toast.LENGTH_SHORT).show();
            }
        });
        //Negative：消极的
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //用户点击Negative按钮以后，要执行的操作代码，可以放在此处执行
                Toast.makeText(DialogActivity.this, "用户点击了Negative按钮", Toast.LENGTH_SHORT).show();
            }
        });
        //关闭按钮：Neutral
        builder.setNeutralButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DialogActivity.this, "用户点击了Neutral按钮", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog dialog = builder.show();
//        dialog.dismiss();//关闭dialog的方法
    }

    /**
     * 该方法用于在界面上弹出一个普通的对话框
     */
    private void openGeneralDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //dialog提示框，不支持直接通过new关键字进行创建Dialog对象
        //而是采用了设计模式中的一种Builder模式，通过AlertDialog.Builder 类完成对AlertDialog的创建
        //通过builder可以为dialog设置各种属性，完成dialog的创建
        //设置图标
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("提示框标题");
        builder.setMessage("提示框中要展示的内容");
        builder.show();//展示创建的dialog 到界面上

    }
}