package recipes.two_strill.com.recipes.ui.view;

import android.content.Context;
import android.graphics.Color;
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

import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.adapter.DetailRecommAdapter;
import recipes.two_strill.com.recipes.ui.bean.Api;
import recipes.two_strill.com.recipes.ui.bean.RecipesInfo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecommView extends LinearLayout {

    private RecyclerView rv_recomm;
    private Api api;
    private Context mContext;

    public RecommView(Context context) {
        this(context, null);
    }

    public RecommView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecommView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        mContext = context;
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_recommend, this, true);
        rv_recomm = view.findViewById(R.id.rv_recomm);
        Log.i("RecommView", "init:RecommView ");
    }

    public void setRecommData(String txt) {
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
                        rv_recomm.setLayoutManager(new LinearLayoutManager(mContext));
                        rv_recomm.setAdapter(new DetailRecommAdapter(R.layout.template_recipes_recommend, recipesInfo.getResult().getData()));
                        rv_recomm.setNestedScrollingEnabled(false);
                    }
                });
    }


}
