package com.embracesource.yilianti.biz.pojo.request.ext;

import com.google.gson.annotations.SerializedName;

import com.embracesource.yilianti.biz.pojo.request.BaseRequest;

/**
 * GetStoryExtraRequest <br/>
 * Created by xiaqiulei on 2016-01-04.
 */
public class GetStoryExtraRequest extends BaseRequest {

    @SerializedName("id")
    public int id;

    public GetStoryExtraRequest(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GetStoryExtraRequest{" +
                "id=" + id +
                "} " + super.toString();
    }
}
