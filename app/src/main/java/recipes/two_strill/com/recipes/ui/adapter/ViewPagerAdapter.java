package recipes.two_strill.com.recipes.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import recipes.two_strill.com.recipes.ui.bean.FragmentInfo;
import recipes.two_strill.com.recipes.ui.fragment.CategoryFragment;
import recipes.two_strill.com.recipes.ui.fragment.MineFragment;
import recipes.two_strill.com.recipes.ui.fragment.RecommendFragment;
import recipes.two_strill.com.recipes.ui.fragment.StuffFragment;

/**
 *
 * Created by SD on 2018/8/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private List<FragmentInfo> mFragments = new ArrayList<>(4);

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragment();
    }

    private void initFragment() {

        mFragments.add(new FragmentInfo("推荐", RecommendFragment.class));
        mFragments.add(new FragmentInfo("分类", CategoryFragment.class));
        mFragments.add(new FragmentInfo("食材", StuffFragment.class));
        mFragments.add(new FragmentInfo("我的", MineFragment.class));


    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }


}
