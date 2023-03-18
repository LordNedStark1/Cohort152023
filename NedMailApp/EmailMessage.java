package EmailingApp;

import java.time.LocalDate;
import java.time.LocalTime;

public class EmailMessage {
    OptionPrompt e = new OptionPrompt();
    private LocalDate dateCreated = LocalDate.now();
    private String header;
    private String content;
    private int id;
    private String contentSlice;
    private LocalDate localDateSent = LocalDate.now();
    private LocalDate dateSent;
    private LocalTime timeSent;
    private LocalTime timeCreated = LocalTime.now();
    private  String sender;
    private LocalDate today;


    private String senderAddress;
    private String originalSender;

    public EmailMessage(String header, String content){
        this.header = header;
        this.content = content;
    }
    public void setOriginalSender(String sender){
        this.originalSender = sender;
    }
    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }
    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public int getId() {
        return this.id;
    }

    public void setId() {
        this.id += 1 ;
    }


    public void setContentSlice(String contentTOSlice) {
        StringBuilder contentSliced = new StringBuilder();
        if (contentTOSlice.length() < 12) contentSliced = new StringBuilder(contentTOSlice);
        else {
            for (int i = 0; i < 12; i++) {

                contentSliced.append(contentTOSlice.charAt(i));

            }
        }
        contentSliced.append("...");
        this.contentSlice = contentSliced.toString();
    }

    public LocalTime getTimeSent() {

        return timeSent;
    }

    public void setTimeSent(LocalTime timeSent) {
        this.timeSent = timeSent;
    }

    public void setDateCreated(LocalDate date){
        this.dateCreated = date;

    }
    public  void setDateSent(LocalDate date){
        this.dateSent = date;
    }
    public void  setSender(String sender){
        this.sender = sender;
    }
    public String getSender(){
        return sender;
    }

    public String vitalInformationDisplay(){
//        if (dateSent == today)
        return id + " " + sender +"\n  " + header +"\n "+ contentSlice +"  "+ timeSent;
    }

    @Override
    public String toString() {
        if (dateSent == null ) {
            return  "\nHeader '" + header + '\'' +
                            "\nMessage was written " + dateCreated.getDayOfWeek() +
                            " " + dateCreated.getDayOfMonth() +
                            "\nThe time"+  timeCreated+
                            "\nBody '\n" + content + '\'' ;
        }

        else {
            return  "\nHeader '" + header + '\'' +
                    ", from '" + sender + '\'' +
                    "\nBody '\n\n" + content + '\'' +
                    "\n" + dateSent + '\'' +
                    "\n"+ timeSent+ '\'' +
                    "\norigin"+ originalSender ;

        }


    }
}
