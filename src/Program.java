import javax.swing.*;
import java.time.LocalDate;

public class Program {

    Receptionist receptionist;


    public Program() {
     receptionist = new Receptionist();
     receptionist.readFromFile();
    }

    // input dialog för att söka efter person
    public void searchDialog(){

        String input = JOptionPane.showInputDialog("Search by name or membership number. For exit, write EXIT.");
        if(input == null){ //Om input null
            messageDialog("Must enter name or ID.");
        }
        else if(input.equalsIgnoreCase("Exit")){ // skriva exit för att stänga programmet
            System.exit(0);

        }else{ //kallar på message metoden
            try{ //Försöker hitta person. Om personen inte finns, hantera exception.
                Person person = receptionist.searchPerson(input);
                if(person.getDate().isAfter(LocalDate.now().minusYears(1))){
                    receptionist.writeToFile(person);
                }
                messageDialog(person.toString());
            }catch(IllegalArgumentException e){
                messageDialog(e.getMessage()); //skriver eget felmeddelande
            }
        }
    }
    //meddelande ruta
    public void messageDialog(String message){
        JOptionPane.showMessageDialog(null, message);
        searchDialog(); //Kallar på search dialog igen
    }
}
