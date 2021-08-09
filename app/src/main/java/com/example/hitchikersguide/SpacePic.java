package com.example.hitchikersguide;

/**
 * Class to define the Space Picture Object
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class SpacePic {
    String imgDate;
    String imgURL;
    long imgID;
    String imgTitle;
    String imgDescription;
    String imgHDURL;


    public SpacePic(String imgDate, String imgURL, long imgID){
        // send in parameters from JSON and save them.
        this.imgDate = imgDate;
        this.imgURL = imgURL;
        this.imgID = imgID;
    }

    // TODO: save title for the image
    // TODO: open HDURL in web browser
    //Mod 3 Intents
//    String url = "http://www.algonquincollege.com";
//    Intent i = new Intent(Intent.ACTION_VIEW);
//    i.setData( Uri.parse(url) );
//    startActivity(i);
}
