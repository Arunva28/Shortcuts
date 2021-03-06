package shortcuts.nura.com.shortcuts;

/**
 * Created by Arun on 9/11/2016.
 */
public class Contact {

    private String Number;
    private String Name;

    public Contact() {}

    public Contact(String Name, String Number) {
        super();
        this.Number = Number;
        this.Name = Name;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return Number;
    }

    @Override
    public String toString() {
        return "Contact [Name=" + Name + ", Number=" + Number+ "]";
    }

}
