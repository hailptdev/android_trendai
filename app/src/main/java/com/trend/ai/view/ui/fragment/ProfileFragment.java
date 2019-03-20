package com.trend.ai.view.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.trend.ai.R;
import com.trend.ai.view.ui.actitivy.MenuActivity;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:31
 * Mail: specialcyci@gmail.com
 */
public class ProfileFragment extends Fragment {

    String name = "";
    TextView tvText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.profile, container, false);
        tvText = view.findViewById(R.id.textView);

        MenuActivity activity = (MenuActivity) getActivity();
        tvText.setText(activity.getName());
        Log.d("hait","onCreateView");
        return view;

    }


}
