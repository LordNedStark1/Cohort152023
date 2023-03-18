package EmailingApp;

public class NedMailHomePage {
    OptionPrompt e = new OptionPrompt();
    NedMailCompany nedMail = new NedMailCompany();

    public void homePage() throws NumberFormatException{
        int option = Integer.parseInt(e.input("""
                Hey there!!
                Welcome To NedMailCompany!
                Are you new here
                ---> 1 to signUp
                
                Oh! you already have an account with us, that's cool
                ---> 2 to login
                
                ---> 3 to exit
                """));
        switch (option){
            case 1:
                String firstName = e.input("Enter your first name");
                String lastName = e.input("Enter your last name");
                String preferredEmailAddress = e.input("Enter the name you prefer as your official nedmail");
                String signUpPassword = e.input("Enter your secured signup password");
                nedMail.signUp(firstName, lastName, preferredEmailAddress, signUpPassword);
                homePage();
                break;
            case 2:
                String mailAccount = e.input("Please enter your nedmail account\nexample@nedmail.com ");
                String loginPassword = e.input("Enter your secured login password xxxxxxxx\nExample12");
                nedMail.login(mailAccount, loginPassword);
                if (nedMail.isLogStatus()) nedMail.menu();
                homePage();
                break;
            case 3:
                System.exit(20);
            default:
                e.print("Invalid input");
                homePage();
        }

    }


}
