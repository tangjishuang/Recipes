package recipes.two_strill.com.recipes.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import recipes.two_strill.com.recipes.R;

/**
 * 食材
 * Created by SD on 2018/8/18.
 */

public class StuffFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stuff, container, false);
        return view;
    }
}
