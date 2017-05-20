package com.pengzhangdemo.com.myapplication.bigbigpeng3.videopath;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pengzhangdemo.com.myapplication.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends Activity implements OnItemClickListener {

    //    private String cur_path = "/storage/emulated/0/";
    private String cur_path;
    private List<Picture> listPictures;
    ListView listView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            if (msg.what == 0) {
                List<Picture> listPictures = (List<Picture>) msg.obj;
//				Toast.makeText(getApplicationContext(), "handle"+listPictures.size(), 1000).show();
                MyAdapter adapter = new MyAdapter(listPictures);
                listView.setAdapter(adapter);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        cur_path = getsaveDirectory();

        loadVaule();
    }

    private void loadVaule() {


        File file = new File(cur_path);

        File[] files = file.listFiles();

        listPictures = new ArrayList<>();

        if (files == null) {
//            Log.e(" zp ", "file.getAbsolutePath() = " + file.getAbsolutePath());
//            Log.e(" zp ", "files == null");
            return;
        }

        for (int i = 0; i < files.length; i++) {
            Picture picture = new Picture();
            picture.setBitmap(getVideoThumbnail(files[i].getPath(), 200, 200, MediaStore.Images.Thumbnails.MICRO_KIND));
            picture.setPath(files[i].getPath());
            listPictures.add(picture);

        }

        listView = (ListView) findViewById(R.id.lv_show);
        listView.setOnItemClickListener(this);
        Message msg = new Message();
        msg.what = 0;
        msg.obj = listPictures;

        handler.sendMessage(msg);

    }


    //获取视频的缩略图
    private Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
//		        System.out.println("w"+bitmap.getWidth());  
//		        System.out.println("h"+bitmap.getHeight());  
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }


    public class MyAdapter extends BaseAdapter {
        private List<Picture> listPictures;

        public MyAdapter(List<Picture> listPictures) {
            super();
            this.listPictures = listPictures;

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listPictures.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return listPictures.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View v, ViewGroup arg2) {
            // TODO Auto-generated method stu
            View view = getLayoutInflater().inflate(R.layout.item_video, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_show);
            TextView textView = (TextView) view.findViewById(R.id.tv_show);

            imageView.setImageBitmap(listPictures.get(position).getBitmap());


            int index = listPictures.get(position).getPath().lastIndexOf("/");
            String realName = listPictures.get(position).getPath().substring(index + 1);

            textView.setText(realName);

            return view;

        }
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1,
                            int arg2, long arg3) {

//        Toast.makeText(getApplicationContext(), "点击了" + arg2, Toast.LENGTH_SHORT).show();
        playVideo(listPictures.get(arg2).getPath());
        Log.e("path", listPictures.get(arg2).getPath());
    }

    //调用系统播放器   播放视频
    private void playVideo(String videoPath) {
//					   Intent intent = new Intent(Intent.ACTION_VIEW);
//					   String strend="";
//					   if(videoPath.toLowerCase().endsWith(".mp4")){
//						   strend="mp4";
//					   }
//					   else if(videoPath.toLowerCase().endsWith(".3gp")){
//						   strend="3gp";
//					   }
//					   else if(videoPath.toLowerCase().endsWith(".mov")){
//						   strend="mov";
//					   }
//					   else if(videoPath.toLowerCase().endsWith(".avi")){
//						   strend="avi";
//					   }
//					   intent.setDataAndType(Uri.parse(videoPath), "video/*");
//					   startActivity(intent);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        File file = new File(videoPath);
        intent.setDataAndType(Uri.fromFile(file), "video/*");
        startActivity(intent);
    }


    public String getsaveDirectory() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String rootDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "ScreenRecord" + "/";// + "ScreenRecord" + "/"

            File file = new File(rootDir);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    return null;
                }
            }
            return rootDir;
        } else {
            return null;
        }
    }

}
