package example2;

/**
 *
 * @author jlombardo
 */
public class Person {
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    // Needs validation and testing because parameter could be invalid
    public void setLastName(String lastName) {
        if(lastName == null || lastName.length() == 0) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
        this.lastName = lastName.trim();
        if(Character.isDigit(this.lastName.charAt(0))) {
            throw new IllegalArgumentException("lastName cannot begin with a number");
        }
    }
    
    // Does not need testing because nothing can go wrong!
    @Override
    public String toString() {
        return lastName;
    }

}
