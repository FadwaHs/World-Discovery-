package com.travelchatapp.pfe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class ProfileSetting extends Fragment {

    private View view;
    private ImageView back;
    private EditProfile editProfile;
    private Profile profile;
    private Button btnlogout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.profile_setting, container, false);


        /***/
        initialise();
        onEditProfileClicked();
        onBackClicked();

        btnlogout = view.findViewById(R.id.btnLogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PreferencesUtils.removeSession(getActivity());

                MoveToLogin();

            }
        });


        ImageView imageView = view.findViewById(R.id.img);
        Picasso.get().load(Constants.bitmap123).into(imageView);
        TextView textView = view.findViewById(R.id.userName);
        textView.setText(Constants.name);


        return view;
    }


    private void MoveToLogin() {

        Intent intent = new Intent(getActivity(), Interface3Login.class);
        startActivity(intent);


    }

    private void initialise() {
        editProfile = new EditProfile();
        profile = new Profile();
        back = view.findViewById(R.id.profileSettingBack);
    }

    /**
     * on edit profile_setting clicked
     */
    private void onEditProfileClicked() {
        ImageView editProfileImg = view.findViewById(R.id.profileEditProfileImg);
        Button editProfilebtn = view.findViewById(R.id.profileEditProfileTxt);

        editProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.homeFragment, editProfile).commit();
            }
        });

        editProfilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.homeFragment, editProfile).commit();
            }
        });

    }

    private void onBackClicked() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.homeFragment, profile).commit();
            }
        });
    }
}
