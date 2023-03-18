package EmailingApp;

public interface EmailInterface {
//    public void signUp(String firstName, String lastName,String preferredEmailName, String passWord);
    public void login(String emailAddress, String password);

     void sendEmail(UserNedMail senderEmail, String receiverEmailAddress, String emailHeader, String emailContent);
}
