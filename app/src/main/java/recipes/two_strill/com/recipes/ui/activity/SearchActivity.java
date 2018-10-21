package recipes.two_strill.com.recipes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jakewharton.rxbinding2.view.RxView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.adapter.RecipesAdapter;
import recipes.two_strill.com.recipes.ui.bean.Api;
import recipes.two_strill.com.recipes.ui.bean.RecipesInfo;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.searchTextView)
    EditText mSearchTextView;
    @BindView(R.id.action_clear_btn)
    ImageView mActionClearBtn;
    @BindView(R.id.search_bar)
    RelativeLayout mSearchBar;
    @BindView(R.id.tv_close)
    TextView mTvClose;
    @BindView(R.id.recycler_view_result)
    RecyclerView mRecyclerViewResult;

    private RecipesAdapter mRecipesAdapter;

    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        mTvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mActionClearBtn.setImageDrawable(new IconicsDrawable(this, Ionicons.Icon.ion_ios_close_empty)
                                                 .color(ContextCompat.getColor(this, R.color.white)).sizeDp(16));

        RxView.clicks(mActionClearBtn).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mSearchTextView.setText("");
                mActionClearBtn.setVisibility(View.GONE);

            }
        });

        mSearchTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchTxt = mSearchTextView.getText().toString().trim();
                    initData(searchTxt);
                    Log.i("mSearchTextView", "onEditorAction: " + mSearchTextView.getText());

                    if (searchTxt.isEmpty()) {//输入框中内容是空，清除键隐藏
                        mActionClearBtn.setVisibility(View.GONE);
                    } else {
                        mActionClearBtn.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });
    }

    /**
     * 请求搜索的数据
     */
    private void initData(String searchText) {

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
        api.getSearch("69517ba5aaa57d42b55780cc22f852ce",searchText)
                    .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<RecipesInfo>() {
                    @Override
                    public void accept(RecipesInfo recipesInfo) throws Exception {

                        setDate(recipesInfo);
                    }
                });

    }

        /**
         * 填充recycleview数据
         * @param recipesInfo
         */
        private void setDate(final RecipesInfo recipesInfo) {

            Log.i("recipesInfo", "setDate: " + recipesInfo.toString());
            mRecipesAdapter = new RecipesAdapter(R.layout.template_recipesinfo, recipesInfo.getResult().getData());
            mRecyclerViewResult.setLayoutManager(new LinearLayoutManager(this));

            mRecyclerViewResult.setVisibility(View.VISIBLE);

           mRecipesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
               @Override
               public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                   Log.i("onItemChildClick", "onItemChildClick: "+position);
                   RecipesInfo.ResultBean.DataBean itemDataBean = recipesInfo.getResult().getData().get(position);
                   Intent intent = new Intent(SearchActivity.this, RecipesDetailsActivity.class);
                   intent.putExtra("itemDataBean", itemDataBean);
                   startActivity(intent);

               }
           });

            mRecyclerViewResult.setAdapter(mRecipesAdapter);

        }

    /**
     * 关闭软键盘
     */
    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
