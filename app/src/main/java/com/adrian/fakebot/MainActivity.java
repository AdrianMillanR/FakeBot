package com.adrian.fakebot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.adrian.fakebot.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.messagesRecycler.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Message> messageList= new ArrayList<>();
        messageList.add(new Message(1,"¿Comeré Tacos hoy?", false));
        messageList.add(new Message(2,"No", true));
        messageList.add(new Message(3,"¿Viviré eternamente?", false));
        messageList.add(new Message(4,"Si", true));

        MessageAdapter adapter= new MessageAdapter();
        binding.messagesRecycler.setAdapter(adapter);
        adapter.submitList(messageList);
    }
}