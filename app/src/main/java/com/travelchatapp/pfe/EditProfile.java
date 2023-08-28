package com.travelchatapp.pfe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.InputStream;

public class EditProfile extends Fragment {

    private View view;
    private ProfileSetting profile;
    private ImageView profileImg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.edit_profle, container, false);

        /***/
        onBackClicked();
        onProfileImgClicked();



        return view;
    }

    private void onProfileImgClicked() {
        profileImg = view.findViewById(R.id.editProfileProfileImg);

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("images/*");
                startActivityForResult(Intent.createChooser(intent,""),381);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==641){
            try{
                Uri uri = data.getData();
                InputStream inputStream = Home.getInstance().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                profileImg = view.findViewById(R.id.editProfileProfileImg);
                profileImg.setImageBitmap(ImageProcessing.resizeImg(bitmap));

            }catch (Exception ex){

            }
        }
    }

    private void onBackClicked() {
        ImageView back = view.findViewById(R.id.editProfileBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile = new ProfileSetting();
                getFragmentManager().beginTransaction().replace(R.id.homeFragment, profile).commit();
            }
        });

        EditText name = view.findViewById(R.id.name);
        EditText email = view.findViewById(R.id.email);
        EditText local = view.findViewById(R.id.local);
        TextView update = view.findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                email.setText("");
                local.setText("");
                profileImg.setImageResource(R.drawable.ic_image);
                Toast.makeText(getContext(),"Updated",Toast.LENGTH_LONG).show();
            }
        });


    }


}
