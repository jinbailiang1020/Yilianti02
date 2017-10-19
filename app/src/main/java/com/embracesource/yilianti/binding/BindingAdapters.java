package com.embracesource.yilianti.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.webkit.WebView;

public class BindingAdapters {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

/*    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView icon, String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(icon.getContext()).load(url).into(icon);
        }
    }*/

    @BindingAdapter("html")
    public static void html(WebView webView, String html) {
        webView.loadDataWithBaseURL(null, html, "text/html", "utf8", null);
    }
}