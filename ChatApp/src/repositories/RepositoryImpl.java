package repositories;

import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.NullChat;
import model.users.NullUser;
import model.users.User;
import model.users.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements RepositoryInterface {
    List<User> users = new ArrayList<>();
    List<ChatInterface> chats = new ArrayList<>();


    @Override
    public void saveNewUser(User newUser) {
        users.add(newUser);
    }

    @Override
    public UserInterface findUserById(String userId) {
        for(User user : users) if(user.getUserId().equals(userId)) return user;
        NullUser nullUser = new NullUser();
        return nullUser;
    }

    @Override
    public void saveChat(ChatInterface chat) {
        chats.add(chat);

    }

    @Override
    public ChatInterface findChatById(String chatId) {
        for(ChatInterface chat : chats) if (chat.getChatId().equals( chatId)) return chat;
        NullChat nullChat = new NullChat();

        return nullChat;
    }

    @Override
    public String findChatById(String userOneId, String userTwoId) {
        for(ChatInterface chat : chats)
            if (chat.getUserOneId().equals(userOneId) && chat.getUserTwoId().equals(userTwoId)){
                return chat.getChatId();
            }
        else if (chat.getUserTwoId().equals(userOneId) && chat.getUserOneId().equals(userTwoId)){
                return chat.getChatId();
            }


        return "nullChatId";
    }


}
