package com.embracesource.yilianti.biz.pojo.request.ext;

import com.embracesource.yilianti.biz.pojo.request.BaseRequest;

/**
 * StartInfoRequest <br/>
 * Created by xiaqiulei on 2015-12-30.
 */
public class GetStartInfoRequest extends BaseRequest {

    public int width;

    public int height;

    public GetStartInfoRequest(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "StartInfoRequest{" +
                "width=" + width +
                ", height=" + height +
                "} " + super.toString();
    }
}