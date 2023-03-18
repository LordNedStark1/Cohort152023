package services;


import dto.request.ChatRequest;
import dto.request.UserRegistrationRequest;
import dto.response.UserRegistrationResponse;

import model.Message;
import model.chat.Chat;
import model.chat.ChatInterface;

import model.users.User;
import model.users.UserInterface;
import repositories.RepositoryInterface;


import java.security.SecureRandom;
import java.util.List;



public class UserServiceImpl implements UserService {
    SecureRandom random = new SecureRandom();
    private final RepositoryInterface repo;
    private long idCounter;

    public UserServiceImpl(RepositoryInterface repo ){
        this.repo = repo;
    }
    public RepositoryInterface getRepo(){
        return repo;
    }
    @Override
    public UserRegistrationResponse userSignUp(UserRegistrationRequest userRegistrationRequest) {

        User user = new User(repo);
        UserRegistrationResponse userResponse = new UserRegistrationResponse();

        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        user.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
        user.setEmailAddress(userRegistrationRequest.getEmailAddress());
        user.setUserId(generateId());
        user.setExisting(true);

        repo.saveNewUser(user);

        userResponse.setFirstName(userRegistrationRequest.getFirstName());
        userResponse.setLastName(userRegistrationRequest.getLastName());
        userResponse.setFullName(userRegistrationRequest.getFullName());
        userResponse.setMessage(1);
        userResponse.setUserId(user.getUserId());


        return userResponse;
    }
    private String generateId() {
        StringBuilder a_z = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        StringBuilder combined = new StringBuilder();

        idCounter ++;

        String number = Integer.toString( 1 + random.nextInt(100000));
        combined.append(number);

        combined.append(idCounter);

        combined.append(a_z.charAt(1 + random.nextInt(25)));
        combined.append(a_z.charAt(1 + random.nextInt(25)));

        return combined.toString();
    }
    private UserInterface findUserById(String userId){
       return repo.findUserById(userId);
    }
    @Override
    public String chat(ChatRequest chatRequest) {

        UserInterface receiver =  findUserById(chatRequest.getReceivingId());
        UserInterface sender = findUserById(chatRequest.getSenderId());

        String chatId = sender.findChatById(sender.getUserId(), receiver.getUserId());
        ChatInterface chat1 = sender.findChatById(chatId);


        if (receiver.isExisting() && chat1.isExisting() && sender.isExisting() ) {

            Message message = new Message();

            message.setSenderName(sender.getFullName());
            message.setSenderId(sender.getUserId());
            message.setDateTimeSent();
            message.setMessage(chatRequest.getRawMessage());
            message.setChatId(chatId);

            chat1.addMessageToChat(message);
            return "sent";

        }
        else if(receiver.isExisting() && !chat1.isExisting() && sender.isExisting()) {
            createNewPrivateChat(chatRequest, generateId());
            chat(chatRequest);
        }
        return  "Not Sent!";
    }

    private void createNewPrivateChat(ChatRequest chatRequest, String generatedChatId) {
        UserInterface userOne =  findUserById(chatRequest.getSenderId());
        UserInterface userTwo =  findUserById(chatRequest.getReceivingId());
        if (userOne.isExisting() && userTwo.isExisting()){

            String chatId = generatedChatId;

            ChatInterface chat = new Chat();

            chat.setChatId(chatId);
            chat.setExisting(true);
            chat.setUserOneId(chatRequest.getSenderId());
            chat.setUserTwoId(chatRequest.getReceivingId());

            userTwo.saveNewChat(chat);
            userOne.saveNewChat(chat);
        }
    }

    @Override
    public List<Message> viewChat(String receiverId, String senderId) {
        UserInterface receiver = findUserById(receiverId);
        UserInterface sender = findUserById(senderId);

        String chatId = sender.findChatById(sender.getUserId(), receiver.getUserId());

        ChatInterface chat = receiver.findChatById(chatId);

//        System.out.println(chat.viewMessages().size());
//        for (Message message : chat.viewMessages()) System.out.println(message);


       return chat.viewMessages();
    }
}
