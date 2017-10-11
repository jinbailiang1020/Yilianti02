package com.embracesource.yilianti.biz.pojo.request.ext;

import com.embracesource.yilianti.biz.pojo.request.BaseRequest;

/**
 * GetNewsRequest <br/>
 * Created by xiaqiulei on 2016-01-04.
 */
public class GetNewsRequest extends BaseRequest {

    public int id;

    public GetNewsRequest(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GetNewsRequest{" +
                "id=" + id +
                "} " + super.toString();
    }
}