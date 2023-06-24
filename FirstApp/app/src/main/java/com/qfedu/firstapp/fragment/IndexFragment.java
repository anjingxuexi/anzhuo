package com.qfedu.firstapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.qfedu.firstapp.Const;
import com.qfedu.firstapp.R;
import com.qfedu.firstapp.activity.HotVideoPlayActivity;
import com.qfedu.firstapp.adapter.HotVideoAdapter;
import com.qfedu.firstapp.bean.Data;
import com.qfedu.firstapp.bean.HotVideo;
import com.qfedu.firstapp.dialog.LoadingDialog;

import org.json.JSONObject;
import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * 首页Fragment自定义类
 * 在首页Fragment当中，从聚合数据网站获取热门视频数据
 * 并解析，然后展示到首页Fragment的页面上
 */
public class IndexFragment extends Fragment {

    //    private ListView lvHotVideo;
    private GridView gvHotVideo;
    private LoadingDialog loadingDialog;

    //该方法用于创建一个IndexFragment的实例，并返回该实例对象，可以通过类名.newInstance直接调用
    public static IndexFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        return fragment;
    }

    //Fragment碎片，是依托于Activity的
    //Activity要对Fragment进行管理
    //Activity、Fragment：生命周期。 生老病死：创建、展示、销毁等过程
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //setcontentView:设置activity对应的布局文件,系统能够自动的绑定
    //Fragment当中，拿到对应的碎片的布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //LayoutInflater：布局解析类。泵：提供动力
        //listView的适配器Adapter当中，用于解析得到条目的布局模板时使用
        View root = inflater.inflate(R.layout.fragment_index, container, false);
//        lvHotVideo = root.findViewById(R.id.lv_hotvideo);
        gvHotVideo = root.findViewById(R.id.gv_hotvideo);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        //1、发起网络请求，请求热门视频的数据
        RequestParams params = new RequestParams(Const.HOTVIDEO_URL);
        params.setHeader("Content-Type", "application/x-www-form-urlencoded");
        //添加请求参数
        params.addParameter("key", Const.HOTVIDEO_KEY);
        params.addParameter("type", "hot_video");
        params.addParameter("size", Const.PAGE_SIZE);
        System.out.println(000);
        HttpManager manager = x.http();
        //将网络请求的loading框 展示出来
        showLoading();
        manager.get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //网络请求成功的方法
                System.out.println("onSuccess:" + 111);
                System.out.println(result);
                //解析json数据，得到Java对象
                Data dataResult = JSON.parseObject(result, Data.class);
                int size = dataResult.getResult().size();
//                System.out.println(size);
                //将解析到的数据传递给 适配器，进行展示
                showHotVideo(dataResult.getResult());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //网络请求失败的方法
                System.out.println("onError:" + 222);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //取消网络请求
            }

            @Override
            public void onFinished() {
                //请求结束时
                System.out.println("onFinished:" + 333);
                //无论网络请求结果如何，最后一定会走onfinished方法
                //因此，在onfinished方法当中，将网络加载对话框取消掉
                closeLoading();
            }

        });
        System.out.println(444);
        System.out.println(555);
        System.out.println(666);
    }

    /**
     * 该方法用于创建一个网络请求加载框，并展示
     */
    private void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getContext());
        }
        loadingDialog.show();//展示loading对话框
    }

    /**
     * 该方法用于关闭正在显示的loading加载框
     */
    private void closeLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    /**
     * 该方法用来展示网络请求到的热门视频的数据
     *
     * @param result 需要展示的结果
     */
    private void showHotVideo(List<HotVideo> result) {
        //1、创建适配器对象
        HotVideoAdapter adapter = new HotVideoAdapter(getContext(), result);
//        lvHotVideo.setAdapter(adapter);
        gvHotVideo.setAdapter(adapter);

        //同时，为列表控件添加 点击事件
        gvHotVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //当用户点击列表控件的某个条目时,程序会到此处执行
                openPlayVideo(result.get(position));
            }
        });
    }

    /**
     * 该方法用于打开热门视频播放界面
     */
    private void openPlayVideo(HotVideo hotVideo) {
        Intent intent = new Intent(getContext(), HotVideoPlayActivity.class);
        intent.putExtra("video", hotVideo);
        getContext().startActivity(intent);
    }
}