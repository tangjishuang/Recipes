package recipes.two_strill.com.recipes.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mikepenz.materialdrawer.model.ContainerDrawerItem;
import com.wq.android.lib.verticaltablayout.VerticalTabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.adapter.MyAdapter;
import recipes.two_strill.com.recipes.ui.bean.Api;
import recipes.two_strill.com.recipes.ui.bean.CategoryInfo;
import recipes.two_strill.com.recipes.ui.bean.FragmentInfo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 分类&食材
 * Created by SD on 2018/8/18.
 */

@SuppressLint("ValidFragment")
public class CategoryFragment extends Fragment {

    private VerticalTabLayout tabLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private String[] tabTxt = new String[17];
    private String[] tabTxtSruff = new String[11];
    //判读是否是recyclerView主动引起的滑动，true- 是，false- 否，由tablayout引起的
    private boolean isRecyclerScroll;
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private int lastPos;
    //用于recyclerView滑动到指定的位置
    private boolean canScroll;
    private int scrollToPosition;
    Context mContext ;
    private Api api;
    List<CategoryInfo.ResultBean> mResultBean = new ArrayList<CategoryInfo.ResultBean>();
    List<CategoryInfo.ResultBean> mResultBeanStuff = new ArrayList<CategoryInfo.ResultBean>();
    private int mFragmentPsition;//mFragmentPsition=1代表是分类页，2代表食材页

    @SuppressLint("ValidFragment")
    public CategoryFragment(int fragmentPsition) {
        mFragmentPsition = fragmentPsition;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        tabLayout = view.findViewById(R.id.tablayout);
        mContext= getActivity().getApplicationContext();
        //a[1] = tabTxt[1];

        initData();

        return view;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://apis.juhe.cn/cook/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .build();
        api = retrofit.create(Api.class);

       /* Map<String, String> map = new HashMap<>();
        map.put("menu", "西瓜");
        map.put("key", "69517ba5aaa57d42b55780cc22f852ce");*/

        //int parentid = 10001+10002+10004+10005+10006+10007+10009+10012+10013+10020+10021+10022+10023+10024+10025+10026+10027+10028;
        //menu=西瓜&dtype=json&pn=0&rn=10&albums=&key=69517ba5aaa57d42b55780cc22f852ce
        api.getCategory("69517ba5aaa57d42b55780cc22f852ce","")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CategoryInfo>() {
                    @Override
                    public void accept(CategoryInfo categoryInfo) throws Exception {
                        setDate(categoryInfo);
                    }
                });


    }

    private void setDate(final CategoryInfo recipesInfo) {

        List<CategoryInfo.ResultBean> resultBean = recipesInfo.getResult();
        int j = 0;
        int f = 0;
        for (int i=0; i<resultBean.size();i++) {
            if (resultBean.get(i).getParentId().equals("10001") | resultBean.get(i).getParentId().equals("10002")
                        | resultBean.get(i).getParentId().equals("10004")
                        | resultBean.get(i).getParentId().equals("10005")
                        | resultBean.get(i).getParentId().equals("10006")
                        | resultBean.get(i).getParentId().equals("10007")
                        | resultBean.get(i).getParentId().equals("10009")
                        | resultBean.get(i).getParentId().equals("10012")
                        | resultBean.get(i).getParentId().equals("10013")
                        | resultBean.get(i).getParentId().equals("10020")
                        | resultBean.get(i).getParentId().equals("10021")
                        | resultBean.get(i).getParentId().equals("10022")
                        | resultBean.get(i).getParentId().equals("10023")
                        | resultBean.get(i).getParentId().equals("10024")
                        | resultBean.get(i).getParentId().equals("10025")
                        | resultBean.get(i).getParentId().equals("10026")
                        | resultBean.get(i).getParentId().equals("10027")) {

                tabTxt[j] = resultBean.get(i).getName();
                mResultBean.add(resultBean.get(i));
                j = j + 1;
            } else {

                tabTxtSruff[f] = resultBean.get(i).getName();
                mResultBeanStuff.add(resultBean.get(i));
                f = f + 1;
            }
        }

        setVeTabLayout();

    }

    /**
     * 设置垂直的tabLayout
     */
    private void setVeTabLayout() {
        //tablayout设置标签
        if (mFragmentPsition == 1) {
            for (int i = 0; i < 17; i++) {
                tabLayout.addTab(tabLayout.newTab().setText(tabTxt[i]));
            }
        } else {
            for (int i = 0; i < 11; i++) {
                tabLayout.addTab(tabLayout.newTab().setText(tabTxtSruff[i]));
            }
        }

        //计算内容块所在的高度，全屏高度-状态栏高度-tablayout的高度(这里固定高度50dp)，用于recyclerView的最后一个item view填充高度
        int screenH = getScreenHeight();
        int statusBarH = getStatusBarHeight(mContext);

        int lastH = screenH - statusBarH;
        manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        if (mFragmentPsition == 1) {
            recyclerView.setAdapter(new MyAdapter(mContext, tabTxt, lastH, mResultBean,mFragmentPsition));
        } else {
            recyclerView.setAdapter(new MyAdapter(mContext, tabTxtSruff, lastH, mResultBeanStuff,mFragmentPsition));
        }

        tabLayout.setOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(VerticalTabLayout.Tab tab, int position) {
                //点击标签，使recyclerView滑动，isRecyclerScroll置false
                // int pos = tab.getPosition();
                isRecyclerScroll = false;
                moveToPosition(manager, recyclerView, position);
            }

            @Override
            public void onTabReleased(VerticalTabLayout.Tab tab, int position) {

            }
        });


        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //当滑动由recyclerView触发时，isRecyclerScroll 置true
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isRecyclerScroll = true;
                }
                return false;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (canScroll) {
                    canScroll = false;
                    moveToPosition(manager, recyclerView, scrollToPosition);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isRecyclerScroll) {
                    //第一个可见的view的位置，即tablayou需定位的位置
                    int position = manager.findFirstVisibleItemPosition();
                    if (lastPos != position) {
                        //tabLayout.setScrollPosition(position, 0, true);
                        //tabLayout.setVerticalScrollbarPosition(3);
                        tabLayout.setSelectedTab(position);
                        Log.i("onScrolled", "onScrolled: "+position);
                    }
                    lastPos = position;
                }
            }
        });
    }

    public void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int position) {
        // 第一个可见的view的位置
        int firstItem = manager.findFirstVisibleItemPosition();
        // 最后一个可见的view的位置
        int lastItem = manager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            int top = mRecyclerView.getChildAt(position - firstItem).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            scrollToPosition = position;
            canScroll = true;
        }
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                                 .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
