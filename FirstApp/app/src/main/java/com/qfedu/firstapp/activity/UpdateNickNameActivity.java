package com.qfedu.firstapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qfedu.firstapp.R;

public class UpdateNickNameActivity extends AppCompatActivity {
    private EditText etNick;
    private Button btnSubmitNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick_name);
        etNick = findViewById(R.id.et_nick);
        btnSubmitNick = findViewById(R.id.btn_submit_update);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nick");
        etNick.setText(nickName);

        btnSubmitNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //① 把修改后的最新的昵称数据 回传给A界面
                //② 关闭当前页面
                updateNick();
            }
        });

    }

    /**
     * 确定修改
     */
    private void updateNick() {
        //① 首先获取到最新修改后的昵称
        String newNick = etNick.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("nick", newNick);
        setResult(RESULT_OK, intent);//将要回传的数据通过setResult方法回传回去
        //关闭当前页面
        finish();
    }
}