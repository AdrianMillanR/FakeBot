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
        messageList.add(new Message("¿Comeré Tacos hoy?", false));
        messageList.add(new Message("No", true));
        messageList.add(new Message("¿Viviré eternamente?", false));
        messageList.add(new Message("Si", true));
    }
}