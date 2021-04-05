package settings;

import driver.DriverType;

import java.util.HashMap;
import java.util.Map;

public class AutomationSettings {

    /** Change to use desired properties **/
    public static void config() {
        AutomationSettings.Chrome();
        setURL("http://google.ca");
    }

    /** Browser types **/
    private static void Chrome() {
    setBrowser(DriverType.CHROME.name());
    }

    private static void FireFox() { setBrowser(DriverType.FIREFOX.name()); }

    /** Useful functions **/
    private static void setBrowser(String value) {
        setSystemProperty("browser", value);
    }

    private static void setURL(String value) { setSystemProperty("url", value); }

    private static void setSystemProperty(String key, String value) {
        System.setProperty(key, value);
    }

    /** Add newly added properties here **/
    public static Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put("browser", System.getProperty("browser"));
        properties.put("url", System.getProperty("url"));
        return properties;
    }
}
