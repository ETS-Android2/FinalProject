package com.example.hitchikersguide;

/**
 * Class to define the Space Picture Object
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class SpacePic {
    String imgDate;
    String imgTitle;
    String imgDescription;
    String imgURL;
    String imgHDURL;
    long imgID;

    public SpacePic(String[] args, long imgID){
        // send in parameters from JSON and save them.
        this.imgDate = args[0];
        this.imgURL = args[1];
        this.imgHDURL = args[2];
        if (args.length>3){
            this.imgDescription = args[3];
        }
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
