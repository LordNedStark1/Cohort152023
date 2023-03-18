package EmailingApp;

import javax.swing.*;
import java.util.List;

public class OptionPrompt {

    public void print(List prompt){
        JOptionPane.showMessageDialog(null, prompt);
    }
    public void print(int prompt){
        JOptionPane.showMessageDialog(null, prompt);
    }
    public void print(EmailMessage prompt){
        JOptionPane.showMessageDialog(null, prompt);
    }
    public void  print(int num, String prompt){
        JOptionPane.showMessageDialog(null, prompt);

    }
    public void print(StringBuilder builder) {
        JOptionPane.showMessageDialog(null, builder);
    }

    public void print(String prompt){
         JOptionPane.showMessageDialog(null, prompt);
    }

    public String print( String prompt, int num){
        return  JOptionPane.showInputDialog(null, prompt);
    }
    public String print( int num,List prompt){
        return JOptionPane.showInputDialog(null, prompt);
    }


    public String input(String prompt){
        return JOptionPane.showInputDialog(prompt);
    }
    public int input (String prompt, String type){
        return Integer.parseInt(JOptionPane.showInputDialog(prompt));
    }


    public String  input(List showList, String prompt) {
        return JOptionPane.showInputDialog(showList, prompt);
    }


}
