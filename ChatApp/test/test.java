import dto.request.ChatRequest;
import dto.request.UserRegistrationRequest;
import dto.response.UserRegistrationResponse;
import model.Message;

import model.users.UserInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.RepositoryImpl;
import repositories.RepositoryInterface;
import services.UserService;
import services.UserServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class test {
    UserService userService;
    RepositoryInterface repo;
    UserRegistrationRequest userReg;
    UserRegistrationRequest userReg2;
    UserRegistrationRequest userReg3;
    UserRegistrationResponse userRegRes;
    UserRegistrationResponse userRegRes2;
    UserRegistrationResponse userRegRes3;
    UserInterface user1;
    UserInterface user2;
    UserInterface user3;
    ChatRequest chatRequest1;
    ChatRequest chatRequest2;

    @BeforeEach
    void setUp(){
        repo = new RepositoryImpl() ;
        userService = new UserServiceImpl(repo);

        chatRequest1 = new ChatRequest();
        chatRequest2 = new ChatRequest();

        userReg = new UserRegistrationRequest();
        userReg.setFirstName("Benjamin");
        userReg.setLastName("Osisiogu");

        userReg2 = new UserRegistrationRequest();
        userReg2.setFirstName("Ned");
        userReg2.setLastName("Stark");

        userReg3 = new UserRegistrationRequest();
        userReg3.setFirstName("Legend");
        userReg3.setLastName("Odogwu");

        userRegRes = userService.userSignUp(userReg);
        userRegRes2 = userService.userSignUp(userReg2);
         userRegRes3 =  userService.userSignUp(userReg3);

       user1 = repo.findUserById(userRegRes.getUserId());
       user2 = repo.findUserById(userRegRes2.getUserId());
        user3 = repo.findUserById(userRegRes3.getUserId());
    }
    @Test
    void confirmSignUp(){
        String first = "User id is : "+ userRegRes.getUserId() + "\nFull name is : "
                + userRegRes.getFullName()+ "\n" +  "SignUp successful!\n";
        String second = "User id is : "+ userRegRes2.getUserId() + "\nFull name is : "
                + userRegRes2.getFullName()+ "\n" +  "SignUp successful!\n";

        assertEquals(String.valueOf(userRegRes),first);
        assertEquals(String.valueOf(userRegRes2),second);

        assertEquals(user1.getUserId(), userRegRes.getUserId());
        assertEquals(user2.getUserId(), userRegRes2.getUserId());

    }
    @Test
    void chatOnce(){
        String message1 = "Special note from me to you. \nBelieve in yourself";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(message1);

        userService.chat(chatRequest1);

        List <Message> receivedMessage =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message message2 = receivedMessage.get(0);


        String messageToAssert = "Message from " + message2.getSenderName() + " "+ message2.getSenderId()
                +"\n" + message1 + "\n" + message2.getTime() + "\n";


        assertEquals(messageToAssert, String.valueOf(message2));
    }
    @Test
    void chatOnceWithoutNullPointerOnChat(){
        String message1 = "Special note from me to you. \nBelieve in yourself";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(message1);

        userService.chat(chatRequest1);

        List <Message> receivedMessage =userService.viewChat("user2.getUserId()", user1.getUserId());
        Message message2 = receivedMessage.get(0);

               assertNotNull(message2);
    }
    @Test
    void twoChatWithoutReply(){
        String firstMessageToSend = "Special note from me to you. \nBelieve in yourself\nlife is all we have";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(firstMessageToSend);

        userService.chat(chatRequest1);

        List <Message> receivedMessage1 =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message message2 = receivedMessage1.get(0);

        String messageToAssert1 = "Message from " + message2.getSenderName() + " " +
                message2.getSenderId() +"\n" + firstMessageToSend +
                "\n" + message2.getTime() + "\n";

        assertEquals(messageToAssert1, String.valueOf(message2));

        String secondMessageToSend = "Special note from me to you. \nYou go pro";

        chatRequest2.setSenderId(user1.getUserId());
        chatRequest2.setReceivingId(user2.getUserId());
        chatRequest2.setRawMessage(secondMessageToSend);


        userService.chat(chatRequest2);

        List <Message> secondReceivedMessage =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message secondMessage = secondReceivedMessage.get(1);

        String secondMessageToAssert = "Message from " + secondMessage.getSenderName() + " " +
                secondMessage.getSenderId() +"\n" + secondMessageToSend +
                "\n" + secondMessage.getTime() + "\n";

        assertEquals(2, secondReceivedMessage.size());
        assertEquals(secondMessageToAssert, String.valueOf(secondMessage));

    }
    @Test
    void otherUserCanNotViewPrivateChat(){
        String message = "Special note from me to you. \nBelieve in yourself";
        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(message);

        userService.chat(chatRequest1);

        List <Message> receivedMessage1 =userService.viewChat(user2.getUserId(), user1.getUserId());
        List <Message> receivedMessage2 =userService.viewChat(user2.getUserId(), user3.getUserId());
        List <Message> receivedMessage3 =userService.viewChat(user1.getUserId(), user1.getUserId());
        List <Message> receivedMessage4 =userService.viewChat(user2.getUserId(), user2.getUserId());
        List <Message> receivedMessage5 =userService.viewChat(user3.getUserId(), user2.getUserId());
        List <Message> receivedMessage6 =userService.viewChat(user3.getUserId(), user1.getUserId());
        List <Message> receivedMessage7 =userService.viewChat(user3.getUserId(), user3.getUserId());

        Message message1 = receivedMessage1.get(0);
        Message message2 = receivedMessage2.get(0);
        Message message3 = receivedMessage3.get(0);
        Message message4 = receivedMessage4.get(0);
        Message message5 = receivedMessage5.get(0);
        Message message6 = receivedMessage6.get(0);
        Message message7 = receivedMessage7.get(0);

        String messageToAssert = "Message from " + message1.getSenderName() + " " + message1.getSenderId()
                +"\n" + message1.getMessage() +
                "\n" + message1.getTime() + "\n";

        assertEquals(messageToAssert, String.valueOf(message1));
        assertNotEquals(messageToAssert, String.valueOf(message2));
        assertNotEquals(messageToAssert, String.valueOf(message3));
        assertNotEquals(messageToAssert, String.valueOf(message4));
        assertNotEquals(messageToAssert, String.valueOf(message5));
        assertNotEquals(messageToAssert, String.valueOf(message6));
        assertNotEquals(messageToAssert, String.valueOf(message7));
    }
    @Test
    void UserCanReplyEachOther(){
        String firstMessageToSend = "Bother for  nothing, the world is yours." +
                "\nBelieve in yourself\nlife is all we have";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(firstMessageToSend);

        userService.chat(chatRequest1);

        List <Message> receivedMessage1 =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message message2 = receivedMessage1.get(0);

        String messageToAssert1 = "Message from " + message2.getSenderName() + " " +
                message2.getSenderId() +"\n" + firstMessageToSend +
                "\n" + message2.getTime() + "\n";

        assertEquals(messageToAssert1, String.valueOf(message2));

        String secondMessageToSend = "Special note from me to you. \nYou go pro";

        chatRequest2.setSenderId(user2.getUserId());
        chatRequest2.setReceivingId(user1.getUserId());
        chatRequest2.setRawMessage(secondMessageToSend);

        userService.chat(chatRequest2);

        List <Message> secondReceivedMessage =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message secondMessage = secondReceivedMessage.get(1);

        String secondMessageToAssert = "Message from " + secondMessage.getSenderName() + " " +
                secondMessage.getSenderId() +"\n" + secondMessageToSend +
                "\n" + secondMessage.getTime() + "\n";

        assertEquals(2, secondReceivedMessage.size());
        assertEquals(secondMessageToAssert, String.valueOf(secondMessage));

        String thirdMessageToSend = "This is the third chat, the world is yours." +
                "\nBelieve in yourself\nlife is all we have";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(thirdMessageToSend);

        userService.chat(chatRequest1);
        List <Message> thirdReceivedMessage =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message thirdMessage = thirdReceivedMessage.get(2);

        String thirdMessageToAssert = "Message from " + thirdMessage.getSenderName() + " " +
                thirdMessage.getSenderId() +"\n" + thirdMessageToSend +
                "\n" + thirdMessage.getTime() + "\n";


        assertEquals(3, thirdReceivedMessage.size());
        assertEquals(thirdMessageToAssert, String.valueOf(thirdMessage));


        String forthMessageToSend = "This is the third chat, the world is yours." +
                "\nBelieve in yourself\nlife is all we have";

        chatRequest1.setSenderId(user2.getUserId());
        chatRequest1.setReceivingId(user1.getUserId());
        chatRequest1.setRawMessage(thirdMessageToSend);

        userService.chat(chatRequest1);
        List <Message> forthReceivedMessage =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message forthMessage = forthReceivedMessage.get(3);

        String forthMessageToAssert = "Message from " + forthMessage.getSenderName() + " " +
                forthMessage.getSenderId() +"\n" + forthMessageToSend +
                "\n" + forthMessage.getTime() + "\n";


        assertEquals(4, forthReceivedMessage.size());
        assertEquals(forthMessageToAssert, String.valueOf(forthMessage));
    }
    @Test
    void testThatBothUsersCanViewTheSameMessage(){
        String firstMessageToSend = "Bother for  nothing, the world is yours." +
                "\nBelieve in yourself\nlife is all we have";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(firstMessageToSend);

        userService.chat(chatRequest1);

        List <Message> receivedMessage1 =userService.viewChat(user2.getUserId(), user1.getUserId());
        List <Message> receivedMessage2 =userService.viewChat(user1.getUserId(), user2.getUserId());
        Message message2 = receivedMessage1.get(0);
        Message message3 = receivedMessage2.get(0);

        String messageToAssert1 = "Message from " + message2.getSenderName() + " " +
                message2.getSenderId() +"\n" + firstMessageToSend +
                "\n" + message2.getTime() + "\n";

        assertEquals(messageToAssert1, String.valueOf(message2));
        assertEquals(messageToAssert1, String.valueOf(message3));

    }

}
