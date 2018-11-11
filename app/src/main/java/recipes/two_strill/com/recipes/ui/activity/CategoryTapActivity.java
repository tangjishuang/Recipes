package recipes.two_strill.com.recipes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.adapter.RecipesAdapter;
import recipes.two_strill.com.recipes.ui.bean.Api;
import recipes.two_strill.com.recipes.ui.bean.CategoryInfo;
import recipes.two_strill.com.recipes.ui.bean.RecipesInfo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryTapActivity extends AppCompatActivity {

    @BindView(R.id.tv_category_tap)
    TextView tvCategoryTap;
    @BindView(R.id.rv_category_tap)
    RecyclerView rvCategoryTap;
    private Api api;
    private RecipesAdapter mRecipesAdapter;

    //详情数据
    public CategoryInfo.ResultBean.ListBean itemDataBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_tap);
        ButterKnife.bind(this);

        itemDataBean = (CategoryInfo.ResultBean.ListBean) getIntent().getSerializableExtra("CategorylistBean");


        initDate(itemDataBean);

    }

    private void initDate(CategoryInfo.ResultBean.ListBean itemDataBean){
        tvCategoryTap.setText(itemDataBean.getName());

        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://apis.juhe.cn/cook/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .build();

        api = retrofit.create(Api.class);

        api.getSearch("69517ba5aaa57d42b55780cc22f852ce",itemDataBean.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<RecipesInfo>() {
                    @Override
                    public void accept(RecipesInfo recipesInfo) throws Exception {

                        setDate(recipesInfo);
                    }
                });

    }


    public void setDate(final RecipesInfo recipesInfo) {
        Log.i("recipesInfo", "setDate: " + recipesInfo.toString());
        mRecipesAdapter = new RecipesAdapter(R.layout.template_recipesinfo, recipesInfo.getResult().getData());
        rvCategoryTap.setLayoutManager(new LinearLayoutManager(this));

        mRecipesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i("onItemChildClick", "onItemChildClick: "+position);
                RecipesInfo.ResultBean.DataBean itemDataBean = recipesInfo.getResult().getData().get(position);
                Intent intent = new Intent(CategoryTapActivity.this, RecipesDetailsActivity.class);
                intent.putExtra("itemDataBean", itemDataBean);
                startActivity(intent);

            }
        });

        rvCategoryTap.setAdapter(mRecipesAdapter);
    }
}
