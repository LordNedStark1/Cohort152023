package model.users;

import model.chat.ChatInterface;

public interface UserInterface {
    ChatInterface findChatById(String chatId);
    String findChatById(String userOneId, String userTwoId);

    boolean isExisting();

    String getFullName();

    String getUserId();

    void saveNewChat(ChatInterface chat);


}
