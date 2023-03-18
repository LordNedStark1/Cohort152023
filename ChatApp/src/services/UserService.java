package services;


import dto.request.ChatRequest;
import dto.request.UserRegistrationRequest;
import dto.response.UserRegistrationResponse;
import model.Message;


import java.util.List;

public interface UserService {

    UserRegistrationResponse userSignUp(UserRegistrationRequest userRegistrationRequest);
    String chat(ChatRequest chatRequest);
    List<Message> viewChat(String receiverId, String senderId);
}
