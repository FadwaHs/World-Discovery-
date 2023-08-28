package com.travelchatapp.pfe;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class HomePage extends Fragment {

    private View view;
    private ArrayList<CityItem> cityItems;
    private HorizontalListView cityListView;
    private HorizontalListView placeListView;
    private HorizontalListView placeListView2;
    private HorizontalListView placeListView3;
    private static HomePage instance;
    private boolean SAVE_FLAG = true;

    public static HomePage getInstance() {
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.home_page, container, false);

        //Toast.makeText(getContext(),"000000000000",Toast.LENGTH_LONG).show();
        //Drawable drawable = Home.getInstance().getDrawable(R.drawable.home);
        instance = this;
        cityListView = view.findViewById(R.id.homePageCityListView);
        placeListView = view.findViewById(R.id.homePagePlaceListView);
        placeListView2 = view.findViewById(R.id.homePagePlaceListView2);
        placeListView3 = view.findViewById(R.id.homePagePlaceListView3);

        EditText editText = view.findViewById(R.id.searchBar);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.homeFragment, new SearchFragement()).commit();
                GetSearchPostDB getSearchPostDB = new GetSearchPostDB();
                getSearchPostDB.execute(editText.getText().toString());
            }
        });

        cityItems = new ArrayList<>();


        /******************************************************* get data from server **************************
         */
        cityItems.add(new CityItem("yy", R.drawable.download1, "Paris"));
        cityItems.add(new CityItem("yy", R.drawable.download2, "New York City"));
        cityItems.add(new CityItem("yy", R.drawable.download3, "London"));
        cityItems.add(new CityItem("yy", R.drawable.download4, "Delhi"));
        cityItems.add(new CityItem("yy", R.drawable.download5, "Barcelona"));
        //cityItems.add(new CityItem("yy",null, "Tokyo"));
        //cityItems.add(new CityItem("yy",null, "Berlin"));
        //cityItems.add(new CityItem("yy",null, "Rome"));
        //cityItems.add(new CityItem("yy",null, "Madrid"));
        //cityItems.add(new CityItem("yy",null, "Lisbon"));
        //cityItems.add(new CityItem("yy",null, "Morocco"));
        //cityItems.add(new CityItem("yy",null, "Turkey"));
        /**
         /***************************************************************************************************************************************************/

        CityAdapter cityAdapter = new CityAdapter(cityItems);
        cityListView.setAdapter(cityAdapter);
        cityAdapter.notifyDataSetChanged();
        //CityAdapter cityAdapter = new CityAdapter(cityItems);
        //cityListView.setAdapter(cityAdapter);


        PlaceDB placeDB = new PlaceDB();
        //placeDB.execute(4);
        //placeDB = new PlaceDB();
        placeDB.execute(1);
        placeDB = new PlaceDB();
        placeDB.execute(2);
        placeDB = new PlaceDB();
        placeDB.execute(3);
        return view;
    }

    public void data(ArrayList<PlaceItem> placeItems, int flag) {

        if (flag == 1) {
            PlaceAdapter placeAdapter = new PlaceAdapter(placeItems);
            placeListView.setAdapter(placeAdapter);
            placeAdapter.notifyDataSetChanged();
        } else if (flag == 2) {
            PlaceAdapter2 placeAdapter2 = new PlaceAdapter2(placeItems);
            placeListView2.setAdapter(placeAdapter2);
            placeAdapter2.notifyDataSetChanged();
        } else if (flag == 3) {
            PlaceAdapter3 placeAdapter3 = new PlaceAdapter3(placeItems);
            placeListView3.setAdapter(placeAdapter3);
            placeAdapter3.notifyDataSetChanged();
        } else if (flag == 4) {
            //ArrayList<CityItem> cityItem = new ArrayList<>();
            //for (int i = 0; i < placeItems.size(); i++) {
            //    ///cityItem.add(new CityItem(placeItems.get(i).getPostId().split(";")[0], placeItems.get(i).getPost(), placeItems.get(i).getDate()));
            //}
            //CityAdapter cityAdapter = new CityAdapter(cityItem);
            //cityListView.setAdapter(cityAdapter);
            //cityAdapter.notifyDataSetChanged();
        }
    }


    /**
     * city adapter
     */
    private class CityAdapter extends BaseAdapter {
        private ArrayList<CityItem> cityItems;

        private CityAdapter(ArrayList<CityItem> cityItems) {
            this.cityItems = cityItems;
        }

        @Override
        public int getCount() {
            return cityItems.size();
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
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.city_item, null);

            ImageView image = view1.findViewById(R.id.cityItemImage);
            TextView description = view1.findViewById(R.id.cityItemDescription);

            //image.setImageResource(R.drawable.koala);
            Picasso.get().load(cityItems.get(i).getBitmap()).into(image);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr1 = getFragmentManager().beginTransaction();
                    fr1.replace(R.id.homeFragment, new Explore());
                    fr1.commit();
                }
            });

            description.setText(cityItems.get(i).getDescription());


            return view1;
        }
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
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.place_item, null);

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
                    FragmentTransaction fr2 = getFragmentManager().beginTransaction();
                    Constants.EMAIL = placeItems.get(i).getPostId().split(";")[0];
                    fr2.replace(R.id.homeFragment, new Profile2());
                    fr2.commit();
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
                    if (placeSave.getTag().toString().isEmpty() || placeSave.getTag().equals("save")) {
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

    /**
     * place adapter
     */
    private class PlaceAdapter2 extends BaseAdapter {

        private ArrayList<PlaceItem> placeItems;

        public PlaceAdapter2(ArrayList<PlaceItem> placeItems) {
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
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.place_item, null);


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
            placePostedDate.setText(placeItems.get(i).getPostId().split(";")[2]);

            placeProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Profile2());
                    Constants.EMAIL = placeItems.get(i).getPostId().split(";")[1];
                    fr.commit();
                }
            });
            placeProfileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Profile2());
                    Constants.EMAIL = placeItems.get(i).getPostId().split(";")[1];
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
     * place adapter
     */
    private class PlaceAdapter3 extends BaseAdapter {

        private ArrayList<PlaceItem> placeItems;

        public PlaceAdapter3(ArrayList<PlaceItem> placeItems) {
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
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.place_item, null);


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
            placePostedDate.setText(placeItems.get(i).getPostId().split(";")[2]);


            placeProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Profile2());
                    Constants.EMAIL = placeItems.get(i).getPostId().split(";")[1];
                    fr.commit();
                }
            });
            placeProfileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Profile2());
                    Constants.EMAIL = placeItems.get(i).getPostId().split(";")[1];
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

    public void savePost(String result) {
        //ImageView imageView = (ImageView)(((ViewGroup) placeListView.getChildAt(index)).getChildAt(4));

        //View view = placeListView.getChildAt(index);
        //ImageView imageView = (ImageView) (((ViewGroup) view).getChildAt(1));
        //imageView.setImageDrawable(getResources().getDrawable(R.drawable.savepostblanc));

        if (result.equals("deleted")) {
            Toast.makeText(getContext(), "post now unsaved", Toast.LENGTH_SHORT).show();
        } else if (result.equals("inserted")) {
            Toast.makeText(getContext(), "post now saved", Toast.LENGTH_SHORT).show();
        }
        SAVE_FLAG = !SAVE_FLAG;
    }


}
























