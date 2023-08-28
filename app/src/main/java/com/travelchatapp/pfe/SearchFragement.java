package com.travelchatapp.pfe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;
import com.travelchatapp.pfe.Explore;
import com.travelchatapp.pfe.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragement extends Fragment {
    private ListView list;
    private static SearchFragement instance;

    public static SearchFragement getInstance() {
        return instance;
    }

    private TextView cancel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        instance = this;
        View view = inflater.inflate(R.layout.searchfragement, container, false);
        list = view.findViewById(R.id.listSearch);
        cancel = view.findViewById(R.id.Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.homeFragment, new HomePage());
                fr.commit();
            }
        });

        EditText editText = view.findViewById(R.id.searchEdit);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetSearchPostDB getSearchPostDB = new GetSearchPostDB();
                getSearchPostDB.execute(editText.getText().toString());
            }
        });

        return view;
    }

    /**
     * place adapter
     */
    private class PlaceAdapter extends BaseAdapter {

        private ArrayList<PlaceItem> placeItems;

        public PlaceAdapter(ArrayList<PlaceItem> placeItems) {
            this.placeItems = placeItems;
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.place_item3, null);


            ImageView placePostedImage = view1.findViewById(R.id.placePostedImage);
            final ImageView placeSave = view1.findViewById(R.id.placeSave);
            TextView placeCategory = view1.findViewById(R.id.placeCategory);
            TextView placeDescription = view1.findViewById(R.id.placeDescription);
            ImageView placeProfileImage = view1.findViewById(R.id.placeProfileImage);
            TextView placeProfileName = view1.findViewById(R.id.placeProfileName);
            TextView placePostedDate = view1.findViewById(R.id.placePostedDate);
            RelativeLayout relativeLayout = view1.findViewById(R.id.relativeLayoutImage);


            relativeLayout.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            relativeLayout.getLayoutParams().height = (Constants.SCREEN_WIDTH / 3) * 2;
            placePostedImage.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            placePostedImage.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

            placeCategory.setText(placeItems.get(i).getCategory());
            placeDescription.setText(placeItems.get(i).getDescription());
            Picasso.get().load(placeItems.get(i).getProfile()).into(placeProfileImage);
            Picasso.get().load(placeItems.get(i).getPost()).into(placePostedImage);
            placeProfileName.setText(placeItems.get(i).getProfileName());
            placePostedDate.setText(placeItems.get(i).getPostId().split(";")[1]);

            placeProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Profile2());
                    Constants.EMAIL = placeItems.get(i).getPostId().split(";")[0];
                    fr.commit();
                }
            });
            placeProfileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Profile2());
                    Constants.EMAIL = placeItems.get(i).getPostId().split(";")[0];
                    fr.commit();
                }
            });
            placePostedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new PostFragement());
                    Constants.IMAGE_ID = placeItems.get(i).getPostId().split(";")[0];
                    Constants.EMAIL = placeItems.get(i).getPostId().split(";")[1];
                    fr.commit();
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
                        savePostDB.execute(placeItems.get(i).getPostId().split(";")[1], Constants.USER_EMAIL);
                    } else {
                        placeSave.setTag("saved");
                        placeSave.setImageDrawable(getResources().getDrawable(R.drawable.savepostborderblanc));
                        SavePostDB savePostDB = new SavePostDB();
                        savePostDB.execute(placeItems.get(i).getPostId().split(";")[1], Constants.USER_EMAIL);
                    }
                }
            });


            return view1;
        }
    }

    public void data(ArrayList<PlaceItem> placeItems) {
        PlaceAdapter placeAdapter = new PlaceAdapter(placeItems);
        list.setAdapter(placeAdapter);
        placeAdapter.notifyDataSetChanged();
    }

}
