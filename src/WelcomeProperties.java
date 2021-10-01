import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

    public void inputPropertiesFile(String filename){
        setEnableSearchByEmail(getPropertiesFromPropertiesFile(filename, "enableSearchByEmail"));
        setEnableCustomUserStyles(getPropertiesFromPropertiesFile(filename, "enableCustomUserStyles"));
    }

    public Boolean getPropertiesFromPropertiesFile(String filename, String property) {

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

    public boolean setStringToBoolean(String s){
        if(s == null){
            return false;
        }
        return s.equals("true") || s.equals("1");
    }
}
