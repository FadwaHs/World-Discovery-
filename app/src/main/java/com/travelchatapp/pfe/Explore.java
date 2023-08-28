package com.travelchatapp.pfe;

import android.annotation.SuppressLint;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;
import com.subinkrishna.widget.CircularImageView;


import java.util.ArrayList;
import java.util.List;


public class Explore extends Fragment {


    private static Explore instance;

    public static Explore getInstance() {
        return instance;
    }

    private ArrayList<ButtonItem> buttonItem;
    private HorizontalListView buttonList;

    private ArrayList<PostItemH> postItem;
    private HorizontalListView postList;

    private List<PostItemH> postItem2;
    private ListView postList2;
    private View view1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        instance = this;
        View view = inflater.inflate(R.layout.explore, container, false);

        view1 = inflater.inflate(R.layout.my_view, container, false);

        TextView cancel = view.findViewById(R.id.Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr1 = getFragmentManager().beginTransaction();
                fr1.replace(R.id.homeFragment, new HomePage());
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


        buttonList = view.findViewById(R.id.horizentalListButton);
        postList = view1.findViewById(R.id.viewH);
        postList2 = view.findViewById(R.id.verticalListPost);

        buttonItem = new ArrayList<>();
        postItem = new ArrayList<>();
        postItem2 = new ArrayList<>();

        buttonItem.add(new ButtonItem(getString(R.string.categorie1)));
        buttonItem.add(new ButtonItem(getString(R.string.categorie2)));
        buttonItem.add(new ButtonItem(getString(R.string.categorie3)));
        buttonItem.add(new ButtonItem("Places to stay"));
        buttonItem.add(new ButtonItem("LifeStyle"));
        buttonItem.add(new ButtonItem("Music"));
        buttonItem.add(new ButtonItem("Art"));
        buttonItem.add(new ButtonItem("Books"));
        buttonItem.add(new ButtonItem(getString(R.string.categorie9)));

        PlaceDB placeDB = new PlaceDB();
        placeDB.execute(12);
        placeDB = new PlaceDB();
        placeDB.execute(13);
        //postItem.add(new PostItemH(R.drawable.image1, "Food & Drink", "the largest kitchen", R.drawable.android, "James", "29/04/2020 at 16:08"));
        //postItem.add(new PostItemH(null, "See & Do", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem.add(new PostItemH(R.drawable.image7, "Guides & Items", "the best things to do", R.drawable.android, "James", "29/04/2020 at 16:08"));
        //postItem.add(new PostItemH(null, "Places to stay", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem.add(new PostItemH(null, "LifeStyle", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem.add(new PostItemH(null, "Art", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem.add(new PostItemH(null, "Books", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem.add(new PostItemH(null, "Film & Tv", "Description", null, "James", "29/04/2020 at 16:08"));

        //postItem2.add(new PostItemH(null, "Food & Drink", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem2.add(new PostItemH(null, "See & Do", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem2.add(new PostItemH(null, "Guides & Items", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem2.add(new PostItemH(null, "Places to stay", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem2.add(new PostItemH(null, "LifeStyle", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem2.add(new PostItemH(R.drawable.image5, "Art", "7 bollywood films", R.drawable.android, "James", "29/04/2020 at 16:08"));
        //postItem2.add(new PostItemH(null, "Books", "Description", null, "James", "29/04/2020 at 16:08"));
        //postItem2.add(new PostItemH(null, "Film & Tv", "Description", null, "James", "29/04/2020 at 16:08"));

        ButtonAdapter buttonAdapter = new ButtonAdapter(buttonItem);
        buttonList.setAdapter(buttonAdapter);


        return view;
    }

    private class ButtonAdapter extends BaseAdapter {
        private ArrayList<ButtonItem> buttonItem;

        //constructor
        private ButtonAdapter(ArrayList<ButtonItem> buttonItem) {
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

            final TextView butt = view1.findViewById(R.id.txtView);
            butt.setText(buttonItem.get(i).getNom());

            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constants.position = i;
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    Constants.categ = buttonItem.get(i).getNom();
                    fr.replace(R.id.homeFragment, new Categorie());
                    fr.commit();
                }


            });


            return view1;
        }
    }

    private class PostAdapter extends BaseAdapter {
        private ArrayList<PlaceItem> postItem;

        private PostAdapter(ArrayList<PlaceItem> postItem) {
            this.postItem = postItem;
        }

        @Override
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
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.post, null);

            ImageView placePostedImage = view1.findViewById(R.id.placePostedImage);
            final ImageView placeSave = view1.findViewById(R.id.placeSave);
            TextView placeCategory = view1.findViewById(R.id.placeCategory);
            //placeCategory.setText(postItem.get(i).getCategory());
            TextView placeDescription = view1.findViewById(R.id.placeDescription);
            //placeDescription.setText(postItem.get(i).getDescription());

            //placePostedImage.setImageResource(postItem.get(i).getPost());

            TextView profileName = view1.findViewById(R.id.ProfileName);
            //profileName.setText(postItem.get(i).getProfileName());

            TextView postedDate = view1.findViewById(R.id.placePostedDate);
            //postedDate.setText(postItem.get(i).getDate());

            CircularImageView profil = view1.findViewById(R.id.ProfileImage);
            //profil.setImageResource(postItem.get(i).getProfile());


            placeCategory.setText(postItem.get(i).getCategory());
            placeDescription.setText(postItem.get(i).getDescription());
            Picasso.get().load(postItem.get(i).getProfile()).into(profil);
            Picasso.get().load(postItem.get(i).getPost()).into(placePostedImage);
            profileName.setText(postItem.get(i).getProfileName());
            postedDate.setText(postItem.get(i).getPostId().split(";")[2]);

            profil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Profile2());
                    Constants.EMAIL = postItem.get(i).getPostId().split(";")[1];
                    fr.commit();
                }
            });


            profileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Profile2());
                    Constants.EMAIL = postItem.get(i).getPostId().split(";")[1];
                    fr.commit();
                }
            });
            placePostedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new PostFragement2());
                    Constants.IMAGE_ID = postItem.get(i).getPostId().split(";")[0];
                    Constants.EMAIL = postItem.get(i).getPostId().split(";")[1];
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
                        savePostDB.execute(postItem.get(i).getPostId().split(";")[0], Constants.USER_EMAIL);
                    } else {
                        placeSave.setTag("saved");
                        placeSave.setImageDrawable(getResources().getDrawable(R.drawable.savepostborderblanc));
                        SavePostDB savePostDB = new SavePostDB();
                        savePostDB.execute(postItem.get(i).getPostId().split(";")[0], Constants.USER_EMAIL);
                    }
                }
            });
            return view1;
        }


    }

    public class PostlistAdapter extends BaseAdapter {
        private List<PlaceItem> postItem;

        public PostlistAdapter(List<PlaceItem> postItem) {
            this.postItem = postItem;
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
            placePostedDate.setText(postItem.get(i).getPostId().split(";")[2]);

            placeProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    Constants.EMAIL = postItem.get(i).getPostId().split(";")[1];
                    fr.replace(R.id.homeFragment, new Profile2());
                    fr.commit();
                }
            });
            placeProfileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    Constants.EMAIL = postItem.get(i).getPostId().split(";")[1];
                    fr.replace(R.id.homeFragment, new Profile2());
                    fr.commit();
                }
            });
            placePostedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new PostFragement2());
                    Constants.IMAGE_ID = postItem.get(i).getPostId().split(";")[0];
                    Constants.EMAIL = postItem.get(i).getPostId().split(";")[1];
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
                        savePostDB.execute(postItem.get(i).getPostId().split(";")[0], Constants.USER_EMAIL);
                    } else {
                        placeSave.setTag("saved");
                        placeSave.setImageDrawable(getResources().getDrawable(R.drawable.savepostborderblanc));
                        SavePostDB savePostDB = new SavePostDB();
                        savePostDB.execute(postItem.get(i).getPostId().split(";")[0], Constants.USER_EMAIL);
                    }
                }
            });
            return view1;

        }


//
    }

    public void data(ArrayList<PlaceItem> placeItems) {


        PostlistAdapter postAdapter2 = new PostlistAdapter(placeItems);

        postList2.setAdapter(postAdapter2);


        postList2.addHeaderView(view1);

        postAdapter2.notifyDataSetChanged();
    }

    public void data2(ArrayList<PlaceItem> placeItems) {

        PostAdapter postAdapter = new PostAdapter(placeItems);
        postList.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();
    }


}