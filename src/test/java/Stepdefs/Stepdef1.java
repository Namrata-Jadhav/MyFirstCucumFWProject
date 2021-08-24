package Stepdefs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Log4j2
public class Stepdef1 {

    public void newMethod(){
        log.debug("Inside new method");
    }
}
