package com.travelchatapp.pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class Home extends AppCompatActivity implements MaterialTabListener {

    public static MaterialTabHost tabHost;
    private HomePage homePage;
    private SavedPost savedPost;
    private ChatList chatlist;
    private static Home instance;
    private AddPhoto addPhoto;
    private Profile profile;
    private Explore explorePage;
    private static boolean BACK_PRESSED = false;
    private static int DELAY = 1500;

    public static Home getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        instance = this;

        tabHost = findViewById(R.id.homeTabHost);
        homePage = new HomePage();
        savedPost = new SavedPost();
        addPhoto = new AddPhoto();
        chatlist = new ChatList();
        profile = new Profile();
        explorePage = new Explore();

        /**change bottom navigation bar color */
        tabHost.setPrimaryColor(Color.WHITE);
        tabHost.setAccentColor(Color.rgb(236, 156, 76));
        tabHost.setIconColor(Color.rgb(236, 156, 76));


        createBottomTabHost();
    }

    /**
     * create a bottom tab host
     */
    private void createBottomTabHost() {
        tabHost.addTab(
                tabHost.newTab()
                        .setIcon(getDrawable(R.drawable.home))
                        .setTabListener(this)
        );
        tabHost.addTab(
                tabHost.newTab()
                        .setIcon(getDrawable(R.drawable.save))
                        .setTabListener(this)
        );
        tabHost.addTab(
                tabHost.newTab()
                        .setIcon(getDrawable(R.drawable.add))
                        .setTabListener(this)
        );
        tabHost.addTab(
                tabHost.newTab()
                        .setIcon(getDrawable(R.drawable.chat))
                        .setTabListener(this)
        );
        tabHost.addTab(
                tabHost.newTab()
                        .setIcon(getDrawable(R.drawable.profile))
                        .setTabListener(this)
        );

        getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, homePage).commit();
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        switch (tab.getPosition()) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, homePage).commit();
                tabHost.setSelectedNavigationItem(0);
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, savedPost).commit();
                tabHost.setSelectedNavigationItem(1);
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, addPhoto).commit();
                tabHost.setSelectedNavigationItem(2);
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, chatlist).commit();
                tabHost.setSelectedNavigationItem(3);
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, profile ).commit();//EditProfile()
                tabHost.setSelectedNavigationItem(4);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onTabReselected(MaterialTab tab) {
        switch (tab.getPosition()) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, homePage).commit();
                tabHost.setSelectedNavigationItem(0);
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, savedPost).commit();
                tabHost.setSelectedNavigationItem(1);
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, addPhoto).commit();
                tabHost.setSelectedNavigationItem(2);
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, chatlist).commit();
                tabHost.setSelectedNavigationItem(3);
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, profile ).commit();//EditProfile()
                tabHost.setSelectedNavigationItem(4);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    @Override
    public void onBackPressed() {
        if (!BACK_PRESSED) {
            Toast.makeText(getApplicationContext(), "Double click to exit", Toast.LENGTH_SHORT).show();
            BACK_PRESSED = true;
        } else {
            super.onBackPressed();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BACK_PRESSED = false;
            }
        }, DELAY);

    }
}
