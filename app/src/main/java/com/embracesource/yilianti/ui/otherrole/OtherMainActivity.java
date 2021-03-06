package com.embracesource.yilianti.ui.otherrole;

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
import com.embracesource.yilianti.databinding.ActivityOtherMainBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.ui.personcenter.PersonCenterFragment;

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
        updateFragment(R.id.homepage);

        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                updateFragment(menuItem.getItemId());
                return false;
            }
        });
/*        init();
        initSlideMenu();
        loadData();*/
    }



    void updateFragment(int itemId) {
        Fragment fragment;
        String tag = "content_fragment";
        switch (itemId) {
            case R.id.homepage:
                fragment = OtherHomeFragment.newInstance();
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

}