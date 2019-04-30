package sample.database;

import org.junit.Assert;


public class ConnectionTest {

    private Connection database = new Connection();
    private boolean expectedResult, actualResult;

    @org.junit.Test
    public void login() {
        expectedResult = true;

        database.connect();
        actualResult = database.login("ahmadabdulal@outlook.com","123132");
        database.connect();

        Assert.assertEquals(expectedResult, actualResult);
    }
}