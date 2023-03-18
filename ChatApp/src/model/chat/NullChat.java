package model.chat;

import model.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NullChat implements ChatInterface{
    private boolean isExisting;

    public boolean isExisting() {
        return isExisting;
    }

    @Override
    public void addMessageToChat(Message message) {

    }

    public void setExisting(boolean existing) {
        isExisting = false;
    }

    @Override
    public void setChatId(String chatId) {

    }

    @Override
    public List<Message> viewMessages() {
        List<Message> messages = new ArrayList<>();

        Message message = new Message();
        message.setMessage("Chat does not exist");
        messages.add(message);

        return messages ;
    }

    @Override
    public String getChatId() {
        return "Chat does not exist";
    }

    @Override
    public void setUserOneId(String userOneId) {

    }

    public static void main(String[] args) {
        int [] nums = {1,2,3,4,5};
        System.out.println((Arrays.toString(method(nums))));
    }
    public static int[] method(int[] user_input) {
        int[] new_array = new int[user_input.length];
        for (int i = user_input.length - 1, j = 0; i >= 0; i--, j++) {
            new_array[j] = user_input[i];
        }
        return new_array;
    }

    @Override
    public void setUserTwoId(String userTwoId) {

    }

    @Override
    public String getUserOneId() {
        return "Chat Not Found!";
    }

    @Override
    public String getUserTwoId() {
        return "Chat Not Found!";
    }
}
