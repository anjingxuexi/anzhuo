package com.qfedu.firstapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.qfedu.firstapp.R;
import com.qfedu.firstapp.bean.HotVideo;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 该界面用于实现热门视频播放功能
 */
//@ContentView(R.layout.activity_hot_video_play)
public class HotVideoPlayActivity extends BaseActivity {
    @ViewInject(R.id.tv_play_title)
    private TextView tvTitle;

    @ViewInject(R.id.webview_play)
    private WebView webViewPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_video_play);
        x.view().inject(this);
        //获取数据
        Intent intent = getIntent();
        HotVideo video = (HotVideo) intent.getSerializableExtra("video");

        //标题
        tvTitle.setText(video.getTitle());
        //对WebView进行设置
        WebSettings settings = webViewPlay.getSettings();
        settings.setJavaScriptEnabled(true);
        //加载播放url
        webViewPlay.loadUrl(video.getShare_url());
    }
}