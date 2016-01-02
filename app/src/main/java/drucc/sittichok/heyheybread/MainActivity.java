package drucc.sittichok.heyheybread;

import android.database.sqlite.SQLiteDatabase;
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
        //testAddValue();

        //Delete All SQLite
        deleteAllSQLite();

    }   // OnCreate

    private void deleteAllSQLite() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_USER, null, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_BREAD, null, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_ORDER, null, null);
    }

    private void testAddValue() {
        objManageTABLE.addNewUser("testUser", "testPass", "testName",
                "testSurname", "testAddress", "testPhone", "testCom");
        objManageTABLE.addNewBread("testBread", "testPrice", "testAmount", "testImage");
        objManageTABLE.addNewOrder("testName","testDate", "testSurname", "testAddress",
                "testPhone", "testBread", "testPrice", "testItem");
    }
}   // Main class
