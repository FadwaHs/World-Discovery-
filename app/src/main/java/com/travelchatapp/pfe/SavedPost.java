package com.travelchatapp.pfe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class SavedPost extends Fragment {


    private ListView savedPostListView;
    private View view;
    private static SavedPost instance;

    public static SavedPost getInstance() {
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.saved_post, container, false);
        instance = this;
        savedPostListView = view.findViewById(R.id.savedPostListView);

        GetSavedPostDB getSavedPostDB = new GetSavedPostDB();
        getSavedPostDB.execute();

        //savedPostItems.add(new PlaceItem("",null, "aaa", "fffff", null, "ttttttt", "25/4/2016"));
        //savedPostItems.add(new PlaceItem("",null, "aaa", "fffff", null, "ttttttt", "25/4/2016"));
        //savedPostItems.add(new PlaceItem("",null, "aaa", "fffff", null, "ttttttt", "25/4/2016"));
        //savedPostItems.add(new PlaceItem("",null, "aaa", "fffff", null, "ttttttt", "25/4/2016"));
        //savedPostItems.add(new PlaceItem("",null, "aaa", "fffff", null, "ttttttt", "25/4/2016"));
        //savedPostItems.add(new PlaceItem("",null, "aaa", "fffff", null, "ttttttt", "25/4/2016"));
        //savedPostItems.add(new PlaceItem("",null, "aaa", "fffff", null, "ttttttt", "25/4/2016"));
        //savedPostItems.add(new PlaceItem("",null, "aaa", "fffff", null, "ttttttt", "25/4/2016"));


        return view;
    }

    class SavedPostAdapter extends BaseAdapter {
        private ArrayList<PlaceItem> savedPostItems;

        protected SavedPostAdapter(ArrayList<PlaceItem> savedPostItems) {
            this.savedPostItems = savedPostItems;
        }

        @Override
        public int getCount() {
            return savedPostItems.size();
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
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.saved_post_item, null);
            ImageView savedPostedImage = view1.findViewById(R.id.savedPostedImage);
            ImageView savedPlaceSave = view1.findViewById(R.id.savedPlaceSave);
            TextView savedCategory = view1.findViewById(R.id.savedCategory);
            TextView savedDescription = view1.findViewById(R.id.savedDescription);
            ImageView savedProfileImage = view1.findViewById(R.id.savedProfileImage);
            TextView savedProfileName = view1.findViewById(R.id.savedProfileName);
            TextView savedPostedDate = view1.findViewById(R.id.savedPostedDate);

            savedPostedImage.getLayoutParams().width = Constants.SCREEN_WIDTH;
            savedPostedImage.getLayoutParams().height = (Constants.SCREEN_HEIGHT / 2);

            Picasso.get().load(savedPostItems.get(i).getPost()).into(savedPostedImage);

            savedCategory.setText(savedPostItems.get(i).getCategory());
            savedDescription.setText(savedPostItems.get(i).getDescription());
            Picasso.get().load(savedPostItems.get(i).getProfile()).into(savedProfileImage);
            savedProfileName.setText(savedPostItems.get(i).getProfileName());
            savedPostedDate.setText(savedPostItems.get(i).getDate());

            savedPlaceSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (savedPlaceSave.getTag().equals("save")) {
                        savedPlaceSave.setTag("unsaved");
                        savedPlaceSave.setImageDrawable(getResources().getDrawable(R.drawable.savepostborderblanc));
                        SavePostDB savePostDB = new SavePostDB();
                        savePostDB.execute(savedPostItems.get(i).getPostId(), Constants.USER_EMAIL);
                        Toast.makeText(getContext(),"Unsaved",Toast.LENGTH_SHORT).show();
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.homeFragment, new SavedPost());
                        fr.commit();
                    } else {
                        savedPlaceSave.setTag("save");
                        savedPlaceSave.setImageDrawable(getResources().getDrawable(R.drawable.savepostblanc));
                        SavePostDB savePostDB = new SavePostDB();
                        savePostDB.execute(savedPostItems.get(i).getPostId(), Constants.USER_EMAIL);
                        Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //Toast.makeText(getContext(),"ffffffffffff",Toast.LENGTH_SHORT).show();

            return view1;
        }
    }

    public void setData(ArrayList<PlaceItem> savedPostItems) {
        SavedPostAdapter savedPostAdapter = new SavedPostAdapter(savedPostItems);
        savedPostListView.setAdapter(savedPostAdapter);
        savedPostAdapter.notifyDataSetChanged();
    }
}
