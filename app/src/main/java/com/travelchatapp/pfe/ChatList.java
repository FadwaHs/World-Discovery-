package com.travelchatapp.pfe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;



public class ChatList extends Fragment {


    ArrayList<ChatSenderItem> senderItems;
    ListView chatlistUserListView;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.chatlist, container, false);

        senderItems = new ArrayList<>();
        chatlistUserListView = view.findViewById(R.id.chatlistUserListView);


        /******************************** get data from server *******************************************/
        /***/senderItems.add(new ChatSenderItem(null, "user 1"));
        /***/senderItems.add(new ChatSenderItem(null, "user 2"));
        /***/senderItems.add(new ChatSenderItem(null, "user 3"));
        /***/senderItems.add(new ChatSenderItem(null, "user 4"));
        /***/senderItems.add(new ChatSenderItem(null, "user 5"));
        /***/senderItems.add(new ChatSenderItem(null, "user 6"));
        /***/senderItems.add(new ChatSenderItem(null, "user 7"));
        /***/senderItems.add(new ChatSenderItem(null, "user 8"));
        /***/senderItems.add(new ChatSenderItem(null, "user 9"));
        /***/senderItems.add(new ChatSenderItem(null, "user 10"));
        /******************************** *** *******************************************/

        ChatSender chatSender = new ChatSender(senderItems);
        chatlistUserListView.setAdapter(chatSender);



        return view;


    }


    private class ChatSender extends BaseAdapter {

        private ArrayList<ChatSenderItem> senderItems;

        public ChatSender(ArrayList<ChatSenderItem> senderItems) {
            this.senderItems = senderItems;
        }

        @Override
        public int getCount() {
            return senderItems.size();
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
            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater.inflate(R.layout.sender_item, null);

            ImageView senderProfileImg = view1.findViewById(R.id.senderItemProfileImg);
            LinearLayout SenderItemLinearlayout = view1.findViewById(R.id.SenderItemLinearlayout);

            SenderItemLinearlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Chat());
                    fr.commit();

                }
            });

            senderProfileImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.homeFragment, new Chat());
                    fr.commit();

                }
            });

            return view1;
        }
    }



}
