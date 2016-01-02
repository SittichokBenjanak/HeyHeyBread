package drucc.sittichok.heyheybread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ManageTABLE objManageTABLE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connected Database
        objManageTABLE = new ManageTABLE(this);



        //test Add Value
        testAddValue();


    }   // OnCreate

    private void testAddValue() {
        objManageTABLE.addNewUser("testUser", "testPass", "testName",
                "testSurname", "testAddress", "testPhone", "testCom");
        objManageTABLE.addNewBread("testBread", "testPrice", "testAmount", "testImage");
    }
}   // Main class
