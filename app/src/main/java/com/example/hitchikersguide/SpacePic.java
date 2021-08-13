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

    /**
     *  Space Pic class holds the saved images of the day and their associated data.
     *
     * @param imgDate - the date of the image
     * @param imgTitle - the title of the image
     * @param imgURL - url to the image
     * @param imgHDURL - high definition url to the image
     * @param imgDetails - image description
     */
    public SpacePic(String imgDate, String imgTitle, String imgURL, String imgHDURL, String imgDetails) {
        this.imgDate = imgDate;
        this.imgURL = imgURL;
        this.imgTitle = imgTitle;
        this.imgDetails = imgDetails;
        this.imgHDURL = imgHDURL;
    }

    /**
     * Set the Title of the SpacePic
     *
     * @param title - Title to be set for SpacePic
     */
    public void setTitle(String title){

        this.imgTitle = title;
    }

    /**
     * Set the Description of the SpacePic
     *
     * @param details - Description to be set for SpacePic
     */
    public void setDetails(String details){

        this.imgDetails = details;
    }

    /**
     * Set the HD URL of the SpacePic
     *
     * @param hdurl - high definition URL to be set for SpacePic
     */
    public void setHDURL(String hdurl){

        this.imgHDURL = hdurl;
    }

    /**
     * Get the Image ID
     *
     * @return - ImageID is returned
     */
    public long getImgID() {

        return imgID;
    }
}
