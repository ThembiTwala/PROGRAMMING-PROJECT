/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Student
 */
public class POEPart1 {

    /**
     * @param args the command line arguments
     */
    // Global variables 
    int MessageNumber;
    String Message;
    String RecipientCell;
    String Recipient;
    String MessageID;
    String username;
   String password;
     String cellphonenumber;
    Scanner userinput=new Scanner(System.in);
    String storedursername;
    String storedpassword;
    // creating a method to store messages called storeMessage
    boolean storeMessage(int actionChoice){
        try( java.io.FileWriter writer = new java.io.FileWriter("StoredMessages.txt", true)){
            writer.write(PrintMessages()+ "\nSTATUS: "+ SentMessage(actionChoice)+"\n\n");
            return true;
        }
        catch( java.io.IOException e){
            return false;
        }
    }
    //creating a method to return number of methods send 
    int returnTotalMessages(){
        System.out.println("How many messages do you want to send?");
        int tatoalallowedMessage= userinput.nextInt();
        int messageCounter=0;
        return MessageNumber;
    }
    
    // creating a method to display messages details called displayMessageDetails
    // \n=then
    String PrintMessages(){
        return "MESSAGE ID:" + MessageID + "\n" +
                "RECIPIENT CELL: " +Recipient+ "\n" +
                "MESSAGE TEXT: " +Message+ "\n" +
                "mESSAGE HASH: " +createMessageHash();
        
        }
    //Creating a return method called SentMessage
    String SentMessage(int MenuChoice){
        if(MenuChoice==1){
            return "Message Sent";
        }
        else if(MenuChoice==2){
            return "Message Stored";
        }
        else if (MenuChoice==3){
            return "Message Disregarded";
        }
        else { 
            return "Inavlid choice";
        }
    }
    //creating a methond called createMessageHash
    // [] to used for  arrays 
    //this.Message.split(" ")[it reminds the systems to separete the message and pack it to the array]
    String createMessageHash(){
        if(MessageID==null ||Message==null)
            return "NO MESSAGES AVAILABLE";
        String FirstTwoID=MessageID.substring(0,2);
        String[] Words=Message.split(" ");
        String FirstWord=Words[0];
        String LastWord=Words[Words.length -1];
        String MessageHash=FirstWord+ ":" +MessageNumber + ":"+ FirstWord +LastWord;
        return MessageHash.toUpperCase();
    }
    //creating a return methond called checkRecipientCell
    String checkRecipientCell(){
        if(Recipient.length()<=13&& Recipient.startsWith("+27")){
            return "valid Recipient";
    }
        return "Invalid Recipient";
    }
    //creating a return methond called checkMessageID
    boolean checkMessageID(){
        if(MessageID.length()<=10)
        {
            return true;
        }
        return false;
    }
    // creating a return method called returnloginstatus
    String returnloginstatus(boolean loginuser){
        if (loginuser ==true){
            System.out.println("succeful login");
        return "succeful login.";
    }
        else {
            System.out.println(" Failed login");
           return " Failed login";
        }
        
    }
    // creating a return called loginuser
    boolean loginuser(){
        System.out.println("  LOGIN   ");
        System.out.println(" Enter username");
         username= userinput.nextLine();
         System.out.println("Enter password");
         password=userinput.nextLine();
         if(username.equals(storedursername)&& password.equals(storedpassword)){
         return true;
         }
         else 
             return false;
    }
    
    //creatin a return method call Registeruser
     String registerUser(){ 
        System.out.println("Create yout Account");
        System.out.println("Create your username");
        username=userinput.nextLine();
         if (!checkusername(username)){ 
            return "invalid username";
                }
        System.out.println("Create your password");
        password=userinput.nextLine();
        if (!checkpassword(password)){
           return "invaild password";
        }
        System.out.println("Enter your cell phone number");
        cellphonenumber=userinput.nextLine();
        if (!checkcellphonenumber( cellphonenumber)){
           return "invaild cellnumber";
        }
        else {
            storedursername=username;
            storedpassword=password;
            System.out.println("Registed succefully");
            return "succesfully registered";
        }
        
    }
    // creating a return  methond call checkcellphonenumber
     boolean checkcellphonenumber(String cellphonenumber){
        // must have international country code
        // must have 11 digits
        String phoneRegex="^\\+[0-9]{11}$";
        if(cellphonenumber.matches(phoneRegex)){
            System.out.println("cell phone number successflly added.");
            return true;
        }
        else{
            System.out.println("Cell phone number incorrectly formatted or does not contain internaational code.");
            return false;
            
        }
        
    }
    // creating a return methond called checkusername
      boolean checkusername(String username){
        //The username should have atleast 5 char and an underscore(_)
        if(username.contains("_")&& username.length()<=5){
        System.out.println("Username successfully captured");
        return true;
    }
        else {
            System.out.println("Username is not correctly formatted please ensure the username has an underscore.");
           return false;
        }
       
    }   
 // creating return methond called checkpassword
     boolean checkpassword(String password){
          /*at least eight characters long [.{8,}$]
          contain capital letter (/=.*[A-Z])
          contain number(?=.*\\d)
          contain special character(?=.*[!@#$%&*?])
          */
          String regex="^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*?]).{8,}$";
          if(password.matches(regex)){
              System.out.println("Password successfully captured");
              return true;
          }
          else{
              System.out.println("Password is not correctly formatted please ensure the password contains at least eight characters,a number and a special character.");
              return false;
          }
          
          
      }
    public static void main(String[] args) {
        Scanner entermenu=new Scanner(System.in);
        // linking the 2 classes
        POEPart1 newaccount =new POEPart1 ();
        // storing the users choice
        int choice;
        // creating a menu 
        do{
         System.out.println("      MENU   ");
         System.out.println("    1.REGISTER ");
         System.out.println("   2.LOGIN ");
         System.out.println("   3.EXIT  ");
            System.out.println("ENTER MENU OPTION");
          choice=entermenu.nextInt();
            
          switch (choice){
              case 1: 
                newaccount.registerUser();
                  break;
              case 2:
                  newaccount.loginuser();
                newaccount.returnloginstatus(true);
                int messageChoice=0;
                while(messageChoice!=3){
                    System.out.println("\n1. Send Message\n2. Show Recently Send Messages\n3. Quit");
                    messageChoice=entermenu.nextInt();
                    if(messageChoice==1){
                        int messageCounter=0;
                        if (messageCounter <newaccount.returnTotalMessages()) {
                            System.out.println("Status");
                            newaccount.storeMessage(entermenu.nextInt());
                        }
                    }
                        System.out.println(newaccount.PrintMessages());
                 if(messageChoice==2){
                        System.out.println(" Coming soon");
                        newaccount.storeMessage(entermenu.nextInt());
                        }
                        }
                        
                  break;
              case 3:
                  System.out.println("GOODBYE.");
                  break;
              default:
                  System.out.println("INVALID CHOICE");
          }       
          }
        // loop will continue while choice is not 3
          while(choice!=3);{
              entermenu.close();
         }
             
 
        
    }
    
}
