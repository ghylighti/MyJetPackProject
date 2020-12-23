package com.ghy.componentproject.fragment;

import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.DialogFragmentNavigator;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;

import com.ghy.componentproject.R;

public class MyNavHostFragment extends NavHostFragment {
    public MyNavHostFragment() {

    }



    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    @NonNull
    protected Navigator<? extends FragmentNavigator.Destination> createFragmentNavigator() {
        return new MyFragmentNavigator(requireContext(), getChildFragmentManager(),
                getContainerId());
    }



    private int getContainerId() {
        int id = getId();
        if (id != 0 && id != View.NO_ID) {
            return id;
        }
        // Fallback to using our own ID if this Fragment wasn't added via
        // add(containerViewId, Fragment)
        return R.id.nav_host_fragment_container;
    }
}
