package com.embracesource.yilianti.ui.otherrole;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.databinding.ActivityOtherMainBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;

import java.lang.reflect.Field;

public class OtherMainActivity extends AacBaseActivity<ActivityOtherMainBinding> {

    LinearLayout llMainMenuContainer;

//    @Inject
//    YiliantiRetrofitApi zhihuRetrofitApi;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        InjectHelp.appComponent().inject(this);
        initBottom();
        setTitleLeftViewGone();
        updateFragment();
/*        init();
        initSlideMenu();
        loadData();*/
    }

    private void initBottom() {
        disableShiftMode(binding.navigation);//    链接：http://www.jianshu.com/p/eada0f16afd9

        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };

        int[] colors = new int[]{getResources().getColor(R.color.main_color),
                getResources().getColor(R.color.color_cacaca)
        };
        ColorStateList csl = new ColorStateList(states, colors);
        binding.navigation.setItemTextColor(csl);
        binding.navigation.setItemIconTintList(csl);
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

    void updateFragment() {
        String tag = "content_fragment";
        Fragment fragment;
  /*      if (currentThemeItem != null) {
            fragment = HomeFragment.newInstance(currentThemeItem);
        } else {
            fragment = HomeFragment.newInstance();
        }*/
        fragment = OtherHomeFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout, fragment, tag)
                .commitAllowingStateLoss();
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