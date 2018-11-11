package recipes.two_strill.com.recipes.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.bean.RecipesInfo;
import recipes.two_strill.com.recipes.ui.utils.SetTabLayoutIndicator;
import recipes.two_strill.com.recipes.ui.view.CustomScrollView;
import recipes.two_strill.com.recipes.ui.view.DetailImtroBurdenView;
import recipes.two_strill.com.recipes.ui.view.DetailStepsView;

public class RecipesDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_title_detail)
    TextView tvTitleDetail;
    @BindView(R.id.iv_detail)
    ImageView ivDetail;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.scroll_view_recipes_details)
    CustomScrollView scrollView;

    public int top;//anchorList的子view的y轴位置

    private String[] tabTxt = {"食 材", "步 骤", "推 荐"};
    //内容块view的集合
    private List<LinearLayout> anchorList = new ArrayList<>();
    //判读是否是scrollview主动引起的滑动，true-是，false-否，由tablayout引起的
    private boolean isScroll;
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private int lastPos = 0;
    //监听判断最后一个模块的高度，不满一屏时让最后一个模块撑满屏幕
    private ViewTreeObserver.OnGlobalLayoutListener listener;

    //详情数据
    public RecipesInfo.ResultBean.DataBean itemDataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_details);
        ButterKnife.bind(this);

        itemDataBean = (RecipesInfo.ResultBean.DataBean) getIntent().getSerializableExtra("itemDataBean");

        tvTitleDetail.setText(itemDataBean.getTitle());
        Log.i("getImtro", "initView: "+itemDataBean.getImtro());
        tvTitleDetail.bringToFront(); //设置view在最上层显示，目的是覆盖图片

        initView();

    }

    private void initView() {

        //菜品描述和食材辅料
        DetailImtroBurdenView detailImtroBurdenView = new DetailImtroBurdenView(this);
        detailImtroBurdenView.setDetailImtrorTxt(itemDataBean.getImtro());
        detailImtroBurdenView.setDetailBruden(itemDataBean.getIngredients() + ";"+itemDataBean.getBurden());
        anchorList.add(detailImtroBurdenView);
        container.addView(detailImtroBurdenView);

       /* //步骤
        DetailStepsView detailStepsView = new DetailStepsView(this);
        detailStepsView.setDetailStepAndRecommend(itemDataBean.getSteps(), 0);
        anchorList.add(detailStepsView);
        container.addView(detailStepsView);*/

        //步骤：0和推荐:1
        //填充scrollview
        for (int i = 0; i < tabTxt.length-1; i++) {
            DetailStepsView detailStepsView = new DetailStepsView(this);
            detailStepsView.setDetailStepAndRecommend(itemDataBean.getSteps(), i);
            anchorList.add(detailStepsView);
            container.addView(detailStepsView);
        }

        //tablayout设置标签
        for (int i = 0; i < tabTxt.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabTxt[i]));

            //设置tablayout的Indicato宽度
            tabLayout.post(new Runnable() {
                @Override
                public void run() {
                    SetTabLayoutIndicator.setIndicator(tabLayout, 50, 50);
                }
            });
        }


        //OnGlobalLayoutListener一般用于在onCreate方法动态的获取组件的高度和宽度，因为只有在onResume方法中才可以获取View的高度。
        /*listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int screenH = getScreenHeight();
                int statusBarH = getStatusBarHeight(RecipesDetailsActivity.this);
                int tabH = tabLayout.getHeight();
                //计算内容块所在的高度，全屏高度-状态栏高度-tablayout的高度-内容container的padding 16dp
                int lastH = screenH - statusBarH - tabH - 16 * 3;
                DetailStepsView lastView = (DetailStepsView) anchorList.get(anchorList.size() - 1);
                //当最后一个view 高度小于内容块高度时，设置其高度撑满
                if (lastView.getHeight() < lastH+20) {
                    Log.i("RecipesDetailsActivity", "onGlobalLayout: lastView+lastH"+lastView.getHeight()+"--"+lastH);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.height = lastH;
                    lastView.setLayoutParams(params);
                }
                container.getViewTreeObserver().removeOnGlobalLayoutListener(listener);

            }
        };
        container.getViewTreeObserver().addOnGlobalLayoutListener(listener);*/

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //点击标签，使scrollview滑动，isScroll置false
                isScroll = false;
                int pos = tab.getPosition();

                top = anchorList.get(pos).getTop();
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.smoothScrollTo(0, 0);
                        scrollView.smoothScrollTo(0, top);
                    }
                });

                //scrollView.smoothScrollTo(0, top);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //当滑动由scrollview触发时，isScroll 置true
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isScroll = true;
                }
                return false;
            }
        });


        //监听scrollview滑动
        scrollView.setCallbacks(new CustomScrollView.Callbacks() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                if (isScroll) {
                    for (int i = tabTxt.length - 1; i >= 0; i--) {
                        //根据滑动距离，对比各模块距离父布局顶部的高度判断
                        //需要y减去顶部内容区域的高度(具体看项目的高度，这里demo写死的200dp)
                        if (y > anchorList.get(i).getTop() - 10) {
                            setScrollPos(i);
                            break;
                        }
                    }
                }
            }
        });
    }

    //tablayout对应标签的切换
    private void setScrollPos(int newPos) {
        if (lastPos != newPos) {
            //该方法不会触发tablayout 的onTabSelected 监听
            tabLayout.setScrollPosition(newPos, 0, true);
        }
        lastPos = newPos;
    }


    /**
     * 获取屏幕高度
     *
     * @return
     */
    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public  int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                                 .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
