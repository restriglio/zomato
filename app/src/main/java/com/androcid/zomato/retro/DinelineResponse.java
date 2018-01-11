package com.androcid.zomato.retro;

import com.androcid.zomato.model.DinelineItem;
import com.androcid.zomato.util.Constant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by spm3 on 4/23/2015.
 */
public class DinelineResponse {
    @SerializedName(Constant.MESSAGE)
    String message;

    @SerializedName(Constant.SUCCESS)
    boolean success;

    @SerializedName(Constant.DETAILS)
    List<DinelineItem> items;

    public List<DinelineItem> getItems() {
        return items;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
