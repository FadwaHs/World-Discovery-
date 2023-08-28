package com.travelchatapp.pfe;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.InputStream;


public class ImageProcessing {

    /**
     * width and height in pixel
     */

    protected static Bitmap resizeImg(Bitmap image) {
        Bitmap newBitmap;

        newBitmap = Bitmap.createScaledBitmap(image, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 2, true);

        return newBitmap;
    }

}
