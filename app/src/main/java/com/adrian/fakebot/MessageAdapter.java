package com.adrian.fakebot;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian.fakebot.databinding.MessageListItemBinding;

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
        MessageListItemBinding binding= MessageListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MessageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder holder, int position) {
        Message message= getItem(position);
        holder.bind(message);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{

        private final MessageListItemBinding binding;

        public MessageViewHolder(@NonNull MessageListItemBinding binding) {
            super(binding.getRoot());

            this.binding=binding;

        }

        public void bind(Message message){
            if(message.isItsBotMessage()){
                binding.messageContainer.setBackgroundColor(Color.parseColor("#ebecf3"));
                binding.messageText.setGravity(Gravity.START);
            }else{
                binding.messageContainer.setBackgroundColor(Color.parseColor("#d1e6da"));
                binding.messageText.setGravity(Gravity.END);
            }
            binding.messageText.setText(String.valueOf(message.getContent()));

            binding.executePendingBindings();
        }
    }
}
