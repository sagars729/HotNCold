package com.example.android.bounce;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by User on 3/25/2017.
 */

public class endFrag extends android.support.v4.app.Fragment implements View.OnClickListener{
    Button button1;
    DataFromActivityToFragment mCallback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.endlayout, container, false);
        TextView score = (TextView) rootView.findViewById(R.id.textView2);
        TextView highScore = (TextView) rootView.findViewById(R.id.textView4);
        String strtext = getArguments().getString("score");
        String hScore = getArguments().getString("highScore");
        score.setText(strtext);
        highScore.setText(hScore);
        button1 = (Button) rootView.findViewById(R.id.button);
        button1.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (DataFromActivityToFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataFromActivityToFragment");
        }
    }

    @Override
    public void onClick(View view) {

        mCallback.first();
    }
    public interface DataFromActivityToFragment {
        void first();
    }
}
