package com.adrian.fakebot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

public class MainViewModel extends ViewModel {
    public ArrayList<Message> messageList= new ArrayList<>();
    private final ArrayList<String> botAnswers= new ArrayList<>();
    private final MutableLiveData<Integer> idMessage=new MutableLiveData<>();
    //public Integer idMessage=1;
    public MessageAdapter adapter= new MessageAdapter();

    public LiveData<Integer> getIdMessage() {
        return idMessage;
    }

    public MainViewModel(){
        botAnswers.add("Si");
        botAnswers.add("No");
        botAnswers.add("Pregunta de nuevo");
        botAnswers.add("Es muy probable");
        botAnswers.add("No lo creo");
        botAnswers.add("No lo sé");
        botAnswers.add("Tal vez");
        idMessage.setValue(1);
    }

    public void addUserMessage(String humanMessage){
        messageList.add(new Message(idMessage.getValue(),humanMessage,false));
        adapter.submitList(messageList);
        idMessage.setValue(idMessage.getValue()+1);
    }

    public void addBotMessage(){
        Random r= new Random();
        int randomPosition= r.nextInt(7);
        messageList.add(new Message(idMessage.getValue(),botAnswers.get(randomPosition),true));
        adapter.submitList(messageList);
        idMessage.setValue(idMessage.getValue()+1);
    }
}
