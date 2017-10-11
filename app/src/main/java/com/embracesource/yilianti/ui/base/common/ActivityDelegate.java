package com.embracesource.yilianti.ui.base.common;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.security.InvalidParameterException;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.core.log.AppLog;

/**
 * CommonFragmentActivityDelegate <br/>
 * Created by xiaqiulei on 2015-04-03.
 */
class ActivityDelegate<T extends ViewDataBinding, A extends CommonActivity<T>, F extends Fragment> {

    private static final int FRAGMENT_CONTAINER = R.id.framelayout;

    private A activity;
    private F commonFragment;
    private CommonExtraParam extraParam;

    ActivityDelegate(A a) {
        if (a == null) {
            throw new InvalidParameterException("activity is null.");
        }
        activity = a;
    }

    void beforeOnCreate(Bundle savedInstanceState) {
        AppLog.d("beforeOnCreate");
    }

    void afterOnCreate(Bundle savedInstanceState) {
        AppLog.d("afterOnCreate");

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        Intent it = activity.getIntent();
        Object obj = it.getSerializableExtra(ICommonFragment.EXTRA_REQ);
        if (validate(obj)) {
            extraParam = (CommonExtraParam) obj;
            if (savedInstanceState == null) {
                try {
                    commonFragment = (F) extraParam.getFragmentClass().newInstance();
                    FragmentManager manager = activity.getSupportFragmentManager();
                    manager.beginTransaction()
                            .add(FRAGMENT_CONTAINER, commonFragment)
                            .commitAllowingStateLoss();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            AppLog.e(getClass().getSimpleName() + "参数不合法");
            activity.finish();
        }

        AppLog.d(String.format("activity = %s, fragment = %s, param = %s", activity, commonFragment, extraParam));
    }

    private boolean validate(Object obj) {
        boolean ret = false;
        do {
            if (obj == null) {
                break;
            }
            if (!(obj instanceof CommonExtraParam)) {
                break;
            }
            CommonExtraParam param = (CommonExtraParam) obj;
            if (!param.validate()) {
                break;
            }
            ret = true;
        } while (false);
        return ret;
    }

    F getCommonFragment() {
        return commonFragment;
    }

    CommonExtraParam getExtraParam() {
        return extraParam;
    }
}