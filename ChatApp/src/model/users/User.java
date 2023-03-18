package model.users;

import model.Message;
import model.chat.Chat;
import model.chat.ChatInterface;
import repositories.RepositoryInterface;

public class User implements UserInterface {
    private boolean isExisting;
    private String userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private RepositoryInterface repo;
    public User (RepositoryInterface repo){
        this.repo = repo;
    }

    public boolean isExisting() {
        return isExisting;
    }

    public void setExisting(boolean existing) {
        isExisting = existing;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getFullName() {
        return firstName + " " + lastName;
    }


    public ChatInterface findChatById(String chatId) {
       return repo.findChatById(chatId);
    }

    @Override
    public String findChatById(String userOneId, String userTwoId) {
        return repo.findChatById(userOneId,userTwoId) ;
    }

    public void  saveNewChat(ChatInterface chat) {
        repo.saveChat(chat);
    }
}
