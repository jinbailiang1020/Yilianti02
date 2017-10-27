package com.embracesource.yilianti.ui;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.databinding.ActivityMainBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.ui.homepage.HomeFragment;
import com.embracesource.yilianti.ui.personcenter.PersonCenterFragment;

import java.lang.reflect.Field;

public class MainActivity extends AacBaseActivity<ActivityMainBinding> {

    LinearLayout llMainMenuContainer;

//    @Inject
//    YiliantiRetrofitApi zhihuRetrofitApi;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        InjectHelp.appComponent().inject(this);
        initBottom();
        setTitleLeftViewGone();
        updateFragment(R.id.homepage);
    }

    private void initBottom() {
        disableShiftMode(binding.navigation);//    链接：http://www.jianshu.com/p/eada0f16afd9

        int[][] states = new int[][]{
                new int[]{android.R.attr.state_checked},
                new int[]{-android.R.attr.state_checked}
        };

        int[] colors = new int[]{getResources().getColor(R.color.main_color),
                getResources().getColor(R.color.color_cacaca)
        };
        ColorStateList csl = new ColorStateList(states, colors);
        binding.navigation.setItemTextColor(csl);
        binding.navigation.setItemIconTintList(csl);

        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                updateFragment(menuItem.getItemId());
                return false;
            }
        });
    }

    //底部菜单去掉动画
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    void updateFragment(int itemId) {
        Fragment fragment;
        String tag = "content_fragment";
        switch (itemId) {
            case R.id.homepage:
                fragment = HomeFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout, fragment, tag)
                        .commitAllowingStateLoss();
                break;
            case R.id.my_patients:


                break;
            case R.id.circle:

                break;
            case R.id.person_center:
                fragment = PersonCenterFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout, fragment, tag)
                        .commitAllowingStateLoss();

                break;
        }

    }


/*    @Override
    protected void onResume() {
        super.onResume();
        requestRuntimePermission(
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA},
                new PermissionListener() {

                    @Override
                    public void onGranted() {
                        // 已被授权 可执行相应权限操作
                        Toast.makeText(mContext,"onGranted",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        // 权限被拒绝
                        Toast.makeText(mContext,"onDenied",Toast.LENGTH_SHORT).show();

                    }
                });
    }*/
}