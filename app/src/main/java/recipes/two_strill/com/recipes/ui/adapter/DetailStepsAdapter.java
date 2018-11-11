package recipes.two_strill.com.recipes.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
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

public class DetailStepsAdapter extends BaseQuickAdapter<RecipesInfo.ResultBean.DataBean.StepsBean, BaseViewHolder> {

    public DetailStepsAdapter(int layoutResId, @Nullable List<RecipesInfo.ResultBean.DataBean.StepsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecipesInfo.ResultBean.DataBean.StepsBean item) {
        String step = item.getStep();
        //截取字符串
        String num = step.substring(0,step.indexOf("."));//截取从下标0开始到.前面的字符串
        String str = step.substring(step.indexOf(".") + 1);//截取从.开始后面的字符串
        Log.i("DetailStepsAdapter", "convert: "+num+str);

        helper.setText(R.id.tv_steps_num,"步骤"+num).setText(R.id.tv_step, str);

        Glide.with(mContext).load(item.getImg()).placeholder(R.mipmap.test).crossFade().error(R.mipmap.test).into((ImageView) helper.getView(R.id.iv_step_img));

    }
}
