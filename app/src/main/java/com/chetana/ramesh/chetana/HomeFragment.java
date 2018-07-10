package com.chetana.ramesh.chetana;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeFragment extends Fragment {

    ImageView iv_license, iv_loksewa, iv_passport, iv_military;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        iv_license = view.findViewById(R.id.iv_license);
        iv_passport = view.findViewById(R.id.iv_passport);
        iv_loksewa = view.findViewById(R.id.iv_loksewa);
        iv_military = view.findViewById(R.id.iv_military);

        iv_passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PassportActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }
}
