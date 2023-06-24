package com.qfedu.firstapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qfedu.firstapp.R;

import java.util.Timer;

public class LoginActivity extends AppCompatActivity {
    //用户输入框：手机号，验证码
    private EditText etPhone, etSmsCode;
    //用户点击按钮：发送验证码，登录按钮
    private Button btnSendSms, btnLogin;
    //文本：忘记密码，新用户注册
    private TextView tvForgetPwd, tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取到控件
        etPhone = findViewById(R.id.et_phonenum);
        etSmsCode = findViewById(R.id.et_smscode);
        btnSendSms = findViewById(R.id.btn_sendsms);
        btnLogin = findViewById(R.id.btn_login);
        tvForgetPwd = findViewById(R.id.tv_forgetpwd);
        tvSignUp = findViewById(R.id.tv_signup);

        //为按钮添加点击事件
        btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用户点击 发送验证码按钮时，程序会到此处执行
                sendSms();
            }
        });
        //为登录按钮添加点击事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //当用户点击登录按钮时，程序会跳到此处执行
                login();
            }
        });
    }

    /**
     * 该方法用于处理用户登录的业务逻辑
     */
    private void login() {
        //1、获取到用户输入的内容
        String phoneStr = etPhone.getText().toString().trim();
        String smsStr = etSmsCode.getText().toString().trim();

        //2、对用户输入的内容进行格式和安全性检查
        if ("".equals(phoneStr) || "".equals(smsStr)) {
            Toast.makeText(this, "手机号或验证码为空，请检查后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phoneStr.length() != 11 || smsStr.length() != 6) {
            Toast.makeText(this, "手机号或验证码不符合正确格式，请检查后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        // 13167581234 =》 23167582345
        if (!phoneStr.startsWith("1")) {
            Toast.makeText(this, "手机号格式不正确，请检查是否以1开头，然后重试", Toast.LENGTH_SHORT).show();
            return;
        }

        //3、进行数据的验证和比对
        //以：13167582311 和 1234 作为数据进行校验
        if (phoneStr != "13167582311" || smsStr != "123456") {
            Toast.makeText(this, "登录失败,请重试", Toast.LENGTH_SHORT).show();
            return;
        }
        //4、根据核对结果 进行相应的处理
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 发送验证码
     */
    private void sendSms() {
        //1、获取到用户输入的手机号
        String phoneInput = etPhone.getText().toString();
        //2、检验核对用户输入的内容
        if ("".equals(phoneInput)) {
            Toast.makeText(this, "请先输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phoneInput.length() != 11) {
            Toast.makeText(this, "手机号不正确，请检查后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        // 13167581234 =》 23167582345
        if (!phoneInput.startsWith("1")) {
            Toast.makeText(this, "手机号格式不正确，请检查是否以1开头，然后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        //131、132、135、187、176、189...123
        //3、用户输入的内容符合规范，给该手机发送验证码
        //申请权限：权限申请
        Toast.makeText(this, "发送验证码成功", Toast.LENGTH_SHORT).show();
        //把发送验证码按钮设置为"不可点击"
        btnSendSms.setClickable(false);
        btnSendSms.setBackgroundColor(getResources().getColor(R.color.gray));
        //56s后再次发送
        //定时器：用于多次执行同一个任务的功能

        btnSendSms.setText("53s后发送");
    }


}