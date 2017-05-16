package com.pengzhangdemo.com.myapplication;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pengzhangdemo.com.myapplication.utils.LogUtils;

import java.util.HashMap;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.AndroidDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.ViewCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;

/**
 * 用户弹幕UI
 */
public class DanmuActivity extends AppCompatActivity implements View.OnClickListener {


    private IDanmakuView mDanmakuView;

    private DanmakuContext mContext;

    private Button btnSendDanmu;


    HashMap<Integer, Integer> maxLinesPair;
    HashMap<Integer, Boolean> overlappingEnablePair;

    private BaseDanmakuParser mParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmu);
        mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
        btnSendDanmu = (Button) findViewById(R.id.btn_sendDanmu);


        btnSendDanmu.setOnClickListener(this);


        // 设置最大显示行数
        maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 6); // 滚动弹幕最大显示5行

        // 设置是否禁止重叠
        overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        mContext = DanmakuContext.create();
        mContext.setDanmakuBold(true);
        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 6)
//                .setMaximumVisibleSizeInScreen(6)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(3f)
                .setScaleTextSize(1f);


        initDanmuView();
    }

    @Override
    public void onClick(View v) {
        if (v == btnSendDanmu){
            addDanmaku(true);
//            initData();
        }
    }


    public class ItemViewHolder extends ViewCacheStuffer.ViewHolder {

        private final ImageView mIcon;
        private final ImageView ivGif;
        private final TextView mText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.icon);
            ivGif = (ImageView) itemView.findViewById(R.id.iv_gif);
            mText = (TextView) itemView.findViewById(R.id.text);
        }

    }


    public void initDanmuView() {

        mContext.setCacheStuffer(new ViewCacheStuffer<ItemViewHolder>() {

            @Override
            public int getItemViewType(int position, BaseDanmaku danmaku) {
                return super.getItemViewType(position, danmaku);
            }

            @Override
            public ItemViewHolder onCreateViewHolder(int viewType) {
                Log.e("DFM", "onCreateViewHolder:" + viewType);
                return new ItemViewHolder(View.inflate(getApplicationContext(), R.layout.item_layout_view_cache, null));
            }

            @Override
            public void onBindViewHolder(int viewType, ItemViewHolder viewHolder, BaseDanmaku danmaku, AndroidDisplayer.DisplayerConfig displayerConfig, TextPaint paint) {
                if (paint != null)
//                    viewHolder.mText.getPaint().set(paint);

                viewHolder.mText.setText(danmaku.text);

                viewHolder.ivGif.setImageResource(R.drawable.anim_fish_blue);
                AnimationDrawable drawable = (AnimationDrawable)viewHolder.ivGif.getDrawable();
                drawable.start();

//                viewHolder.mText.setTextColor(danmaku.textColor);
//                viewHolder.mText.setTextSize(TypedValue.COMPLEX_UNIT_PX, danmaku.textSize);

//                MyImageWare imageWare = (MyImageWare) danmaku.tag;
//                if (imageWare != null) {
//                    bitmap = imageWare.bitmap;
//                    if (danmaku.text.toString().contains("textview")) {
//                        Log.e("DFM", "onBindViewHolder======> bitmap:" + (bitmap == null) + "  " + danmaku.tag + "url:" + imageWare.getImageUri());
//                    }
//                }
//                if (bitmap != null) {
//                    viewHolder.mIcon.setImageBitmap(bitmap);
//                    if (danmaku.text.toString().contains("textview")) {
//                        Log.e("DFM", "onBindViewHolder======>" + danmaku.tag + "url:" + imageWare.getImageUri());
//                    }
//                } else {
//                    viewHolder.mIcon.setImageResource(R.drawable.ic_launcher);
//                }
            }

            @Override
            public void releaseResource(BaseDanmaku danmaku) {
//                MyImageWare imageWare = (MyImageWare) danmaku.tag;
//                if (imageWare != null) {
//                    ImageLoader.getInstance().cancelDisplayTask(imageWare);
//                }
//                danmaku.setTag(null);
//                Log.e("DFM", "releaseResource url:" + danmaku.text);
            }


            @Override
            public void prepare(BaseDanmaku danmaku, boolean fromWorkerThread) {
                if (danmaku.isTimeOut()) {
                    return;
                }
//                mDanmakuView.start();
//                MyImageWare imageWare = (MyImageWare) danmaku.tag;
//                if (imageWare == null) {
//                    String avatar = avatars[danmaku.index % avatars.length];
//                    imageWare = new MyImageWare(avatar, danmaku, mIconWidth, mIconWidth, mDanmakuView);
//                    danmaku.setTag(imageWare);
//                }
//                if (danmaku.text.toString().contains("textview")) {
//                    Log.e("DFM", "onAsyncLoadResource======>" + danmaku.tag + "url:" + imageWare.getImageUri());
//                }
//                ImageLoader.getInstance().displayImage(imageWare.getImageUri(), imageWare);
            }

        }, null).setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);


        mDanmakuView.setOnDanmakuClickListener(new IDanmakuView.OnDanmakuClickListener() {

            @Override
            public boolean onDanmakuClick(IDanmakus danmakus) {
                Log.d("DFM", "onDanmakuClick: danmakus size:" + danmakus.size());
                BaseDanmaku latest = danmakus.last();
                if (null != latest) {
                    Log.d("DFM", "onDanmakuClick: text of latest danmaku:" + latest.text);
                    Toast.makeText(DanmuActivity.this, "弹幕" + latest.text, Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onDanmakuLongClick(IDanmakus danmakus) {
                return false;
            }

            @Override
            public boolean onViewClick(IDanmakuView view) {
//                mMediaController.setVisibility(View.VISIBLE);

                return false;
            }
        });


        if (mDanmakuView != null) {
//            mParser = createParser(this.getResources().openRawResource(R.raw.comments));
            mDanmakuView.setCallback(new DrawHandler.Callback() {
                @Override
                public void updateTimer(DanmakuTimer timer) {
                }

                @Override
                public void drawingFinished() {

                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {
//                    Log.d("DFM", "danmakuShown(): text=" + danmaku.text);
                }

                @Override
                public void prepared() {
                    mDanmakuView.start();
                }
            });
        }


        //这里原本是一个解析器，可以使用库里提供A站或B站的解析器，也可以自己写一个，但由于项目中获取到的数据已经是model，这里就没有使用这个。
        mDanmakuView.prepare(new BaseDanmakuParser() {
            @Override
            protected Danmakus parse() {
                return new Danmakus();
            }
        }, mContext);


//        mDanmakuView.prepare(null, mContext);
        mDanmakuView.showFPS(true);
        mDanmakuView.enableDanmakuDrawingCache(true);


//        initData();
        LogUtils.e(" initData();");

    }

    private void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 100; i++) {
                    addDanmaku(true);
                    LogUtils.e("  addDanmaku(true);;");
                }

            }
        }).start();

    }


    private void addDanmaku(boolean islive) {

        LogUtils.e(" addDanmaku ");
        BaseDanmaku danmaku = mContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || mDanmakuView == null) {
            LogUtils.e(" danmaku == null  " + (danmaku == null) + ",mDanmakuView == null " + (mDanmakuView == null));
            return;
        }
        // for(int i=0;i<100;i++){
        // }
        danmaku.text = "这是一条弹幕" + System.nanoTime();
//        danmaku.padding = 5;
        danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
        danmaku.isLive = islive;
        danmaku.setTime(mDanmakuView.getCurrentTime());
//        danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
//        danmaku.textColor = Color.RED;
//        danmaku.textShadowColor = Color.WHITE;
        // danmaku.underlineColor = Color.GREEN;
//        danmaku.borderColor = Color.GREEN;
        mDanmakuView.addDanmaku(danmaku);
    }



    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
//        ImageLoader.getInstance().destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }


}
