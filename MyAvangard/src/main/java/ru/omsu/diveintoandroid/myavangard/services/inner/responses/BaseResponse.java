package ru.omsu.diveintoandroid.myavangard.services.inner.responses;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("Error")
    public String errorMessage;
}