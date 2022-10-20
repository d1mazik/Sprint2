import java.time.LocalDate;

public class Person {

  protected String id;
  protected String name;
  protected LocalDate date;

  public Person (String id, String name, LocalDate date){
    this.id = id;
    this.name = name;
    this.date = date;
  }

    @Override
    public String toString() {
        if(date.isBefore(LocalDate.now().minusYears(1))){
          return "Id: "  + id +
                  ". Name: " + name +
                  ". Membership: Outdated";
        }
        //om personen betalade minst 1 år sen
          return "Id: "  + id +
                 ". Name: " + name +
                 ". Membership: Active";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }
}
