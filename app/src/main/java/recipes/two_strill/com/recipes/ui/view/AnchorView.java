package recipes.two_strill.com.recipes.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.bean.City;

public class AnchorView extends LinearLayout {

    private TextView tvAnchor;
    //private TextView tvContent;

    private ArrayList<City> city_hot;

    public AnchorView(Context context) {
        this(context, null);
    }

    public AnchorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnchorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        city_hot = new ArrayList<City>();
        hotCityInit();
        init(context);
    }


    /**
     * 热门城市
     */
    public void hotCityInit() {
        City city = new City("快手菜", "2");
        city_hot.add(city);
        city = new City("家常菜", "2");
        city_hot.add(city);
        city = new City("创意菜", "2");
        city_hot.add(city);
        city = new City("素菜", "2");
        city_hot.add(city);
        city = new City("烘焙", "2");
        city_hot.add(city);
        city = new City("面食", "2");
        city_hot.add(city);
        city = new City("汤", "2");
        city_hot.add(city);
        city = new City("东北菜", "2");
        city_hot.add(city);
        city = new City("川菜", "2");
        city_hot.add(city);
        city = new City("粤菜", "2");
        city_hot.add(city);
        city = new City("京菜", "2");
        city_hot.add(city);
    }
    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_anchor, this, true);
        tvAnchor = view.findViewById(R.id.tv_anchor);
        /*tvContent = view.findViewById(R.id.tv_content);*/
        GridView hotCity = view.findViewById(R.id.recent_city);
        hotCity.setAdapter(new HotCityAdapter(context, city_hot));

        /*Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        tvContent.setBackgroundColor(Color.rgb(r, g, b));*/
    }

    public void setAnchorTxt(String txt) {
        tvAnchor.setText(txt);
    }

    /*public void setContentTxt(String txt, List<City> hotList) {
        tvContent.setText(txt);
    }*/

    class HotCityAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<City> hotCitys;

        public HotCityAdapter(Context context, List<City> hotCitys) {
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            this.hotCitys = hotCitys;
        }

        @Override
        public int getCount() {
            return hotCitys.size();
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
            TextView city = (TextView) convertView.findViewById(R.id.city);
            city.setText(hotCitys.get(position).getName());
            return convertView;
        }
    }

}
