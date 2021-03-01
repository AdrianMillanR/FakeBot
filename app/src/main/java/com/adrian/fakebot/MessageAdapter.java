package com.adrian.fakebot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends ListAdapter<Message, MessageAdapter.MessageViewHolder> {

    public static final DiffUtil.ItemCallback<Message> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Message>() {
                @Override
                public boolean areItemsTheSame(@NonNull Message oldMessage, @NonNull Message newMessage) {
                    return oldMessage.getId().equals(newMessage.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Message oldMessage, @NonNull Message newMessage) {
                    return oldMessage.equals(newMessage);
                }
            };


    protected MessageAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item,parent,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder holder, int position) {
        Message message= getItem(position);
        holder.bind(message);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{

        private TextView messageText;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            messageText=itemView.findViewById(R.id.message_text);
        }

        public void bind(Message message){
            messageText.setText(String.valueOf(message.getContent()));
        }
    }
}
