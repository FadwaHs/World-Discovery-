package com.travelchatapp.pfe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.squareup.picasso.Picasso;
import com.subinkrishna.widget.CircularImageView;
import java.util.ArrayList;


public class PostFragement extends Fragment {


    @SuppressLint("StaticFieldLeak")
    private static PostFragement instance;

    public static PostFragement getInstance() {
        return instance;
    }

    private ImageView ImagePost;
    private ImageView imageReaction3;
    private CircularImageView ProfileImage;
    private TextView ProfileName;
    private TextView placePostedDate;
    private TextView desc;
    private TextView descr;
    private Button category;
    private Button ville;
    private ImageView saveIcon;
    private ImageView favoriteIcon;
    private int g;
    private int f;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        instance = this;

        GetImageDB getImageDB = new GetImageDB();
        getImageDB.execute(0);


        View view = inflater.inflate(R.layout.postfragement, container, false);
        imageReaction3 = view.findViewById(R.id.imageReaction3);
        ImagePost = view.findViewById(R.id.ImagePost);
        ProfileImage = view.findViewById(R.id.ProfileImage);
        ville = view.findViewById(R.id.ville);
        ProfileName = view.findViewById(R.id.ProfileName);
        placePostedDate = view.findViewById(R.id.placePostedDate);
        desc = view.findViewById(R.id.desc);
        descr= view.findViewById(R.id.descr);
        ImagePost.getLayoutParams().width = Constants.SCREEN_WIDTH;
        ImagePost.getLayoutParams().height = (Constants.SCREEN_HEIGHT / 3) * 2;

        ville.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assert getFragmentManager() != null;
                //FragmentTransaction fr = getFragmentManager().beginTransaction();
                //fr.replace(R.id.homeFragment, new Explore());
                //fr.commit();
            }
        });
        category = view.findViewById(R.id.category);
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assert getFragmentManager() != null;
                //FragmentTransaction fr = getFragmentManager().beginTransaction();
                //fr.replace(R.id.homeFragment, new Categorie());
                //fr.commit();
            }
        });

        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.homeFragment, new Profile2());
                fr.commit();
            }
        });

        ImageView back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.homeFragment, new HomePage());
                fr.commit();
            }
        });

        ProfileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.homeFragment, new Profile2());
                fr.commit();
            }
        });
        favoriteIcon = view.findViewById(R.id.imageReaction);
        saveIcon = view.findViewById(R.id.imageReaction2);

        imageReaction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;

    }

    public void data(ArrayList<PlaceItem> placeItems) {
        Picasso.get().load(placeItems.get(0).getPost()).into(ImagePost);
        Picasso.get().load(placeItems.get(0).getProfile()).into(ProfileImage);
        ProfileName.setText(placeItems.get(0).getProfileName());
        placePostedDate.setText(placeItems.get(0).getCategory().split(";")[3]);
        desc.setText(placeItems.get(0).getDescription());
        category.setText(placeItems.get(0).getCategory().split(";")[0]);

        ville.setText(placeItems.get(0).getCategory().split(";")[1]);

        g = 0;
        saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (g == 0) {
                    saveIcon.setImageDrawable(getResources().getDrawable(R.drawable.saveposth));
                    g = 1;
                    SavePostDB savePostDB = new SavePostDB();
                    savePostDB.execute(placeItems.get(0).getPostId(),Constants.USER_EMAIL);
                    Toast.makeText(getContext(),"Saved",Toast.LENGTH_LONG).show();
                } else {
                    saveIcon.setImageDrawable(getResources().getDrawable(R.drawable.saveborderh));
                    g = 0;
                    SavePostDB savePostDB = new SavePostDB();
                    savePostDB.execute(placeItems.get(0).getPostId(),Constants.USER_EMAIL);
                    Toast.makeText(getContext(),"Unsaved",Toast.LENGTH_LONG).show();
                }
            }
        });

        f = 0;
        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (f == 0) {
                    favoriteIcon.setImageDrawable(getResources().getDrawable(R.drawable.favorite));
                    f = 1;
                    LikePostDB likePostDB = new LikePostDB();
                    likePostDB.execute(placeItems.get(0).getPostId(),Constants.USER_EMAIL);
                    Toast.makeText(getContext(),"Liked",Toast.LENGTH_LONG).show();
                } else {
                    favoriteIcon.setImageDrawable(getResources().getDrawable(R.drawable.favoriteborder));
                    f = 0;
                    LikePostDB likePostDB = new LikePostDB();
                    likePostDB.execute(placeItems.get(0).getPostId(),Constants.USER_EMAIL);
                    Toast.makeText(getContext(),"Disliked",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}


