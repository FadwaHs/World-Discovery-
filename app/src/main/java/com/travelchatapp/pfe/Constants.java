
package com.travelchatapp.pfe;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;

import java.util.ArrayList;

public class Constants {

    private static final String PACKAGE_NAME = "com.travlechatapp.pfe";
    static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";
    static final int SUCCESS_RESULT = 1;
    static final int FAILURE_RESULT = 0;

    public static String USER_EMAIL = "user1@gmail.com";
    public static String KEY_PASSWORD;
    private static String IP = "http://192.168.43.172/";
    public static String FOLDER_IMAGE = IP + "PhpForAndroid/Image/";
    public static String SERVER = IP + "PhpForAndroid/Image/";
    public static String URL_1 = IP + "PhpForAndroid/AddPhotoDB.php";
    public static String URL_2 = IP + "PhpForAndroid/GetImagesDB.php";
    public static String URL_3 = IP + "PhpForAndroid/SignUp.php";
    public static String URL_4 = IP + "PhpForAndroid/Login.php";
    public static String URL_5 = IP + "PhpForAndroid/ProfileDB.php";
    public static String URL_6 = IP + "PhpForAndroid/SavePostDB.php";
    public static String URL_7 = IP + "PhpForAndroid/PlaceDB.php";
    public static String URL_8 = IP + "PhpForAndroid/GetImagesDB2.php";
    public static String URL_9 = IP + "PhpForAndroid/GetImageDB.php";
    public static String URL_10 = IP + "PhpForAndroid/LikePostDB.php";
    public static String URL_11 = IP + "PhpForAndroid/GetSavedPostDB.php";
    public static String URL_12 = IP + "PhpForAndroid/AddProfileImageDB.php";
    public static String URL_13 = IP + "PhpForAndroid/SearchDB.php";
    public static String URL_14 = IP + "PhpForAndroid/CategoryDB.php";
    public static ArrayList<String> imageUrls;

    public static String IMAGE_ID;

    public static ArrayList<PostItem> postedItems;
    public static int position;


    private static Point getSize() {
        Display display = MainActivity.getInstance().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static final int SCREEN_WIDTH = getSize().x;

    public static final int SCREEN_HEIGHT = getSize().y;

    //variables
    public static int Flag = 0;
    public static String EMAIL;
    public static boolean Flag2 = true;

    //
    public static String bitmap123;
    public static String name;
    public static String categ;

}
