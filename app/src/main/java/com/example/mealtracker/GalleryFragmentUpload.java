package com.example.mealtracker;

import com.example.mealtracker.DAO.Database;

public class GalleryFragmentUpload {
    private String mName;
    private String mImageUrl;
    private int numOfUploads;
    private String index;

    public GalleryFragmentUpload() {
        // empty constructor needed
    }

    public GalleryFragmentUpload(String name, String imageUrl) {
        this.numOfUploads = Database.numOfUploads;
        index = Integer.toString(this.numOfUploads+1);
        if (name.trim().equals("")) {
            name = "image_" + index;
        }
        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
