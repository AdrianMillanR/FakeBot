package com.adrian.fakebot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.adrian.fakebot.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.messagesRecycler.setLayoutManager(new LinearLayoutManager(this));

        viewModel= new ViewModelProvider(this).get(MainViewModel.class);


        binding.messagesRecycler.setAdapter(viewModel.adapter);

        binding.sendMessageButton.setOnClickListener(v -> {
                addMessages();
        });

        if(viewModel.messageList.isEmpty()){
            binding.messageEmptyView.setVisibility(View.VISIBLE);
        }else{
            binding.messageEmptyView.setVisibility(View.GONE);
            binding.messagesRecycler.scrollToPosition(viewModel.getIdMessage().getValue()-2);

        }

        viewModel.getIdMessage().observe(this, idMessage -> {
            binding.messagesRecycler.scrollToPosition(idMessage - 2);
        });

    }

    private void addMessages(){
        String humanMessage= binding.messageText.getText().toString();
        if (humanMessage.isEmpty()){
            Toast.makeText(this,"You must write a message",Toast.LENGTH_SHORT).show();
        }else{
            binding.messageEmptyView.setVisibility(View.GONE);
           addUserMessage(humanMessage);
           Runnable c= () -> {
            addBotMessage();
           };
            Handler hand=new Handler();
            hand.postDelayed(c,2000);
        }
    }

    public void addUserMessage(String humanMessage){
        viewModel.addUserMessage(humanMessage);
        binding.messageText.setText("");
    }
    public void addBotMessage(){
        viewModel.addBotMessage();
    }
}