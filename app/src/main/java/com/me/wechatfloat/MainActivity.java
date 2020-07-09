package com.me.wechatfloat;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lzf.easyfloat.EasyFloat;
import com.lzf.easyfloat.enums.SidePattern;
import com.lzf.easyfloat.interfaces.OnFloatCallbacks;
import com.noober.background.drawable.DrawableCreator;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] mLocation = new int[2];

    private PopupWindow mWindow;

    private View mContentView;
    private Drawable[] mDrawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resources = getResources();
        mDrawables = new Drawable[]{
                resources.getDrawable(R.drawable.image_1),
                resources.getDrawable(R.drawable.image_2),
                resources.getDrawable(R.drawable.image_3),
                resources.getDrawable(R.drawable.image_4),
                resources.getDrawable(R.drawable.image_5)
        };


        EasyFloat.with(MainActivity.this)
                .setSidePattern(SidePattern.RESULT_HORIZONTAL)
                .setGravity(Gravity.END | Gravity.BOTTOM, 0, -200)
                .setLayout(R.layout.float_custom, view -> {
                    mLocation[0] = 1;
                    mLocation[1] = 3;
                    CompositionAvatarView avatarView = view.findViewById(R.id.web_composition);
                    Drawable drawable = new DrawableCreator.Builder().setCornersRadius(DensityUtils.dip2px(MainActivity.this, 40),
                            0, DensityUtils.dip2px(MainActivity.this, 40), 0)
                            .setStrokeWidth(DensityUtils.dip2px(MainActivity.this, 0.5f))
                            .setSolidColor(Color.parseColor("#EEDDDDDD"))
                            .setStrokeColor(Color.WHITE)
                            .build();
                    avatarView.setPadding(DensityUtils.dip2px(MainActivity.this, 10), DensityUtils.dip2px(MainActivity.this, 10), DensityUtils.dip2px(MainActivity.this, 10), DensityUtils.dip2px(MainActivity.this, 10));
                    view.findViewById(R.id.web_composition).setBackground(drawable);
                    for (Drawable drawable1 : mDrawables) {
                        avatarView.addDrawable(drawable1);
                    }


                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onRelationClick();
                        }
                    });

                }).registerCallbacks(new OnFloatCallbacks() {
            @Override
            public void createdResult(boolean b, @org.jetbrains.annotations.Nullable String s, @org.jetbrains.annotations.Nullable View view) {

            }

            @Override
            public void show(@NotNull View view) {

            }

            @Override
            public void hide(@NotNull View view) {

            }

            @Override
            public void dismiss() {

            }

            @Override
            public void touchEvent(@NotNull View view, @NotNull MotionEvent motionEvent) {

            }

            @Override
            public void drag(@NotNull View view, @NotNull MotionEvent motionEvent) {
                view.setPadding(DensityUtils.dip2px(MainActivity.this, 2), DensityUtils.dip2px(MainActivity.this, 2), DensityUtils.dip2px(MainActivity.this, 2), DensityUtils.dip2px(MainActivity.this, 2));
                Drawable drawable = new DrawableCreator.Builder().setCornersRadius(DensityUtils.dip2px(MainActivity.this, 40))
                        .setStrokeWidth(DensityUtils.dip2px(MainActivity.this, 0.5f))
                        .setSolidColor(Color.parseColor("#EEDDDDDD"))
                        .setStrokeColor(Color.WHITE)
                        .build();
                view.findViewById(R.id.web_composition).setPadding(DensityUtils.dip2px(MainActivity.this, 10), DensityUtils.dip2px(MainActivity.this, 10), DensityUtils.dip2px(MainActivity.this, 10), DensityUtils.dip2px(MainActivity.this, 10));
                view.findViewById(R.id.web_composition).setBackground(drawable);

            }

            @Override
            public void dragEnd(@NotNull View view) {
                view.getLocationOnScreen(mLocation);
                Drawable drawable;
                if (mLocation[0] > 0) {
                    drawable = new DrawableCreator.Builder().setCornersRadius(DensityUtils.dip2px(MainActivity.this, 40),
                            0, DensityUtils.dip2px(MainActivity.this, 40), 0)
                            .setStrokeWidth(DensityUtils.dip2px(MainActivity.this, 0.5f))
                            .setSolidColor(Color.parseColor("#EEDDDDDD"))
                            .setStrokeColor(Color.WHITE)
                            .build();

                } else {
                    drawable = new DrawableCreator.Builder().setCornersRadius(0,
                            DensityUtils.dip2px(MainActivity.this, 40), 0, DensityUtils.dip2px(MainActivity.this, 40))
                            .setStrokeWidth(DensityUtils.dip2px(MainActivity.this, 0.5f))
                            .setSolidColor(Color.parseColor("#EEDDDDDD"))
                            .setStrokeColor(Color.WHITE)
                            .setGradientAngle(90)
                            .setGradientColor(Color.parseColor("#FFE02020"), Color.parseColor("#FFE02020"))
                            .setGradientRadius(DensityUtils.dip2px(MainActivity.this, 5))
                            .build();
                }
                view.findViewById(R.id.web_composition).setPadding(DensityUtils.dip2px(MainActivity.this, 10), DensityUtils.dip2px(MainActivity.this, 10), DensityUtils.dip2px(MainActivity.this, 10), DensityUtils.dip2px(MainActivity.this, 10));

                view.findViewById(R.id.web_composition).setBackground(drawable);

            }
        }).show();
    }

    private void onRelationClick() {


        List<Drawable> list = new ArrayList<>();

        for (Drawable drawable : mDrawables) {
            list.add(drawable);
        }

        MainAdapter adapter = new MainAdapter(list);

        if (mWindow == null) {
            // 用于PopupWindow的View
            mContentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.float_relation, null, false);
            // 创建PopupWindow对象，其中：
            // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
            // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
            mWindow = new PopupWindow(mContentView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
            //创建一个缩小后的bitmap

            // 设置PopupWindow是否能响应外部点击事件
            mWindow.setOutsideTouchable(true);
            // 设置PopupWindow是否能响应点击事件
            mWindow.setTouchable(true);

            fitPopupWindowOverStatusBar(true);
        }
        mContentView.setOnClickListener(v -> mWindow.dismiss());
        RecyclerView recyclerView = mContentView.findViewById(R.id.relation_recycler);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        if (mLocation[0] > 0) {
            ((ConstraintLayout.LayoutParams) recyclerView.getLayoutParams()).endToEnd = 0;
            ((ConstraintLayout.LayoutParams) recyclerView.getLayoutParams()).startToStart = 1;
        } else {
            ((ConstraintLayout.LayoutParams) recyclerView.getLayoutParams()).startToStart = 0;
            ((ConstraintLayout.LayoutParams) recyclerView.getLayoutParams()).endToEnd = 1;
        }

        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);

        if (mWindow.isShowing()) {
            mWindow.dismiss();
        } else {
            mWindow.showAtLocation(mContentView, Gravity.CENTER, 0, 0);
        }

    }

    public void fitPopupWindowOverStatusBar(boolean needFullScreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Field mLayoutInScreen = PopupWindow.class.getDeclaredField("mLayoutInScreen");
                mLayoutInScreen.setAccessible(true);
                mLayoutInScreen.set(mWindow, needFullScreen);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}