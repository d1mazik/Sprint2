import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Receptionist {
    //lista med personer
    ArrayList<Person> personList;

    public Receptionist(){
        personList = new ArrayList<>();
    }

    //Läser in textfilen med personer
    public void readFromFile(){
        //snyggare väg till filen
        String path = "src/resources/customers.txt";
        try(FileReader fr = new FileReader(Paths.get(path).toFile());
            BufferedReader br = new BufferedReader(fr)){
            LocalDate date;
            int count = 0; //räknare som håller reda på personen
            String line; //raden som läses från textfilen
            String[] nameId = new String[2]; //array för att splitta id och namn
            while((line = br.readLine()) != null){
               if(count == 0){
                   nameId = line.split(", "); //delar på id och namn
               }else if(count == 1){
                   date = LocalDate.parse(line);
                   personList.add(new Person(nameId[0], nameId[1], date)); //skapar en person, lägger till i listan
               }
               count++;
               //räknare som håller reda på personer
               if(count == 2){
                   count = 0;
               }
            }
        }   catch(IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    //skriver ut personen till textfil med dagens datum
    public void writeToFile(Person person){
        String path = "src/resources/ActiveList.txt";
        try(FileWriter fw = new FileWriter(Paths.get(path).toFile(), true);
            BufferedWriter bw = new BufferedWriter(fw)){
            bw.write("Id: " + person.getId() +
                    "\nName: " + person.getName() +
                    "\nDate: " + LocalDate.now() +
                    "\n\n");

        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    //Söker efter person. Om inte hittas, skapa ett exception.
    public Person searchPerson(String search){
           for (Person person : personList ){
               if(search.equalsIgnoreCase(person.getName()) || search.equalsIgnoreCase(person.getId())){
                   return person;
               }
           }throw new IllegalArgumentException("Person doesn't exist."); //skapar felmeddelandet
    }

}
