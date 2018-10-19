package recipes.two_strill.com.recipes.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import recipes.two_strill.com.recipes.R;
import recipes.two_strill.com.recipes.ui.bean.City;
import recipes.two_strill.com.recipes.ui.view.AnchorView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private String[] tabTxt;
    private int lastH;
    private List<City> hotList;

    public MyAdapter(Context context, String[] tabTxt, int lastH,List<City> hotList) {
        this.context = context;
        this.tabTxt = tabTxt;
        this.lastH = lastH;
        this.hotList = hotList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_view, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.anchorView.setContentTxt(tabTxt[position],hotList);
        holder.anchorView.setAnchorTxt(tabTxt[position]);




        //判断最后一个view
        if (position == tabTxt.length - 1) {
            if (holder.anchorView.getHeight() < lastH) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.height = lastH;
                holder.anchorView.setLayoutParams(params);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 17;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private AnchorView anchorView;

        public MyViewHolder(View itemView) {
            super(itemView);
            anchorView = itemView.findViewById(R.id.anchorView);
        }
    }


}
