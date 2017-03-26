package com.example.android.bounce;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 3/26/2017.
 */

public class gameFrag extends Fragment {
    CustomDrawableView cv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gamelayout, container, false);
        cv = new CustomDrawableView(this.getActivity());
        return rootView;
    }
}
