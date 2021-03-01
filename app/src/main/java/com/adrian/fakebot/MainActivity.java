package com.adrian.fakebot;

import androidx.appcompat.app.AppCompatActivity;
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
    private  ArrayList<Message> messageList= new ArrayList<>();
    private ArrayList<String> botAnswers= new ArrayList<>();
    private Integer idMessage=1;
    private MessageAdapter adapter= new MessageAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.messagesRecycler.setLayoutManager(new LinearLayoutManager(this));


        botAnswers.add("Si");
        botAnswers.add("No");
        botAnswers.add("Pregunta de nuevo");
        botAnswers.add("Es muy probable");
        botAnswers.add("No lo creo");
        botAnswers.add("No lo sé");
        botAnswers.add("Tal vez");


        binding.messagesRecycler.setAdapter(adapter);
        //adapter.submitList(messageList);

        binding.sendMessageButton.setOnClickListener(v -> {
                addMessages();
        });

        if(messageList.isEmpty()){
            binding.messageEmptyView.setVisibility(View.VISIBLE);
        }else{
            binding.messageEmptyView.setVisibility(View.GONE);
        }

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
        messageList.add(new Message(idMessage,humanMessage,false));
        idMessage++;
        adapter.submitList(messageList);
        binding.messagesRecycler.scrollToPosition(idMessage-2);
        binding.messageText.setText("");
    }
    public void addBotMessage(){
        Random r= new Random();
        int randomPosition= r.nextInt(7);
        messageList.add(new Message(idMessage,botAnswers.get(randomPosition),true));
        idMessage++;
        adapter.submitList(messageList);
        binding.messagesRecycler.scrollToPosition(idMessage-2);
    }
}