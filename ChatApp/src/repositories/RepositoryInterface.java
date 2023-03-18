package repositories;

import model.Message;

import model.chat.Chat;
import model.chat.ChatInterface;
import model.users.User;
import model.users.UserInterface;

public interface RepositoryInterface {
    void saveNewUser(User newUser);


    UserInterface findUserById(String userId);

    void saveChat(ChatInterface chat);


    ChatInterface findChatById(String chatId);

    String findChatById(String userOneId, String userTwoId);
}
