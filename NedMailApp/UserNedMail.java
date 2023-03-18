package EmailingApp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

public class UserNedMail {

    private  boolean isNotNullPassword;
    private String firstName;
    private String lastName;
    private String password;
    private String emailAddress;
    private int invalidAttempt;
    private int mailNotifier;


    private List<EmailMessage> savedMails = new ArrayList<>();
    private List<EmailMessage> receivedMails = new ArrayList<>();
    private List <String> receivedMailHeaders = new ArrayList<>();

    private List<EmailMessage> sentMails = new ArrayList<>();
    private OptionPrompt e = new OptionPrompt();
    private boolean isNewMail;

    public UserNedMail(String firstName, String lastName, String preferredEmailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (isValidPassword(password)){
            this.password = password;
            isNotNullPassword = true;
        }else {
            isNotNullPassword = false;
        }
//        setEmailAddress(
        this.emailAddress = preferredEmailAddress + "@nedmail.com";
    }

    public void setEmailAddress(String emailAddressToSet) {
        this.emailAddress = emailAddressToSet + "@nedmail.com";
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setReceivedMailsNotification(){
        mailNotifier = receivedMails.size();
        isNewMail = false;
    }
    public void confirmNewMessage(){
        if(mailNotifier < receivedMails.size()){
            isNewMail = true; 
        }
    }
    public boolean isNotNullPassword() {
        return isNotNullPassword;
    }

    public EmailMessage writeMessage(String emailHeader, String content) {
        EmailMessage message = new EmailMessage(emailHeader, content);

        return message;
    }
    public boolean isValidPassword(String password){
        int number =Integer.MIN_VALUE;
        int count = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <password.length(); i++){
            String string = builder.append(password.charAt(i)).toString();
            try {
                number = Integer.parseInt(string);
            }catch (InputMismatchException e){
            }catch (NumberFormatException n){
            }
            builder.deleteCharAt(0);

            if (number > Integer.MIN_VALUE && number/10 == 0){
                count ++;
                number = Integer.MIN_VALUE;
            }

        }
        if (password.length() >= 8 && count >=2) return true;
        e.print("Weak Password");
        return false;

    }
    public String getPassword(){
        return password;
    }
    public void changePassWord(String oldPassword, String newPassword){
        if (this.password.equals( oldPassword)){
            e.print("old password is correct");
            if (isValidPassword(newPassword)) {
                this.password = newPassword;
                e.print("password change successful");
                invalidAttempt = 0;
            }
        }
        else {
            e.print("Invalid attempt");
            invalidAttempt++;
            if (invalidAttempt == 3){
                e.print("you are a criminal");
            }
        }
    }

    public void setReceivedMails(EmailMessage message) {
        receivedMails.add(message);
    }
    public List<EmailMessage> getSavedMails() {
        return savedMails;
    }

    public List<EmailMessage> getSentMails() {
        return sentMails;
    }

    public void setSentMails(EmailMessage sentMail ) {
        this.sentMails.add( sentMail);
    }

    public void setSavedMails(EmailMessage savedMail) {
        this.savedMails.add( savedMail);
    }
    public String
    getFullName() {
        return firstName + " " + lastName;
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

    public List<String> getReceivedMailHeaders() {
        return receivedMailHeaders;
    }

    public void setReceivedMailHeaders(String receivedMailHeader) {
        this.receivedMailHeaders.add(receivedMailHeader);
    }

    public boolean login(String passwordToVerify) {

        if (Objects.equals(this.password, passwordToVerify)) {
            e.print("Password is correct\nYou are logged in");
            return true;
        }
        else e.print("Password is incorrect! ");
        return false;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public List<String> viewAllMailHeader() {
        return receivedMailHeaders;
    }
    public void viewHeaderOneByOne(){
        for (String message : receivedMailHeaders) e.print(message + "\n");
    }
    public  EmailMessage viewSpecificMessage(int index){
        return receivedMails.get(index);
    }


}
