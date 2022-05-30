package com.example.appcomidi.Adapter.User;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appcomidi.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcomidi.Model.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ChatMessage> chatMessages;
    private final String imageReceiverLink;
    private final String senderId;
    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVER = 2;

    public ChatAdapter(List<ChatMessage> chatMessages, String imageReceiverLink, String senderId) {
        this.chatMessages = chatMessages;
        this.imageReceiverLink = imageReceiverLink;
        this.senderId = senderId;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder
    {

        private TextView textMess,textTime;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            textMess=itemView.findViewById(R.id.textMessage);
            textTime=itemView.findViewById(R.id.textDateTime);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lineitemchatmess,parent,false);
        return new ChatViewHolder(view);
    }
    static class SentMessageViewHolder extends RecyclerView.ViewHolder{

        public SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    static class ReceiverMessageViewHolder extends RecyclerView.ViewHolder{

        public ReceiverMessageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

}
