package recipes.two_strill.com.recipes.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.activity.CategoryTapActivity;
import recipes.two_strill.com.recipes.ui.bean.CategoryInfo;

public class AnchorView extends LinearLayout {

    private TextView tvAnchor;
    private GridView hotCity;
    private Context mContext;

    public AnchorView(Context context) {
        this(context, null);
    }

    public AnchorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnchorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_anchor, this, true);
        tvAnchor = view.findViewById(R.id.tv_anchor);
        hotCity = view.findViewById(R.id.recent_city);

    }

    public void setAnchorTxt(String txt) {
        tvAnchor.setText(txt);
    }

    public void setCategoryTxt(final List<CategoryInfo.ResultBean.ListBean> resultBeanlist) {

        hotCity.setAdapter(new CategoryListAdapter(mContext, resultBeanlist));
        hotCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext,
                        resultBeanlist.get(i).getName(), Toast.LENGTH_SHORT).show();
                CategoryInfo.ResultBean.ListBean listBean = resultBeanlist.get(i);

                Intent intent = new Intent(mContext, CategoryTapActivity.class);
                intent.putExtra("CategorylistBean", listBean);
                mContext.startActivity(intent);

            }
        });
    }


    class CategoryListAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<CategoryInfo.ResultBean.ListBean> resultBeanlist;

        public CategoryListAdapter(Context context, List<CategoryInfo.ResultBean.ListBean> resultBeanList) {
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            this.resultBeanlist = resultBeanList;
        }

        @Override
        public int getCount() {
            return resultBeanlist.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.item_city, null);
            TextView cai = (TextView) convertView.findViewById(R.id.city);
            cai.setText(resultBeanlist.get(position).getName());
            return convertView;
        }
    }

}
