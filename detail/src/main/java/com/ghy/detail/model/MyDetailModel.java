package com.ghy.detail.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;

import com.ghy.common.base.BaseModel;

public class MyDetailModel extends BaseModel {
    public MyDetailModel(@NonNull Application application, SavedStateHandle stateHandle) {
        super(application, stateHandle);
    }
}
