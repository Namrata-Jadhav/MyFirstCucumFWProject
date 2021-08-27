package utils;

import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {
  protected String url;
    protected String username;
    protected String password;

    public BaseTest()  {

        readProperties();
    }

    public void readProperties()  {
        try{
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            this.url = (String) properties.get("url");
            this.username = (String) properties.get("user_Name");
            this.password = (String) properties.get("pass");

        }catch(Exception e){
            e.printStackTrace();
            Assert.fail("config.properties file not found");
        }

    }
}
