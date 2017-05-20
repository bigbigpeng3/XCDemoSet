package com.pengzhangdemo.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pengzhangdemo.com.myapplication.bigbigpeng3.BigBigPengMainActivity;

/**
 *
 *
 *
 * 起始于2017/05/20 (ps:窝草，520我还在敲代码)
 *
 * <p>
 * 注意事项:
 * <p>
 * 1、上传代码注意上传好资源，一定不能让其他人出现资源报错。还不太熟悉Git的话。参考:
 * http://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000
 * PS：最好不要上传自己根目录的 build.gradle。这样会导致不同AS的版本问题。给其他成员造成困扰。
 * <p>
 * 2、Demo内的项目必须能运行，符合自己的项目要求后再上传，保证Demo的质量。拒绝 System.out.println("Hellow World"); 式的Demo。
 * <p>
 * 3、项目成员需要项目帮助，解决了对方的问题就是解决了将来自己可能遇到的问题。项目的速度就会提升，做项目也更有信心！
 * <p>
 * 4、如果对方帮助力度比较大，受帮助方可以已发红包的形式表示感谢 比如 666 66.6 6.66 等等。。。买瓶饮料压压惊。也是对帮助你的童鞋一个认可。
 * <p>
 * 5、建议大家使用Module的方式管理自己的资源文件，以及项目依赖。这样就不会让主Module资源变的太庞大。
 * <p>
 * 6、建议大家在自己的主Activity写上自己这个Demo内的功能/UI 主要是哪个种类的App。比如：直播类App 功能&UI  。不同项目最好不要混杂到一起。
 * <p>
 * 7、建议大家在自己的主 package 内写一个 README.md 方便其他成员知道你的Demo内有什么 功能&UI。
 * <p>
 * 8、
 *
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnBigbigpeng;
    Button btnHuaiLanhai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }


    private void initView() {

        btnBigbigpeng = (Button) findViewById(R.id.btn_bigbigpeng);
        btnHuaiLanhai = (Button) findViewById(R.id.btn_huailanhai);

        btnBigbigpeng.setOnClickListener(this);
        btnHuaiLanhai.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v == btnBigbigpeng) {
            startActivity(new Intent(this, BigBigPengMainActivity.class));
        } else if (v == btnHuaiLanhai) {

        }


    }
}
