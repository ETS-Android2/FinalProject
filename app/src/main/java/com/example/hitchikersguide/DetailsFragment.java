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
 * Details Fragment provides picture details.
 * It includes links to the Image Display and back to the Image list.
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class DetailsFragment extends Fragment {
    private Bundle dataFromActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Gets item details passed from SavedList
     * Sets TextView text with Strings passed
     * Passes image details to ImageDisplay when view button is clicked
     * Closes fragment and goes back to SavedList when hide button is clicked
     *
     * @param inflater - LayoutInflater for inflating views
     * @param container - ViewGroup to contain the fragment
     * @param savedInstanceState - the state that the app was last saved in
     * @return - View to be used in the fragment
     */
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

        Button viewButton = result.findViewById(R.id.FD_View);
        viewButton.setOnClickListener(click -> {
            Intent imgView = new Intent(getContext(), ImageDisplay.class);

            imgView.putExtra("Date", dataFromActivity.getString("Date"));
            imgView.putExtra("Title", dataFromActivity.getString("Title"));
            imgView.putExtra("URL", dataFromActivity.getString("URL"));
            imgView.putExtra("HDURL", dataFromActivity.getString("HDURL"));
            imgView.putExtra("Details", dataFromActivity.getString("Details"));

            startActivity(imgView);
        });

        Button hideButton = result.findViewById(R.id.FD_Hide);
        hideButton.setOnClickListener(click -> {
            if (SavedList.isTablet) {
                SavedList parent = (SavedList) getActivity();
                if (parent != null) {
                    parent.getSupportFragmentManager().beginTransaction().remove(this).commit();
                }
            }
            else {
                EmptyActivity parent = (EmptyActivity) getActivity();
                Intent backToSL = new Intent();
                backToSL.putExtras(dataFromActivity);

                if (parent != null) {
                    parent.setResult(Activity.RESULT_OK, backToSL);
                    parent.finish();
                }
            }
        });
        return result;
    }
}