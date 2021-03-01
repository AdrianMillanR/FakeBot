package com.adrian.fakebot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.adrian.fakebot.databinding.ActivityMainBinding;

import java.util.ArrayList;

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

    }

    private void addMessages(){
        String humanMessage= binding.messageText.getText().toString();
        if (humanMessage.isEmpty()){
            Toast.makeText(this,"You must write a message",Toast.LENGTH_SHORT).show();
        }else{
            messageList.add(new Message(idMessage,humanMessage,false));
            idMessage++;
            int randomPosition= (int)Math.random()*8;
            messageList.add(new Message(idMessage,botAnswers.get(randomPosition),true));
            idMessage++;
            adapter.submitList(messageList);
            binding.messageText.setText("");
        }
    }
}