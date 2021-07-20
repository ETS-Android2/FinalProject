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

    /**
     * Space Pic class holds the saved images of the day and their associated data.
     *
     * @param imgDate - the date of the image
     * @param imgURL - url to the image
     * @param imgID - location in the pictures array
     */
    public SpacePic(String imgDate, String imgURL, long imgID){
        // send in parameters from JSON and save them.
        this.imgDate = imgDate;
        this.imgURL = imgURL;
        this.imgID = imgID;
    }

    public void setTitle(String title){
        this.imgTitle = title;
    }

    public void setDescription(String description){
        this.imgDescription = description;
    }

    public void setHDURL(String hdurl){
        this.imgHDURL = hdurl;
    }
    // TODO: save title for the image
    // TODO: open HDURL in web browser
    // TODO: DO we make variables private and use getter methods? Probably should

}
