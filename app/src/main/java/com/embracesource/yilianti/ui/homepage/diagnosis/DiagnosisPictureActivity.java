package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RadioGroup;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.common.adapter.CommonAdapter;
import com.embracesource.yilianti.common.adapter.ViewHolder;
import com.embracesource.yilianti.common.recyclerview.SwipeRecyclerView;
import com.embracesource.yilianti.databinding.ActivityDiagnosisPictureBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.viewmodel.DiagnosisPictureCallBack;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisPictureActivity extends AacBaseActivity<ActivityDiagnosisPictureBinding> implements DiagnosisPictureCallBack {

    //    @Inject
    DiagnosisPictureViewModel viewModel;
    private CommonAdapter mAdapter;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this); //注入
        initView();
        initTitleLayout(getString(R.string.diagnosis));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_diagnosis_picture;
    }

    @Override
    protected void initView() {
        viewModel = new DiagnosisPictureViewModel(this);
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_my_launch:
                        viewModel.getMyLaunchList(mContext, 0);
                        break;
                    case R.id.rb_my_participate:
//                        requestData02();
                        break;
                }
            }
        });
        binding.rbMyLaunch.setChecked(true);

//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        int i = 10;
        List<Object> mList = new ArrayList<>();
        while (i-- > 0) {
            mList.add(i);
        }
        mAdapter = new CommonAdapter(this, R.layout.diagnosis_item, mList) {
            @Override
            protected void convert(ViewHolder viewHolder, Object item, int position) {

            }
        };
/*        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                //// TODO: 2017/10/17 0017 test 
                new ApplyDiagnosisDetailViewModel(new ApplyDiagnosisDetailCallBack() {

                }).getDetail("65", "2");
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });*/
        binding.swipeRecyclerView.setAdapter(mAdapter);
        binding.swipeRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        binding.swipeRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                viewModel.getMyLaunchList(mContext, currentPage);
                binding.swipeRecyclerView.setRefreshing(false);
            }

            @Override
            public void onLoadMore() {
                viewModel.getMyLaunchList(mContext, ++currentPage);
            }
        });
        setTitleRightVisiable(getString(R.string.apply_diagnosis), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //申请会诊
                Intent intent = new Intent(mContext, ApplyDiagnosisActivity01.class);
                startActivity(intent);
            }
        });
    }
    //        设置分割线
//        rcv.addItemDecoration(new BaseItemDecoration(this,R.color.colorAccent));
/*        binding.recyclerView.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void OnItemClick ( int position){
            Toast.makeText(LinearManagerActivity.this, "item" + position + " has been clicked", Toast.LENGTH_SHORT).show();
        }
    });*/
/*
    private View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.item_header,rcv,false);
        ((TextView) view.findViewById(R.id.tv)).setText("Header"+headerViews.size());
        headerViews.add(view);
        return view;
    }

    private View getFooterView() {
        View view = getLayoutInflater().inflate(R.layout.item_footer,rcv,false);
        ((TextView) view.findViewById(R.id.tv)).setText("Footer"+footerViews.size());
        footerViews.add(view);
        return view;
    }
*/

    public void getMyLaunchListOK(MyLaunchListBean response, int pageNum) {
  /*      if(pageNum == 0){
            mAdapter.setDatas(response.getData().getList());
        }else{
            mAdapter.addDatas(response.getData().getList());
        }*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getMyLaunchList(mContext, 0);
    }
}
