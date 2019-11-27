package ua.com.epam;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import ua.com.epam.core.rest.RestClient;
import ua.com.epam.service.CleanUpService;
import ua.com.epam.utils.DataFactory;
import ua.com.epam.validation.Validator;

public class BaseTest {

    protected static Logger log = Logger.getLogger(BaseTest.class);
    protected Validator validator = new Validator();

    protected RestClient client = new RestClient();
    protected DataFactory testData = new DataFactory();
    protected CleanUpService clean = new CleanUpService(client);

    //don't delete this!!!
    @BeforeMethod
    public void reinitialize() {
        client = new RestClient();
        testData = new DataFactory();
        clean = new CleanUpService(client);
    }
}
