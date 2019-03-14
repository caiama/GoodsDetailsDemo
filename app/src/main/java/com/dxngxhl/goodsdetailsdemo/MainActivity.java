package com.dxngxhl.goodsdetailsdemo;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    RadioGroup radioGroup;
    //头部选项
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4,checkRadio;
    //头部选项底部横线
    View lineradio1,lineradio2,lineradio3,lineradio4,checkView;
    GoodsScrollView nestedScrollView;
    //头部选项对应需要滑动的目标控件
    TextView scroll_texttest1,scroll_texttest2,scroll_texttest3,scroll_texttest4;
    //头部选项对应的需要滑动目标值
    int scrollViewH1 = 0,scrollViewH2 = 0,scrollViewH3 = 0,scrollViewH4 = 0;
    //返回和右侧
    ImageView backImageView,imageRight;
    private Banner banner;
    private String[] images = new String[]{
            "http://img.zcool.cn/community/012d7d57a803000000018c1b970650.jpg@1280w_1l_2o_100sh.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1260824203,605692684&fm=26&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=639358842,3761865490&fm=26&gp=0.jpg"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //透明状态栏
        View view = findViewById(R.id.view);
        view.getLayoutParams().height = getStatusBarHeight();
        ImmersionBar.with(this).statusBarDarkFont(true).init();

        banner = findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setImages(Arrays.asList(images)).setImageLoader(new GlideImageLoader()).start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(MainActivity.this, "banner=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //
        toolbar = findViewById(R.id.toolbar);
        nestedScrollView = findViewById(R.id.gooddetailNestedScrollView);
        backImageView = findViewById(R.id.backImage);
        imageRight = findViewById(R.id.imageRight);
        radioGroup = findViewById(R.id.radiogroup);
        radioButton1 = findViewById(R.id.radiotest1);
        radioButton2 = findViewById(R.id.radiotest2);
        radioButton3 = findViewById(R.id.radiotest3);
        radioButton4 = findViewById(R.id.radiotest4);
        lineradio1 = findViewById(R.id.lineradio1);
        lineradio2 = findViewById(R.id.lineradio2);
        lineradio3 = findViewById(R.id.lineradio3);
        lineradio4 = findViewById(R.id.lineradio4);
        checkRadio = radioButton1;
        checkView = lineradio1;
        scroll_texttest1 = findViewById(R.id.scroll_texttest1);
        scroll_texttest2 = findViewById(R.id.scroll_texttest2);
        scroll_texttest3 = findViewById(R.id.scroll_texttest3);
        scroll_texttest4 = findViewById(R.id.scroll_texttest4);
        //有数据后需要在数据加载完成后设置
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radiotest1:
                        clickRadio(radioButton1,lineradio1);
                        break;
                    case R.id.radiotest2:
                        clickRadio(radioButton2,lineradio2);
                        break;
                    case R.id.radiotest3:
                        clickRadio(radioButton3,lineradio3);
                        break;
                    case R.id.radiotest4:
                        clickRadio(radioButton4,lineradio4);
                        break;
                }
            }
        });
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nestedScrollView.scrollTo(0, scrollViewH1);
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nestedScrollView.scrollTo(0, scrollViewH2);
            }
        });
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nestedScrollView.scrollTo(0, scrollViewH3);
            }
        });
        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nestedScrollView.scrollTo(0, scrollViewH4);
            }
        });
        nestedScrollView.setOnScrollListener(new GoodsScrollView.OnScrollListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (scrollViewH2 == 0){
                    scrollViewH1 = (int) scroll_texttest1.getY();
                    scrollViewH2 = (int) scroll_texttest2.getY();
                    scrollViewH3 = (int) scroll_texttest3.getY();
                    scrollViewH4 = (int) scroll_texttest4.getY();
                }
            }

            @Override
            public void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
                if (scrollY > scrollViewH1  && scrollY < scrollViewH2){
                    radioButton1.setChecked(true);
                }else if (scrollY > scrollViewH2 && scrollY < scrollViewH3){
                    radioButton2.setChecked(true);
                }else if (scrollY > scrollViewH3 && scrollY < scrollViewH4){
                    radioButton3.setChecked(true);
                }else if (scrollY > scrollViewH4){
                    radioButton4.setChecked(true);
                }
//                if (clampedY == true && scrollY == 0){
//
//                }
            }
        });
        appBarLayout = findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener);

    }
    /**
     * 当前点击选中的radiobutton
     * @param radioButton
     */
    public void clickRadio(RadioButton radioButton,View lineView){
        if (checkRadio == radioButton){
            Log.e("fffff","去重复");
            return;
        }
        checkRadio.setTextColor(Color.rgb(0, 0, 0));
        checkView.setBackgroundColor(Color.argb(0,0, 0, 0));
        checkRadio = radioButton;
        checkView = lineView;
        radioButton.setTextColor(Color.rgb( 255, 0, 0));
        lineView.setBackgroundColor(Color.rgb( 255, 0, 0));
    }

    /**
     *
     * AppBarLayout滑动处理
     */
    AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            //垂直方向偏移量
            int offset = Math.abs(verticalOffset);
            //最大偏移距离
            int scrollRange = appBarLayout.getTotalScrollRange();
            //根据偏移百分比计算扫一扫布局的透明度值
            float scale = (float) offset / scrollRange;
            int alpha = (int) (255 * scale);
            toolbar.setBackgroundColor(Color.argb(alpha, 232, 232, 232));
            radioButton1.setTextColor(Color.argb(alpha, 0, 0, 0));
            radioButton2.setTextColor(Color.argb(alpha, 0, 0, 0));
            radioButton3.setTextColor(Color.argb(alpha, 0, 0, 0));
            radioButton4.setTextColor(Color.argb(alpha, 0, 0, 0));
            //当前选中的radiobutton
            checkRadio.setTextColor(Color.argb(alpha, 255, 0, 0));
            checkView.setBackgroundColor(Color.argb(alpha, 255, 0, 0));
            imageRight.setAlpha(scale);
            if (scale > 0.5){
                //顶部下滑
                backImageView.setImageResource(R.mipmap.icon_back2);
                backImageView.setAlpha(scale);
            }else {
                //上划
                backImageView.setImageResource(R.mipmap.icon_back1);
                backImageView.setAlpha(1 - scale);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
        appBarLayout.removeOnOffsetChangedListener(onOffsetChangedListener);
        nestedScrollView.removeAllViews();
        nestedScrollView = null;
        appBarLayout = null;
        banner.removeAllViews();
        banner = null;
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int height = 0;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }
}
