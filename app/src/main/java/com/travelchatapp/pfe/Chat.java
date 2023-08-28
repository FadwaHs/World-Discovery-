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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Chat extends Fragment {


    ArrayList<MessageItem> messageItems;
    ListView chatListView;
    EditText writeMessage;
    ImageView sendMessage;

    private ImageView backtochat;
    private TextView chatuser;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.chat, container, false);


        backtochat = view.findViewById(R.id.Chatbackicon);
        backtochat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.homeFragment,new com.travelchatapp.pfe.ChatList()).commit();
            }
        });

            chatuser = view.findViewById(R.id.senderItemName);
            chatuser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager().beginTransaction().replace(R.id.homeFragment,new Profile2()).commit();
                }
            });

        messageItems = new ArrayList<>();
        chatListView = view.findViewById(R.id.chatListView);
        writeMessage = view.findViewById(R.id.chatWriteMessage);
        sendMessage = view.findViewById(R.id.chatSendMessage);

        /******************************** get data from server *******************************************/
        /***/
        /***/messageItems.add(new MessageItem("me", "Hi"));
        /***/messageItems.add(new MessageItem("you", "Hello"));
        /***/messageItems.add(new MessageItem("me", "nice"));
        /***/messageItems.add(new MessageItem("you", "nice nice"));
        /***/messageItems.add(new MessageItem("me", "Thanks"));
        /***********************************************************************************************/


        final ChatList chatList = new ChatList(messageItems);
        chatListView.setAdapter(chatList);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (writeMessage.getText() != null) {
                    messageItems.add(new MessageItem("me", writeMessage.getText().toString()));
                    messageItems.add(new MessageItem("you", "Thanks"));
                    chatList.notifyDataSetChanged();
                    writeMessage.setText("");
                    chatListView.smoothScrollToPosition(chatList.getCount());

                }
            }
        });

        return view;
    }

    private class ChatList extends BaseAdapter {

        ArrayList<MessageItem> chatList;

        public ChatList(ArrayList<MessageItem> chatList) {
            this.chatList = chatList;
        }

        @Override
        public int getCount() {
            return chatList.size();
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
            int flag = 0;

            LayoutInflater inflater1 = getLayoutInflater();
            LayoutInflater inflater2 = getLayoutInflater();

            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = inflater1.inflate(R.layout.message_send, null);
            @SuppressLint({"ViewHolder", "InflateParams"}) View view2 = inflater2.inflate(R.layout.message_receive, null);

            TextView send = view1.findViewById(R.id.messageSendSender);
            TextView receive = view2.findViewById(R.id.messageReceiveReceiver);


            if (chatList.get(i).getSenderID().equals("me")) {
                send.setText(chatList.get(i).getMessage());
            } else {
                receive.setText(chatList.get(i).getMessage());
                flag = 1;
            }

            return flag == 0 ? view1 : view2;
        }
    }


}
