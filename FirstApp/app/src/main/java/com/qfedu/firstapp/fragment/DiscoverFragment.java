package com.qfedu.firstapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.qfedu.firstapp.Const;
import com.qfedu.firstapp.R;
import com.qfedu.firstapp.adapter.NewsTopicAdapter;
import com.qfedu.firstapp.bean.NewsTopic;
import com.qfedu.firstapp.bean.NewsTopicReturn;

import org.json.JSONObject;
import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class DiscoverFragment extends Fragment {

    private ListView lvNewsTopic;
    private int page_num = 1;//请求的页码，默认是1
    private NewsTopicAdapter topicAdapter;
    private int firstVisableItem,visableItemCount,totalItemCount;
    public DiscoverFragment() {

    }

    public static DiscoverFragment newInstance(String param1, String param2) {
        DiscoverFragment fragment = new DiscoverFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discover, container, false);
        lvNewsTopic = root.findViewById(R.id.lv_newstopic);
        setListener();
        return root;
    }

    private void setListener() {
        //为列表控件添加滑动监听
        lvNewsTopic.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int state) {
                switch (state){
                    case SCROLL_STATE_IDLE:
                        //当滑动停止的时候，判断是否到最后一个条目了
                        //如果到了最后一个条目，则出发下一次网络请求
                        if(firstVisableItem + visableItemCount == totalItemCount){
                            //请求下一次新数据
                            System.out.println("到最后一条内容，出发分页请求");
                            netRequest();
                        }
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        break;
                    case SCROLL_STATE_FLING:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisableItem, int visableItemCount, int totalItemCount) {
                System.out.println("第一个可见条目的下标:"+firstVisableItem+"，屏幕可见:"+visableItemCount+",共:"+totalItemCount+"条");
                DiscoverFragment.this.firstVisableItem = firstVisableItem;
                DiscoverFragment.this.visableItemCount = visableItemCount;
                DiscoverFragment.this.totalItemCount = totalItemCount;
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        netRequest();
    }

    private void netRequest(){
        //进入到界面后，先执行一次网络请求
        HttpManager manager = x.http();
        RequestParams params = new RequestParams(Const.NEWSTOPIC_URL);
        //添加请求头
        params.addHeader("Content-Type", "application/x-www-form-urlencoded");
        //设置请求参数
        params.addParameter("key", Const.NEWSTOPIC_KEY);
        params.addParameter("type", "top");
        params.addParameter("page", page_num++);
        params.addParameter("page_size", Const.PAGE_SIZE);
        params.addParameter("is_filter", 0);
        manager.get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                //请求成功，解析数据
                NewsTopicReturn newsTopic = JSON.parseObject(result.toString(), NewsTopicReturn.class);
                List<NewsTopic> newsList = newsTopic.getResult().getData();
                showTopicList(newsList);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void showTopicList(List<NewsTopic> newsList) {
        if(topicAdapter == null){
            topicAdapter = new NewsTopicAdapter(getContext(), newsList);
            lvNewsTopic.setAdapter(topicAdapter);
        }else {
            //非首次请求，指的就是后续的分页加载
            topicAdapter.addNewTopicList(newsList);
            topicAdapter.notifyDataSetChanged();
        }
    }
}