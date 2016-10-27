package com.example.administrator.day_10_10.ui.fragmentui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.day_10_10.App;
import com.example.administrator.day_10_10.R;
import com.example.administrator.day_10_10.adapter.listAdapter;
import com.example.administrator.day_10_10.api.Server;
import com.example.administrator.day_10_10.component.DaggerFragmentCompoent;
import com.example.administrator.day_10_10.module.AdapterModule;
import com.example.administrator.day_10_10.view.MyScrollListView;
import com.example.administrator.day_10_10.view.RefreshScrollviewLayout;
import com.jaydenxiao.common.baserx.RxBus;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class BlankFragment extends Fragment implements RefreshScrollviewLayout.SuperRefreshLayoutListener{

    public RxManager manager = new RxManager();


    Server server = new Server();
    @BindView(R.id.but_on)
    Button butOn;
    @BindView(R.id.listView)
    MyScrollListView listView;
    @BindView(R.id.listLayout)
    LinearLayout listLayout;
    @BindView(R.id.refresh)
    RefreshScrollviewLayout mRefreshLayout;

    @Inject @Named("one")
    List<String> datas;

    @Inject
    listAdapter adapter;
    private LinearLayout mFooterView;

    public BlankFragment() {

        manager.on("string", new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("BlankFragment", s);
            }
        });

    }

    public void getData() {
        manager.add(server.getDatas().subscribe(new RxSubscriber<String>(getContext()) {
            @Override
            protected void _onNext(String s) {
                Log.d("BlankFragment", s);
            }

            @Override
            protected void _onError(String s) {

            }
        }));
    }

    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, view);
        mRefreshLayout.setSuperRefreshLayoutListener(this);
        DaggerFragmentCompoent.builder()
                .appComponent(App.getAppComponent())
                .adapterModule(new AdapterModule())
                .build()
                .inject(this);
        mFooterView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_list_view_footer, null);
        listView.addFooterView(mFooterView);
        listView.setAdapter(adapter);
        mRefreshLayout.setCanLoadMore();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        manager.clear();
        super.onDetach();

    }


    @OnClick(R.id.but_on)
    public void onClick() {
        getData();
        RxBus.getInstance().post("string", "hahah");
    }

    @Override
    public void onRefreshing() {
        Log.d("BlankFragment", "onRefreshing");
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.onLoadComplete();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        Log.d("BlankFragment", "onLoadMore()");
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.onLoadComplete();
                adapter.addItem(datas);
            }
        },2000);
    }
}
