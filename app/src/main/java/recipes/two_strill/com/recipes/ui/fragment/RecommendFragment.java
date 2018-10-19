package recipes.two_strill.com.recipes.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.adapter.RecommendAdapter;
import recipes.two_strill.com.recipes.ui.bean.Api;
import recipes.two_strill.com.recipes.ui.bean.RecipesInfo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 推荐
 * Created by SD on 2018/8/18.
 */

public class RecommendFragment extends Fragment {


    private RecyclerView rv_recomm;
    private Api api;
    private RecommendAdapter mRecipesAdapter;
    private RecipesInfo binnereInfo;//binner数据

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        rv_recomm = view.findViewById(R.id.rv_recomm);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

    }

    /**
     * 请求搜索的数据
     */
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


        //menu=西瓜&dtype=json&pn=0&rn=10&albums=&key=69517ba5aaa57d42b55780cc22f852ce
        api.getBinner("69517ba5aaa57d42b55780cc22f852ce","时令",5)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<RecipesInfo>() {
                    @Override
                    public void accept(RecipesInfo recipesInfo) throws Exception {
                        binnereInfo = recipesInfo;
                        /*setDate(recipesInfo);*/

                        //请求推荐数据
                        api.getSearch("69517ba5aaa57d42b55780cc22f852ce", "黄花菜")
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Consumer<RecipesInfo>() {
                                    @Override
                                    public void accept(RecipesInfo mrecipesInfo) throws Exception {
                                        /*recommRecipesInfo = recipesInfo;*/

                                        setDate(binnereInfo,mrecipesInfo);
                                    }
                                });
                    }
                });


    }

    /**
     * 填充recycleview数据
     * @param recipesInfo
     * @param mrecipesInfo
     */
    private void setDate(final RecipesInfo recipesInfo, RecipesInfo mrecipesInfo) {

        Log.i("recipesInfo", "setDate: " + recipesInfo.toString());
        mRecipesAdapter = new RecommendAdapter(getActivity());
        mRecipesAdapter.setData(recipesInfo.getResult(),mrecipesInfo.getResult());
        rv_recomm.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*mRecyclerViewResult.setVisibility(View.VISIBLE);

        mRecipesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i("onItemChildClick", "onItemChildClick: "+position);
                RecipesInfo.ResultBean.DataBean itemDataBean = recipesInfo.getResult().getData().get(position);
                Intent intent = new Intent(SearchActivity.this, RecipesDetailsActivity.class);
                intent.putExtra("itemDataBean", itemDataBean);
                startActivity(intent);

            }
        });*/

        rv_recomm.setAdapter(mRecipesAdapter);

    }


}
