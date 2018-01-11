package com.androcid.zomato.retro;

import com.androcid.zomato.model.ReviewItem;
import com.androcid.zomato.util.Constant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by spm3 on 4/23/2015.
 */
public class ProfileReviewResponse {
    @SerializedName(Constant.MESSAGE)
    String message;

    @SerializedName(Constant.SUCCESS)
    boolean success;

    @SerializedName(Constant.DETAILS)
    List<ReviewItem> items;

    public List<ReviewItem> getItems() {
        return items;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
