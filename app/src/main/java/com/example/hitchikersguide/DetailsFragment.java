package com.example.hitchikersguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    private Bundle dataFromActivity;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataFromActivity = getArguments();

        View result = inflater.inflate(R.layout.fragment_details, container, false);

        TextView imgTitle = result.findViewById(R.id.FD_Title);
        imgTitle.setText(dataFromActivity.getString("Title"));

        TextView imgDetails = result.findViewById(R.id.FD_Details);
        imgDetails.setText(dataFromActivity.getString("Details"));

        TextView imgHDURL = result.findViewById(R.id.FD_HDURL);
        imgHDURL.setText(dataFromActivity.getString("HDURL"));

        Button hideButton = result.findViewById(R.id.FD_Hide);
        hideButton.setOnClickListener(click -> {
            if (SavedList.isTablet) {
                SavedList parent = (SavedList) getActivity();
                parent.getSupportFragmentManager().beginTransaction().remove(this).commit();
            }
            //WHAT IS GOING ON HERE?!
            else {
                EmptyActivity parent = (EmptyActivity) getActivity();
                Intent backToSL = new Intent();
                backToSL.putExtras(dataFromActivity);

                parent.setResult(Activity.RESULT_OK, backToSL);
                parent.finish();
            }
        });
        return result;
    }
}