package recipes.two_strill.com.recipes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private List<String> titles;

    private int[] tabIcons = {R.drawable.selector_recommend, R.drawable.selector_category
            , R.drawable.selector_stuff, R.drawable.selector_mine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initTablayout();
        initView();
    }

    private void initView() {

        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTablayout() {

        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        titles = new ArrayList<>(4);
        titles.add("推荐");
        titles.add("分类");
        titles.add("食材");
        titles.add("我的");

        setupTabIcons();

        mViewPager.setCurrentItem(1);
        mViewPager.setCurrentItem(0);

    }

    private void setupTabIcons() {

        mTabLayout.getTabAt(0).setCustomView(getTabView(0));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
        mTabLayout.getTabAt(2).setCustomView(getTabView(2));
        mTabLayout.getTabAt(3).setCustomView(getTabView(3));
    }

    private View getTabView(int position) {

        Log.i("titles", "getTabView: " + titles.get(position));

        View view = View.inflate(this, R.layout.template_tab, null);
        TextView mTabText = view.findViewById(R.id.tab_text);
        mTabText.setText(titles.get(position));
        ImageView mTabImage = view.findViewById(R.id.tab_image);
        mTabImage.setImageResource(tabIcons[position]);
        return view;
    }

}
