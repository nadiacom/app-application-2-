package com.app.saco;

import com.example.android.navigationdrawerexample.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BilanComplementaire extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.bilan_complementaire, container, false);
		return rootView;
    }

}
