package com.travelchatapp.pfe;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Categorie extends Fragment {

    private ArrayList<com.travelchatapp.pfe.ButtonItem> buttonItem;
    private HorizontalListView buttonList;
    private static Categorie instance;

    public static Categorie getInstance() {
        return instance;
    }

    private List<PostItemH> postItem;
    private ListView postList;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        instance = this;

        View view = inflater.inflate(R.layout.categorie, container, false);

        TextView Cancel = view.findViewById(R.id.Cancel);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr1 = getFragmentManager().beginTransaction();
                fr1.replace(R.id.homeFragment, new Explore());
                fr1.commit();
            }
        });

        EditText editText = view.findViewById(R.id.searchBar);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.homeFragment, new SearchFragement());
                fr.commit();
            }
        });


        postList = view.findViewById(R.id.verticalListPost);
        buttonList = view.findViewById(R.id.horizentalListButton);

        buttonItem = new ArrayList<>();
        postItem = new ArrayList<>();

        buttonItem.add(new com.travelchatapp.pfe.ButtonItem(getString(R.string.categorie1)));
        buttonItem.add(new com.travelchatapp.pfe.ButtonItem(getString(R.string.categorie2)));
        buttonItem.add(new com.travelchatapp.pfe.ButtonItem(getString(R.string.categorie3)));
        buttonItem.add(new com.travelchatapp.pfe.ButtonItem("Places to stay"));
        buttonItem.add(new com.travelchatapp.pfe.ButtonItem("LifeStyle"));
        buttonItem.add(new com.travelchatapp.pfe.ButtonItem("Music"));
        buttonItem.add(new com.travelchatapp.pfe.ButtonItem("Art"));
        buttonItem.add(new com.travelchatapp.pfe.ButtonItem("Books"));
        buttonItem.add(new com.travelchatapp.pfe.ButtonItem(getString(R.string.categorie9)));


        CategoryDB categoryDB = new CategoryDB();
        categoryDB.execute(Constants.categ);

        //postItem.add(new PostItemH(null, "Food & Drink", "Description", null, "ProfilName", "1/1/2020"));
        //postItem.add(new PostItemH(null, "See & Do", "Description", null, "ProfilName", "1/1/2020"));
        //postItem.add(new PostItemH(null, "Guides & Items", "Description", null, "ProfilName", "1/1/2020"));
        //postItem.add(new PostItemH(null, "Places to stay", "Description", null, "ProfilName", "1/1/2020"));
        //postItem.add(new PostItemH(null, "LifeStyle", "Description", null, "ProfilName", "1/1/2020"));
        //postItem.add(new PostItemH(null, "Art", "Description", null, "ProfilName", "1/1/2020"));
        //postItem.add(new PostItemH(null, "Books", "Description", null, "ProfilName", "1/1/2020"));
        //postItem.add(new PostItemH(null, "Film & Tv", "Description", null, "ProfilName", "1/1/2020"));
        //

        //Constants.categ

        ButtonAdapter buttonAdapter = new ButtonAdapter(buttonItem);
        buttonList.setAdapter(buttonAdapter);


        return view;
    }

    private class ButtonAdapter extends BaseAdapter {
        private ArrayList<com.travelchatapp.pfe.ButtonItem> buttonItem;

        //constructor
        private ButtonAdapter(ArrayList<com.travelchatapp.pfe.ButtonItem> buttonItem) {
            this.buttonItem = buttonItem;
        }

        @Override
        public int getCount() {
            return buttonItem.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.button, null);

            final TextView textView = view1.findViewById(R.id.txtView);

            if (i == Constants.position) {
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
            }

            textView.setText(buttonItem.get(i).getNom());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constants.position = i;
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Categorie());
                    Constants.categ = textView.getText().toString();
                    fr.commit();
                }


            });
            return view1;
        }
    }

    public class PostlistAdapter extends BaseAdapter {
        private List<PlaceItem> postItem;

        public PostlistAdapter(List<PlaceItem> postItems) {
            this.postItem = postItems;
        }

        public int getCount() {
            return postItem.size();
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
        public View getView(int i, View convertView, ViewGroup parent) {
            ImageView placePostedImage;
            final ImageView placeSave;
            TextView placeCategory;
            TextView placeDescription;
            ImageView placeProfileImage;
            TextView placeProfileName;
            TextView placePostedDate;
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.post2, null);

            placePostedImage = view1.findViewById(R.id.placePostedImage2);
            placeSave = view1.findViewById(R.id.placeSave2);
            placeCategory = view1.findViewById(R.id.placeCategory2);
            placeDescription = view1.findViewById(R.id.placeDescription2);
            placeProfileImage = view1.findViewById(R.id.placeProfileImage2);
            placeProfileName = view1.findViewById(R.id.placeProfileName2);
            placePostedDate = view1.findViewById(R.id.placePostedDate2);


            placeCategory.setText(postItem.get(i).getCategory());
            placeDescription.setText(postItem.get(i).getDescription());
            Picasso.get().load(postItem.get(i).getProfile()).into(placeProfileImage);
            Picasso.get().load(postItem.get(i).getPost()).into(placePostedImage);
            placeProfileName.setText(postItem.get(i).getProfileName());
            placePostedDate.setText(postItem.get(i).getPostId().split(";")[1]);


            placeProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    Constants.EMAIL = postItem.get(i).getPostId().split(";")[0];
                    fr.replace(R.id.homeFragment, new Profile2());
                    fr.commit();
                }
            });
            placeProfileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    Constants.EMAIL = postItem.get(i).getPostId().split(";")[0];
                    fr.replace(R.id.homeFragment, new Profile2());
                    fr.commit();
                }
            });
            placePostedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    Constants.IMAGE_ID = postItem.get(i).getPostId().split(";")[0];
                    Constants.EMAIL = postItem.get(i).getPostId().split(";")[1];
                    fr.replace(R.id.homeFragment, new PostFragement3());
                    fr.commit();
                }

            });
            final int[] f = {0};
            placeSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (placeSave.getTag().toString().isEmpty() || placeSave.getTag().equals("saved")) {
                        placeSave.setTag("unsaved");
                        placeSave.setImageDrawable(getResources().getDrawable(R.drawable.savepostblanc));
                        SavePostDB savePostDB = new SavePostDB();
                        savePostDB.execute(postItem.get(i).getPostId().split(";")[1], Constants.USER_EMAIL);
                    } else {
                        placeSave.setTag("saved");
                        placeSave.setImageDrawable(getResources().getDrawable(R.drawable.savepostborderblanc));
                        SavePostDB savePostDB = new SavePostDB();
                        savePostDB.execute(postItem.get(i).getPostId().split(";")[1], Constants.USER_EMAIL);
                    }
                }
            });
            return view1;

        }

    }

    public void data(ArrayList<PlaceItem> placeItems) {
        PostlistAdapter postAdapter = new PostlistAdapter(placeItems);
        postList.setAdapter(postAdapter);
    }

}
