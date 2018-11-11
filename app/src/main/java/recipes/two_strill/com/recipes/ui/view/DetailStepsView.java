package recipes.two_strill.com.recipes.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.adapter.DetailBurderAdapter;
import recipes.two_strill.com.recipes.ui.adapter.DetailRecommAdapter;
import recipes.two_strill.com.recipes.ui.adapter.DetailStepsAdapter;
import recipes.two_strill.com.recipes.ui.bean.Api;
import recipes.two_strill.com.recipes.ui.bean.RecipesInfo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailStepsView extends LinearLayout {

    private Api api;
    private RecipesInfo mRecipesInfo;
    private RecyclerView rvSteps;
    private Context mContext;


    public DetailStepsView(Context context) {
        this(context, null);
    }

    public DetailStepsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailStepsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        mContext = context;
    }


    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_steps, this, true);
        rvSteps = view.findViewById(R.id.rv_steps);
        /*Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        tvContent.setBackgroundColor(Color.rgb(r, g, b));*/

    }

    /*public void setDetailImtrorTxt(String txt) {
        if (!txt.isEmpty())
            tvImtro.setText(txt);
    }*/

    public void setDetailStepAndRecommend(List<RecipesInfo.ResultBean.DataBean.StepsBean> steps, int i) {
        Log.i("setDetailStepRecommend", "setDetailStepAndRecommend: ");

        if (i == 0) {

            rvSteps.setLayoutManager(new LinearLayoutManager(mContext));
            rvSteps.setAdapter(new DetailStepsAdapter(R.layout.template_steps, steps));
            //嵌套RecyclerView禁止滑动事件
            rvSteps.setNestedScrollingEnabled(false);

        } else {

            Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://apis.juhe.cn/cook/")
                                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

            api = retrofit.create(Api.class);

            api.getSearch("69517ba5aaa57d42b55780cc22f852ce","西瓜")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<RecipesInfo>() {
                        @Override
                        public void accept(RecipesInfo recipesInfo) throws Exception {
                            rvSteps.setLayoutManager(new LinearLayoutManager(mContext));
                            rvSteps.setAdapter(new DetailRecommAdapter(R.layout.template_recipes_recommend, recipesInfo.getResult().getData()));
                            rvSteps.setNestedScrollingEnabled(false);
                        }
                    });


        }
    }

}


