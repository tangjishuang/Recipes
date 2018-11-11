package recipes.two_strill.com.recipes.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.Widget.BannerLayout;
import recipes.two_strill.com.recipes.ui.activity.RecipesDetailsActivity;
import recipes.two_strill.com.recipes.ui.activity.RecipesDetailsActivity_ViewBinding;
import recipes.two_strill.com.recipes.ui.bean.RecipesInfo;
import recipes.two_strill.com.recipes.ui.imageloader.ImageLoader;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by SD on 2018/9/4.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_BANNER = 1;
    public static final int TYPE_RECOMM = 2;



    private LayoutInflater layoutInflater;

    private RecipesInfo.ResultBean mResultBean;
    private RecipesInfo.ResultBean mRecommResultBean;

    private Context mContext;


    public RecommendAdapter(Context context) {

        layoutInflater = LayoutInflater.from(context);
        mContext = context;

    }

    public void setData(RecipesInfo.ResultBean resultBean,RecipesInfo.ResultBean recommResultBean) {
        mResultBean = resultBean;
        mRecommResultBean = recommResultBean;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_RECOMM;
        }

        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_BANNER) {
            return new BannerViewHolder(layoutInflater.inflate(R.layout.template_banner, parent, false));
        } else if (viewType == TYPE_RECOMM) {
            return new RecommViewHolder(layoutInflater.inflate(R.layout.detail_steps, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position == 0) {

            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;

            final List<RecipesInfo.ResultBean.DataBean> mDatabean = mResultBean.getData();
            List<String> urls = new ArrayList<>(mDatabean.size());

            for (RecipesInfo.ResultBean.DataBean dataBean : mDatabean) {

                urls.add(dataBean.getAlbums().get(0));

            }

            bannerViewHolder.banner.setViewUrls(urls);

            /**
             * banner点击事件
             */
            bannerViewHolder.banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    RecipesInfo.ResultBean.DataBean dataBean = mDatabean.get(position);
                    Intent intent = new Intent(mContext, RecipesDetailsActivity.class);
                    intent.putExtra("itemDataBean", dataBean);
                    startActivity(mContext, intent, null);
                }
            });

        } else if (position == 1) {

            RecommViewHolder recommViewHolder = (RecommViewHolder) holder;

            RecipesAdapter recipesAdapter = new RecipesAdapter(R.layout.template_recipesinfo, mRecommResultBean.getData());
            recommViewHolder.rvSteps.setLayoutManager(new LinearLayoutManager(mContext));
            recommViewHolder.rvSteps.setAdapter(recipesAdapter);

            recipesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                    Intent intent = new Intent(mContext, RecipesDetailsActivity.class);
                    intent.putExtra("itemDataBean", mRecommResultBean.getData().get(position));
                    startActivity(mContext,intent,null);

                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        BannerLayout banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            banner.setImageLoader(new ImgLoader());


        }
    }

    class RecommViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rv_steps)
        RecyclerView rvSteps;

        public RecommViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            /*
            设置不能滑动
             */
            rvSteps.setLayoutManager(new LinearLayoutManager(mContext) {

                @Override
                public boolean canScrollVertically() {
                    return false;
                }

                @Override
                public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                    super.onMeasure(recycler, state, widthSpec, heightSpec);
                }
            });



        }

    }

    class ImgLoader implements BannerLayout.ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.load(path, imageView);
        }

    }

}
