package recipes.two_strill.com.recipes.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.bean.RecipesInfo;

/**
 * Created by SD on 2018/9/1.
 */

public class DetailRecommAdapter extends BaseQuickAdapter<RecipesInfo.ResultBean.DataBean, BaseViewHolder> {


    public DetailRecommAdapter(int layoutResId, @Nullable List<RecipesInfo.ResultBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecipesInfo.ResultBean.DataBean item) {

        helper.setText(R.id.tv_title_recomm, item.getTitle())
                .setText(R.id.tv_ingredients_recomm, "食材:" + item.getIngredients() + "  辅料:" + item.getBurden())
                .addOnClickListener(R.id.rl_template_recomm);
        Glide.with(mContext).load(item.getAlbums().get(0)).placeholder(R.mipmap.test).crossFade().error(R.mipmap.test).into((ImageView) helper.getView(R.id.iv_albums_recomm));
    }

}
