
package example2;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jlombardo
 */
public class PersonTest {

    public PersonTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setLastName method, of class Person.
     * Requirement: cannot be null
     */
    @Test(expected = IllegalArgumentException.class)
    public void lastNameShouldNotBeNull() {
        String lastName = null;
        Person instance = new Person();
        instance.setLastName(lastName);
    }

    /**
     * Test of setLastName method, of class Person.
     * Requirement: cannot be empty String
     */
    @Test(expected = IllegalArgumentException.class)
    public void lastNameShouldNotBeEmpty() {
        String lastName = "";
        Person instance = new Person();
        instance.setLastName(lastName);
    }

    /**
     * Test of setLastName method, of class Person.
     * Requirement: cannot begin with a number
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void lastNameShouldNotBeginWithNumber() {
        String[] lastNames = {"7Smith", "Smith"};
        Person instance = new Person();
        for(String name : lastNames) {
            instance.setLastName(name);
        }
    }

    /**
     * Test of betLastName method, of class Person.
     * Requirement: cannot begin or end with spaces
     */
    @Test
    public void lastNameShouldNotBeginOrEndWithSpaces() {
        String[] lastNames = {" Smith", "  Smith", "Smith ", " Smith "};
        Person instance = new Person();
        final String SPACE = " ";

        for(String name : lastNames) {
            instance.setLastName(name);
            if(instance.getLastName().startsWith(SPACE)
               || instance.getLastName().endsWith(SPACE)) {
                fail("lastName cannot begin or end with spaces");
            }
        }
    }

}