package recipes.two_strill.com.recipes.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.adapter.DetailBurderAdapter;

public class DetailImtroBurdenView extends LinearLayout {

    private TextView tvImtro;
    private RecyclerView rvBurden;
    private Context mContext;


    public DetailImtroBurdenView(Context context) {
        this(context, null);
    }

    public DetailImtroBurdenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailImtroBurdenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        mContext = context;
    }


    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.details_imtro_burden, this, true);
        tvImtro = view.findViewById(R.id.tv_detail_imtro);
        rvBurden = view.findViewById(R.id.rv_detail_burden);
        /*Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        tvContent.setBackgroundColor(Color.rgb(r, g, b));*/
    }

    public void setDetailImtrorTxt(String txt) {
        if (!txt.isEmpty())
            tvImtro.setText(txt);
    }

    public void setDetailBruden(String txt) {
        Log.i("setDetailBruden", "setDetailBruden: "+txt);
        List<String> list = Arrays.asList(txt.split(";"));
        rvBurden.setLayoutManager(new LinearLayoutManager(mContext));
        rvBurden.setAdapter(new DetailBurderAdapter(R.layout.template_burder,list));
        //嵌套RecyclerView禁止滑动事件
        rvBurden.setNestedScrollingEnabled(false);
    }

}


