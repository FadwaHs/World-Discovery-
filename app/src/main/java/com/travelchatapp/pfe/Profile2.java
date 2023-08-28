package com.travelchatapp.pfe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;


public class Profile2 extends Fragment {

    private List<PlaceItem> postItem;
    private ListView postList;
    private View view1;
    private static Profile2 instance;
    private ImageView cityItemImage;
    private TextView cityItemDescription;
    private Button sendMessage;

    public static Profile2 getInstance() {
        return instance;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profil, container, false);

        instance = this;

        view1 = inflater.inflate(R.layout.viewp, container, false);

        cityItemImage = view1.findViewById(R.id.cityItemImage);
        cityItemDescription = view1.findViewById(R.id.cityItemDescription);
        sendMessage = view1.findViewById(R.id.sendMessage);

        postList = view.findViewById(R.id.list_View);
        postItem = new ArrayList<>();

        ImageView back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.homeFragment, new HomePage());
                fr.commit();
            }
        });
        PlaceDB placeDB = new PlaceDB();
        placeDB.execute(10);

        Constants.Flag2 = true;
        ProfileDB profileDB = new ProfileDB();
        profileDB.execute();

        return view;
    }

    public class PostlistAdapter extends BaseAdapter {
        private List<PlaceItem> placeItems;

        public PostlistAdapter(List<PlaceItem> placeItems) {
            this.placeItems = placeItems;
        }

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
        public View getView(int i, View convertView, ViewGroup parent) {
            ImageView placePostedImage;
            ImageView placeSave;
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
            RelativeLayout relativeLayout = view1.findViewById(R.id.relativeLayoutImage);


            relativeLayout.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            relativeLayout.getLayoutParams().height = (Constants.SCREEN_WIDTH / 4) * 3;
            placePostedImage.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            placePostedImage.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

            placeCategory.setText(placeItems.get(i).getCategory());
            placeDescription.setText(placeItems.get(i).getDescription());
            Picasso.get().load(placeItems.get(i).getProfile()).into(placeProfileImage);
            Picasso.get().load(placeItems.get(i).getPost()).into(placePostedImage);
            placeProfileName.setText(placeItems.get(i).getProfileName());
            placePostedDate.setText(placeItems.get(i).getPostId().split(";")[2]);


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
//
    }


    public void data(ArrayList<PlaceItem> placeItems) {
        postItem.addAll(placeItems);
        PostlistAdapter postAdapter = new PostlistAdapter(postItem);
        postList.setAdapter(postAdapter);
        postList.addHeaderView(view1);
        postAdapter.notifyDataSetChanged();
    }

    public void setData(String data){
        String[] datas = data.split(";");
        if (datas.length == 2) {
            cityItemDescription.setText(datas[0]);
            Picasso.get().load(Constants.FOLDER_IMAGE + datas[1]).into(cityItemImage);
        }
    }

}
