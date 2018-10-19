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
 * Created by SD on 2018/8/25.
 */

public class RecipesAdapter extends BaseQuickAdapter<RecipesInfo.ResultBean.DataBean,BaseViewHolder> {


    public RecipesAdapter(int layoutResId, @Nullable List<RecipesInfo.ResultBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecipesInfo.ResultBean.DataBean item) {

        helper.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_ingredients, "食材:"+item.getIngredients()+"  辅料:"+item.getBurden())
        .addOnClickListener(R.id.rl_template_recipesinfo);


        //Glid加载网络图片
        /**
         * Glide.with(mContext)
         .load(path)
         .asGif()
         .override(200,200)
         .diskCacheStrategy(DiskCacheStrategy.SOURCE)
         .placeholder(R.mipmap.loading_image)
         //.thumbnail(1f)
         .crossFade()
         .error(R.mipmap.error)
         .transform(new GlideCircleTransform(mContext))
         .into(mImageView);
         --参数说明
         with() 传入对象 Context,Activity,Fragment，Glide会与当前传入值的生命周期一致。
         load() 对象: String(文件路径，网络地址)，File(文件资源)，Integer(资源id)。
         asGif() 表示的gif动画，asBitmap：表示静态图。
         diskCacheStrategy() 表示磁盘缓存策略。
         1.DiskCacheStrategy.RESULT:展示小大的图片缓存
         2.DiskCacheStrategy.ALL; 展示在控件中大小图片尺寸和原图都会缓存
         3.DiskCacheStrategy.NONE：不设置缓存
         4.DiskCacheStrategy.SOURCE：原图缓存
         override() 设置图片的width，height
         placeholder() 设置占位图
         error() 设置失败图
         thumbnail() 缩略图显示传入值(0-1f)
         crossFade() 显示动画-淡入淡出
         transform() 设置图片圆角或圆形显示(继承 BitmapTransformation)这都是很常用的哦
         centerCrop() 图片显示类型 fitCenter();
         into() 显示到ImageView上

         作者：王元_Trump
         链接：https://www.jianshu.com/p/e78407a18716
         來源：简书
         简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
         */

        ImageView logoview = helper.getView(R.id.iv_albums);
        Glide.with(mContext).load(item.getAlbums().get(0)).placeholder(R.mipmap.test).crossFade().error(R.mipmap.test).into((ImageView) helper.getView(R.id.iv_albums));
     /*   Glide.with(mContext).load(item.getAlbums().get(0)).crossFade()
                .into((ImageView) helper.getView(R.id.iv_albums));*/

    }
}
