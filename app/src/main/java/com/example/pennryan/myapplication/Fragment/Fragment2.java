package com.example.pennryan.myapplication.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pennryan.myapplication.R;

/**
 * Created by pennryan on 15/7/8.
 */
public class Fragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button btn = (Button)getActivity().findViewById(R.id.button);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView textView = (TextView) getActivity().findViewById(R.id.fragment1_text);
                    if (textView != null) {
                        Toast.makeText(getActivity(),textView.getText(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
