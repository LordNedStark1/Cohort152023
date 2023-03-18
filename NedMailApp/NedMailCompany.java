package EmailingApp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class NedMailCompany implements EmailInterface {
    private static List<UserNedMail> multipleMails = new ArrayList<>();
//    NedMailCompany c = new NedMailCompany();
     static OptionPrompt e = new OptionPrompt();
    UserNedMail user;
    private boolean logStatus;
    public boolean isLogStatus() {
        return logStatus;
    }
//    @Override
    public static void signUp(String firstName, String lastName, String preferredEmailName, String passWord) {
        boolean isNewEmail = false;
//        for(UserNedMail email : emails){
//            if (Objects.equals(email.getEmailAddress(), preferredEmailName)){
//                e.print("email already exists.");
//                break;
//            }
            isNewEmail = true;
//        }
        if (isNewEmail){
            UserNedMail newMail = new UserNedMail(firstName, lastName, preferredEmailName, passWord);
            if (newMail.isNotNullPassword()) {
                multipleMails.add(newMail);
                e.print("signUp successful");
            }else {
                e.print("""
                        Password too weak
                        Password should be
                        8 characters long or more and have at least 2 numbers\s
                        """);
            }

        }
    }
    @Override
    public void login(String emailAddress, String password) {
        boolean isValidEmail = false;
        for(UserNedMail email : multipleMails) {

            if (Objects.equals(email.getEmailAddress(), emailAddress)) {
                isValidEmail = true;
                e.print("email is correct");
                if (email.login(password)) {
                     user = email;
                     logStatus = true;

                }
            }

        }
        if (!isValidEmail) {
            e.print("UserNedMail does not exist");
        }
    }
    public void logOut(){
        user = null;
        logStatus = false;
        e.print("You are logged out");

    }

    @Override
    public void sendEmail(UserNedMail senderEmail, String receiverEmailAddress, String emailHeader, String emailContent) {
        boolean isFoundEmail = false;
        for (UserNedMail email : multipleMails) {
            if (Objects.equals(email.getEmailAddress(), receiverEmailAddress)) {
                LocalDate date = LocalDate.now();
                LocalTime time = LocalTime.now();
                EmailMessage message = senderEmail.writeMessage(emailHeader, emailContent);
                message.setTimeSent(time);
                message.setDateSent(date);
                message.setOriginalSender(senderEmail.getFullName());
                message.setId();
                message.getId();
                message.setContentSlice(emailContent);
                message.setSender(senderEmail.getFullName());
                message.setSenderAddress(senderEmail.getEmailAddress());
                email.setReceivedMailHeaders(message.vitalInformationDisplay());
                email.setReceivedMails(message);
                e.print("Message sent");
                isFoundEmail = false;
                break;
            }
            else isFoundEmail = true;

        }
        if (isFoundEmail){
            e.print("Invalid Email");
        }
    }
    public void forward(){

    }
    public void menu() {
        e.print("Welcome " + user.getFullName());
        int option = Integer.parseInt(e.input("""
                1 --> write message              
                2 --> view messages
                3 --> settings
                """));
        switch (option) {
            case 1 -> {
                String emailHeader = e.input("Enter the email header \n");
                String content = e.input("Enter the content of the email");
                sendOrSaveEmail(emailHeader, content);
                backToMenu();
            }
            case 2 -> {

                viewMessage();
            }
            case 3 -> settings();
            default -> {
                e.print("Invalid input");
                backToMenu();
            }
        }
    }

    private void settings() {
        int logOption = Integer.parseInt(e.input("""
                Welcome to settings
                1 --> to change first name
                2 --> to change last name
                3 --> to change password
                4 --> to delete messages
                5 --> to log out
                """));
        switch (logOption) {
            case 1 -> {
                user.setFirstName(e.input("Enter your first name"));
                backToMenu();
            }
            case 2 -> {
                user.setLastName(e.input("Enter your last name"));
                backToMenu();
            }
            case 3 -> {
                String oldPassword = e.input("Enter the old password");
                String newPassword = e.input("Enter new password");
                user.changePassWord(oldPassword, newPassword);
                backToMenu();
            }
            case 4 -> e.print("nothing here yet");
            case 5 -> logOut();
            default -> {
                e.print("Invalid input");
                settings();
            }
        }
    }

    private void viewMessage() {
        int viewOption = Integer.parseInt(e.input("""
                                        1 --> to view received messages
                                        2 --> to view sent messages 
                                        3 --> to view draft
                                        """));
        switch (viewOption) {
            case 1 -> {
                List<String> receivedMails = user.viewAllMailHeader();
                if (receivedMails.size() > 0) {
                    manipulatingReceivedMails(receivedMails);

                }
                backToMenu();
            }
            case 2 -> {
                e.print(user.getSentMails());
                backToMenu();
            }
            case 3 -> {
                e.print(user.getSavedMails());
                backToMenu();
            }
            default -> {
                e.print("Invalid input");
                viewMessage();
            }
        }
    }

    private void manipulatingReceivedMails(List<String> receivedMails) {
        for (int i = 0; i < receivedMails.size() ; i++) {
            e.print(receivedMails.get(i));
            int innerChoice = Integer.parseInt(e.input("""
                            1 --> to view this message
                            2 --> to go back
                            3 --> to delete this message                                      
                            """));
            switch (innerChoice){
                case 1 -> {
                    e.print(user.viewSpecificMessage(i));
                    String yesOrNo = e.input("Do you want to reply");

                    if (yesOrNo.equals("yes")) {
                        String receiver = user.viewSpecificMessage(i).getSenderAddress();
                        String emailHeader = e.input("Enter the email header \n");
                        String content = e.input("Enter the content of the email");
                        sendEmail(user, receiver, emailHeader, content);
                    }
                }
                case 2 -> {
                    if ( i-1 != 0) i -= 1;
                }
                case 3 ->
                    e.print("delete");
            }
        }
    }

    private void backToMenu(){
        menu();
    }
    private void sendOrSaveEmail(String emailHeader, String content) {
        String sendOption =  e.input("Do want to send this mail now\n Yes / no");
        switch (sendOption) {
            case "yes" -> {
                String receiverMail = e.input("Enter the nedmail account\n example@nedmail.com");
                sendEmail(user, receiverMail, emailHeader, content);
                user.setSentMails(user.writeMessage(emailHeader, content));
            }
            case "no" -> {
                user.setSavedMails(user.writeMessage(emailHeader, content));
                e.print("Nothing here yet");
            }
        }
    }
}
