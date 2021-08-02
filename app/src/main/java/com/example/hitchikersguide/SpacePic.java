package com.example.hitchikersguide;

/**
 * Class to define the Space Picture Object
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class SpacePic {
    long   imgID;
    String imgDate;
    String imgURL;
    String imgTitle;
    String imgDetails;
    String imgHDURL;

    /**
     * Space Pic class holds the saved images of the day and their associated data.
     *
     * @param imgDate - the date of the image
     * @param imgURL - url to the image
     * @param imgID - location in the pictures array
     */
    public SpacePic(long imgID, String imgDate, String imgURL){
        // send in parameters from JSON and save them.
        this.imgDate = imgDate;
        this.imgURL = imgURL;
        this.imgID = imgID;
    }

    public SpacePic(String imgDate, String imgTitle, String imgURL, String imgHDURL, String imgDetails) {
        this.imgDate = imgDate;
        this.imgURL = imgURL;
        this.imgTitle = imgTitle;
        this.imgDetails = imgDetails;
        this.imgHDURL = imgHDURL;
    }

    public void setTitle(String title){

        this.imgTitle = title;
    }

    public void setDetails(String details){

        this.imgDetails = details;
    }

    public void setHDURL(String hdurl){

        this.imgHDURL = hdurl;
    }

    public long getImgID() {
        return imgID;
    }
    // TODO: save title for the image
    // TODO: open HDURL in web browser
    // TODO: DO we make variables private and use getter methods? Probably should

}
