package com.pengzhangdemo.com.myapplication.bigbigpeng3.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pengzhangdemo.com.myapplication.R;
import com.pengzhangdemo.com.myapplication.utils.LogUtils;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;

/**
 * 通过 ijkplayer 拉流
 */
public class PullStreamActivity extends AppCompatActivity {


    private PLVideoTextureView mVideoView;


    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        setContentView(R.layout.activity_pull_stream);
        mVideoView = (PLVideoTextureView) findViewById(R.id.VideoView);

        url = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
//        url = "http://23340.live-vod.cdn.aodianyun.com/m3u8/0x0/merge/02235e76590a08db99d2296f8632c5cf.m3u8";
//        url = "http://a.img.shouyintv.cn/AaUC201-big";
//        url = "http://flv.quanmin.tv/live/2078181740.flv";
//        url = "rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov";



        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        mVideoView.setMirror(false);
        //真实情况下使用注释的方式
//        mVideoView.setVideoPath(SystemConfig.ucloud_player_url + liveId);
        //临时固定地址使用
        mVideoView.setVideoPath(url);
        mVideoView.start();


        mVideoView.setOnPreparedListener(new PLMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(PLMediaPlayer plMediaPlayer) {
                LogUtils.d("onPrepared:" + url);

            }
        });


        mVideoView.setOnBufferingUpdateListener(new PLMediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int i) {
                if (i > 0)
                    LogUtils.d("onBufferingUpdate|" + i);
            }
        });
        mVideoView.setOnCompletionListener(new PLMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(PLMediaPlayer plMediaPlayer) {
                LogUtils.d("onCompletion");
            }
        });
        mVideoView.setOnInfoListener(new PLMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
                LogUtils.d("onInfo|i:" + i + "--i1:" + i1);
                return false;
            }
        });

        mVideoView.setOnErrorListener(new PLMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
                LogUtils.w("onError:i:" + i);

                return false;
            }
        });


    }


    private void start() {
        if (mVideoView != null)
            mVideoView.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        start();
    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    public int getDisplayAspectRatio() {
        return mVideoView.getDisplayAspectRatio();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlayback();
    }


    public void pause() {
        if (mVideoView != null)
            mVideoView.pause();
    }

    public void stopPlayback() {
        if (mVideoView != null)
            mVideoView.stopPlayback();

    }

}
