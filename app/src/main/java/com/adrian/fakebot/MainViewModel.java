package com.adrian.fakebot;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

public class MainViewModel extends ViewModel {
    public ArrayList<Message> messageList= new ArrayList<>();
    private ArrayList<String> botAnswers= new ArrayList<>();
    public Integer idMessage=1;
    public MessageAdapter adapter= new MessageAdapter();

    public MainViewModel(){
        botAnswers.add("Si");
        botAnswers.add("No");
        botAnswers.add("Pregunta de nuevo");
        botAnswers.add("Es muy probable");
        botAnswers.add("No lo creo");
        botAnswers.add("No lo sé");
        botAnswers.add("Tal vez");
    }

    public void addUserMessage(String humanMessage){
        messageList.add(new Message(idMessage,humanMessage,false));
        idMessage++;
        adapter.submitList(messageList);
    }

    public void addBotMessage(){
        Random r= new Random();
        int randomPosition= r.nextInt(7);
        messageList.add(new Message(idMessage,botAnswers.get(randomPosition),true));
        idMessage++;
        adapter.submitList(messageList);
    }
}
