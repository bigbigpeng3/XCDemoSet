package com.pengzhangdemo.com.myapplication.tag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.utils.TagColorUtils;

import java.util.List;


/**
 * PrepareTagAdapter
 * Created by zp on 4/15/17.
 */
public class PrepareTagAdapter extends RecyclerView.Adapter<PrepareTagAdapter.TagViewHolder> {


    private Context context;
    private List<String> mDatas;

    public PrepareTagAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TagViewHolder(LayoutInflater.from(context).inflate(R.layout.item_prepare_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {

        // 第一个tag字体颜色为黑，其它为白
        if (position == 0) {
            holder.titleText.setTextColor(context.getApplicationContext().getResources().getColor(R.color.white));
            holder.titleText.setCompoundDrawablesWithIntrinsicBounds(context.getApplicationContext().getResources().getDrawable(R.drawable.icon_24_tag), null, null, null);
            holder.titleText.setCompoundDrawablePadding(20);
            holder.titleText.setText("热门标签");
            holder.rlTagBg.setBackgroundColor(context.getApplicationContext().getResources().getColor(R.color.transparent));
            return;

        } else if (position == 2) {
            holder.titleText.setTextColor(context.getApplicationContext().getResources().getColor(R.color.tag_text_color_black));
        } else {
            // 因为复用问题，所以必须要写else语句
            holder.titleText.setTextColor(context.getApplicationContext().getResources().getColor(R.color.tag_text_color_white));
        }


        holder.titleText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
//        holder.titleText.setText("# " + mDatas.get(position - 1));
        holder.titleText.setText( mDatas.get(position - 1));
        // 不允许出现 1 以下的数字
        int realIndex = position;
        // 调整对应的Tag颜色
        holder.rlTagBg.setBackground(TagColorUtils.switchColor(realIndex, true));

//        if (realIndex % 4 == 0) {
//            holder.rlTagBg.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.shape_home_tag_bg1));
//        } else if (realIndex % 3 == 0) {
//            holder.rlTagBg.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.shape_home_tag_bg2));
//        } else if (realIndex % 2 == 0) {
//            holder.rlTagBg.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.shape_home_tag_bg3));
//        } else {
//            holder.rlTagBg.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.shape_home_tag_bg1));
//        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }


    class TagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleText;
        RelativeLayout rlTagBg;

        TagViewHolder(View view) {
            super(view);
            titleText = (TextView) view.findViewById(R.id.t_tag_title);
            rlTagBg = (RelativeLayout) view.findViewById(R.id.rl_tag_bg);
            rlTagBg.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }


    private TagItemClickListener itemClickListener;
    public void setOnTagItemClickListener(TagItemClickListener listener){
        itemClickListener = listener;
    }
    public interface TagItemClickListener{
        void onItemClick(View v, int position);
    }



}
