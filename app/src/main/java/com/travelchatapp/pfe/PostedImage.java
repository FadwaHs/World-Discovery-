package com.travelchatapp.pfe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostedImage extends AppCompatActivity {


    private ListView itemsList;
    private PostAdapter postAdapter;
    private ArrayList<PostItem> postedItems;
    private ArrayList<PostItem> postedItemsReversed;
    private ArrayList<String> descriptions;
    private static PostedImage instance;

    public static PostedImage getInstance() {
        return instance;
    }

    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_posted_image);

        initialise();

    }

    private void initialise() {
        descriptions = new ArrayList<>();
        instance = this;
        postedItems = new ArrayList<>();
        itemsList = findViewById(R.id.postedImageListView);
        i = getIntent().getIntExtra("data", 0);
        GetImagesDB2 getImages = new GetImagesDB2();
        getImages.execute(Constants.USER_EMAIL);

    }


    /**
     * post adapter
     */
    private class PostAdapter extends BaseAdapter {
        private ArrayList<PostItem> postItems;

        private PostAdapter(ArrayList<PostItem> postItems) {
            this.postItems = postItems;
        }

        @Override
        public int getCount() {
            return postItems.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.posted_item, null);

            ImageView image = view1.findViewById(R.id.postedImg);
            TextView description = view1.findViewById(R.id.postedDescription);
            Picasso.get().load(postedItems.get(i).getImageUrl()).into(image);
            description.setText(descriptions.get(i).toString());
            image.getLayoutParams().height = Constants.SCREEN_WIDTH;

            //image.setImageBitmap();


            return view1;
        }
    }


    /**
     * Reverse the array list
     */
    private void reverseArrayList() {
        postedItemsReversed = new ArrayList<>();
        int n = postedItems.size() - 1;
        for (int i = n; 0 <= i; i--) {
            postedItemsReversed.add(this.postedItems.get(i));
        }

    }

    public void setImage(ArrayList<String> descriptions) {
        this.descriptions.addAll(descriptions);
        postedItems.clear();
        for (int i = 0; i < Constants.imageUrls.size(); i++) {
            postedItems.add(new PostItem(Constants.imageUrls.get(i)));
        }

        reverseArrayList();

        postAdapter = new PostAdapter(postedItemsReversed);
        itemsList.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();
        itemsList.smoothScrollToPosition(i);
        itemsList.setVerticalScrollBarEnabled(false);
    }


}
