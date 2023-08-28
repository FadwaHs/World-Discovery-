package com.travelchatapp.pfe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class Profile extends Fragment {

    private View view;
    private TextView newProfileUserName;
    private ImageView newProfileImage;
    //private TextView newProfilePublication;
    private ImageView sett;
    private TextView newProfileMessage;
    private ListView newProfileListView;
    private int CODE = 523;

    private ArrayList<PlaceItem> placeItems = new ArrayList<>();

    private static Profile instance;

    public static Profile getInstance() {
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.new_profile, container, false);

        /***/
        initialise();

        newProfileMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.homeFragment, new ChatList()).commit();
                Home.getInstance().tabHost.setSelectedNavigationItem(3);
            }
        });


        newProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, ""), CODE);
            }
        });

        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.homeFragment, new ProfileSetting()).commit();
            }
        });

        /***/

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE) {
            try {
                Uri uri = data.getData();
                InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                AddProfileImageDB addProfileImageDB = new AddProfileImageDB();
                addProfileImageDB.execute(bitmap);
                ProfileDB profileDB = new ProfileDB();
                profileDB.execute();
                PlaceDB placeDB = new PlaceDB();
                placeDB.execute(6);
            } catch (Exception ex) {

            }
        }
    }

    private void initialise() {
        instance = this;
        newProfileUserName = view.findViewById(R.id.newProfileUserName);
        newProfileImage = view.findViewById(R.id.newProfileImage);
        sett = view.findViewById(R.id.sett);
        //newProfilePublication = view.findViewById(R.id.newProfilePublication);
        newProfileMessage = view.findViewById(R.id.newProfileMessage);
        newProfileListView = view.findViewById(R.id.newProfileListView);

        placeItems = new ArrayList<>();

        ProfileDB profileDB = new ProfileDB();
        profileDB.execute();

        PlaceDB placeDB = new PlaceDB();
        placeDB.execute(6);

        //GetImagesDB getImages = new GetImagesDB();
        //getImages.execute(Constants.USER_EMAIL);

    }

    /**
     * post adapter
     */
    private class PostAdapter extends BaseAdapter {
        private ArrayList<PlaceItem> placeItems = new ArrayList<>();

        private PostAdapter(ArrayList<PlaceItem> placeItems) {
            this.placeItems.addAll(placeItems);
        }

        @Override
        public int getCount() {
            return placeItems.size();
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.place_item2, null);

            ImageView placePostedImage = view1.findViewById(R.id.placePostedImage);
            final ImageView placeSave = view1.findViewById(R.id.placeSave);
            TextView placeCategory = view1.findViewById(R.id.placeCategory);
            TextView placeDescription = view1.findViewById(R.id.placeDescription);
            ImageView placeProfileImage = view1.findViewById(R.id.placeProfileImage);
            TextView placeProfileName = view1.findViewById(R.id.placeProfileName);
            TextView placePostedDate = view1.findViewById(R.id.placePostedDate);
            RelativeLayout relativeLayout = view1.findViewById(R.id.relativeLayoutImage);


            relativeLayout.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            relativeLayout.getLayoutParams().height = (Constants.SCREEN_WIDTH / 4) * 3;
            placePostedImage.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            placePostedImage.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

            placeCategory.setText(placeItems.get(i).getCategory());
            placeDescription.setText(placeItems.get(i).getDescription());
            Picasso.get().load(placeItems.get(i).getProfile()).into(placeProfileImage);
            Constants.bitmap123 = placeItems.get(i).getProfile();
            Picasso.get().load(placeItems.get(i).getPost()).into(placePostedImage);
            placeProfileName.setText(placeItems.get(i).getProfileName());
            Constants.name = placeItems.get(i).getProfileName();
            placePostedDate.setText(placeItems.get(i).getPostId().split(";")[2]);


            placeProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //FragmentTransaction fr = getFragmentManager().beginTransaction();
                    //fr.replace(R.id.homeFragment, new Profile2());
                    //fr.commit();
                }
            });
            placeProfileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //FragmentTransaction fr = getFragmentManager().beginTransaction();
                    //fr.replace(R.id.homeFragment, new Profile2());
                    //fr.commit();
                }
            });
            placePostedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //FragmentTransaction fr = getFragmentManager().beginTransaction();
                    //fr.replace(R.id.homeFragment, new PostFragement());
                    //Constants.IMAGE_ID = placeItems.get(i).getPostId();
                    //fr.commit();
                }

            });
            //final int[] f = {0};
            placeSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (placeSave.getTag().toString().isEmpty() || placeSave.getTag().equals("saved")) {
                        placeSave.setTag("unsaved");
                        placeSave.setImageDrawable(getResources().getDrawable(R.drawable.savepostblanc));
                        SavePostDB savePostDB = new SavePostDB();
                        savePostDB.execute(placeItems.get(i).getPostId().split(";")[0], Constants.USER_EMAIL);
                    } else {
                        placeSave.setTag("saved");
                        placeSave.setImageDrawable(getResources().getDrawable(R.drawable.savepostborderblanc));
                        SavePostDB savePostDB = new SavePostDB();
                        savePostDB.execute(placeItems.get(i).getPostId().split(";")[0], Constants.USER_EMAIL);
                    }
                }
            });

            return view1;
        }

    }


    /**
     * Reverse the array list
     */
    //private void reverseArrayList() {
    //    postedItemsReversed = new ArrayList<>();
    //    int n = postedItems.size() - 1;
    //    for (int i = n; 0 <= i; i--) {
    //        postedItemsReversed.add(this.postedItems.get(i));
    //    }
    //}

    //public void setImage() {
    //    postedItems.clear();
    //    for (int i = 0; i < Constants.imageUrls.size(); i++) {
    //        postedItems.add(new PostItem(Constants.imageUrls.get(i)));
    //    }

    //    reverseArrayList();

    //    PostAdapter postAdapter = new PostAdapter(postedItemsReversed);
    //    profileGridView.setAdapter(postAdapter);
    //}
    public void setData(String data) {

        String[] datas = data.split(";");
        if (datas.length == 2) {
            newProfileUserName.setText(datas[0]);
            Picasso.get().load(Constants.FOLDER_IMAGE + datas[1]).into(newProfileImage);
        }
    }

    public void data(ArrayList<PlaceItem> placeItems) {

        PostAdapter postAdapter = new PostAdapter(placeItems);
        newProfileListView.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();
        //newProfilePublication.setText(placeItems.size() + " Publications");

    }

}
