package com.ghy.componentproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;

import com.ghy.common.base.BaseModel;

public class FindModel extends BaseModel {
    public FindModel(@NonNull Application application, SavedStateHandle stateHandle) {
        super(application, stateHandle);
    }
}
