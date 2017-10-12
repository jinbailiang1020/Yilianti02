package com.embracesource.yilianti.ui.base.common.permission;

import java.util.List;

public interface PermissionListener {
    void onGranted();
    void onDenied(List<String> deniedPermission);
}