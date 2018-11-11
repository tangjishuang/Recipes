package recipes.two_strill.com.recipes.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import recipes.two_strill.com.recipes.R;

/**
 * Created by SD on 2018/8/31.
 */

public class DetailBurderAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public DetailBurderAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {


        String[] arr = item.split(",");
        String foodName = arr[0];
        String dosage = arr[1];

        Log.i("foodName", "convert: "+foodName);

        helper.setText(R.id.tv_food_name, foodName).setText(R.id.tv_dosage, dosage);


    }
}
