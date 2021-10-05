import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**Class containing methods for retrieving the properties file and defining attributes through this file*/
public class WelcomeProperties {
    private boolean enableSearchByEmail;
    private boolean enableCustomUserStyles;

    public boolean getEnableSearchByEmail(){return enableSearchByEmail;}
    public boolean getEnableCustomUserStyles(){return enableCustomUserStyles;}

    public void setEnableSearchByEmail(boolean enableSearchByEmail) {
        this.enableSearchByEmail = enableSearchByEmail;
    }
    public void setEnableCustomUserStyles(boolean enableCustomUserStyles) {
        this.enableCustomUserStyles = enableCustomUserStyles;
    }

    /** Method for implement a properties file
     * @param filename - name of the properties file */
    public void inputPropertiesFile(String filename){
        setEnableSearchByEmail(getBooleanPropertiesFromPropertiesFile(filename, "enableSearchByEmail"));
        setEnableCustomUserStyles(getBooleanPropertiesFromPropertiesFile(filename, "enableCustomUserStyles"));
    }

    /** Method for get a boolean property from a file
     * @param filename - name of the file where the property is located
     * @param property - property that will be redeemed from the file
     * @return Boolean - returns true or false according to the requested property*/
    public Boolean getBooleanPropertiesFromPropertiesFile(String filename, String property) {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return false;
            }

            prop.load(input);

            String stringProperty = (String) prop.get(property);
            boolean booleanProperty = setStringToBoolean(stringProperty);

            if(stringProperty == null){
                System.out.println("Sorry, unable to find the property" + property + " in " + filename);
            }
            return booleanProperty;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /** Method for set a string to boolean type
     * @param s - string that will be boolean
     * @return Boolean - returns true or false according to the string*/
    public boolean setStringToBoolean(String s){
        if(s == null){
            return false;
        }
        return s.equals("true") || s.equals("1");
    }
}
