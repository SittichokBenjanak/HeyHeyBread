package drucc.sittichok.heyheybread;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class showMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu);

        //ListView Controller
        ListViewController();

    }   //  onCreate

    public void clickConfirmOrder(View view) {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM " + ManageTABLE.TABLE_ORDER,null);

        if (objCursor.getCount() > 0) {

            //Have Data
            Intent objIntent = new Intent(showMenuActivity.this, ConfirmOrderActivity.class);
            startActivity(objIntent);

        } else {

            //No Data
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(showMenuActivity.this,"กรุณา Order","กรุณาสั่งอาหารก่อนครับ");
        }

    }   // clickConfirmOrder

    private void ListViewController() {

        // Setup Value Array
        ManageTABLE objManageTABLE = new ManageTABLE(this);

        final String[] breadStrings = objManageTABLE.readAllBread(1);
        final String[] priceStrings = objManageTABLE.readAllBread(2);
        String[] stockStrings = objManageTABLE.readAllBread(3);
        String[] iconStrings = objManageTABLE.readAllBread(4);

        ListView menuListView = (ListView) findViewById(R.id.listView);
        MenuAdapter objMenuAdapter = new MenuAdapter(showMenuActivity.this,
                stockStrings, priceStrings, breadStrings, iconStrings);
        menuListView.setAdapter(objMenuAdapter);

        // Active When Click ListView
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChooseItem(breadStrings[i], priceStrings[i]);
            }   // event
        });


    }   //  ListViewController

    private void ChooseItem(final String breadString, final String priceString) {

        CharSequence[] mySequences = {"1 ชิ้น", "2 ชิ้น", "3 ชิ้น", "4 ชิ้น", "5 ชิ้น",
                "6 ชิ้น", "7 ชิ้น", "8 ชิ้น", "9 ชิ้น", "10 ชิ้น",};
        //final int intItem = 0;

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle(breadString);
        objBuilder.setSingleChoiceItems(mySequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int intItem = i +1;
                UpdateOrderToSQLit(breadString, priceString, intItem);

                dialogInterface.dismiss();
            }   // event
        });

        objBuilder.show();


    }   // ChooseItem

    private void UpdateOrderToSQLit(String breadString, String priceString, int intItem) {

        String strID = getIntent().getStringExtra("ID");
        int intID = Integer.parseInt(strID);  //  parseInt(strID) ถ้าโยน อักษร 5 มา จะเป็น เลข 5
        ManageTABLE objManageTABLE = new ManageTABLE(this);
        String[] resultStrings = objManageTABLE.readAtPosition(intID -1);

        addValueToSQLite(resultStrings[1],
                resultStrings[2],
                resultStrings[3],
                resultStrings[4],
                breadString,
                priceString,
                Integer.toString(intItem));


    }   //UpdateOrderToSQLit

    private void addValueToSQLite(String strName, String strSurname,
                                  String strAddress, String strPhone,
                                  String strbread, String strPrice, String strItem) {

        Log.d("hey", "Name " +strName);
        Log.d("hey", "Surname " +strSurname);
        Log.d("hey", "Address " +strAddress);
        Log.d("hey", "Phone " +strPhone);
        Log.d("hey", "Bread " +strbread);
        Log.d("hey", "Price " +strPrice);
        Log.d("hey", "Item " +strItem);

        //update to SQLite
        DateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date clickDate = new Date();
        String strDate = myDateFormat.format(clickDate);

        try {
            ManageTABLE objManageTABLE = new ManageTABLE(this);
            String[] myResultStrings = objManageTABLE.SearchBread(strbread);
            int oldItem = Integer.parseInt(myResultStrings[2]); //โยนไอเทมมา
            int newItem = Integer.parseInt(strItem) + oldItem;
            String strNewItem = Integer.toString(newItem);

            SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                    MODE_PRIVATE,null);
            objSqLiteDatabase.delete(ManageTABLE.TABLE_ORDER,
                    ManageTABLE.COLUMN_id + "=" + Integer.parseInt(myResultStrings[0]),null );

            addOrderToMySQLite(strName,strDate,strSurname,strAddress,strPhone,strbread,strPrice, strNewItem);


        } catch (Exception e) {
            addOrderToMySQLite(strName,strDate,strSurname,strAddress,
                    strPhone,strbread,strPrice,strItem);
        }

    }   // addValueToSQLite

    private void addOrderToMySQLite(String strName,
                                    String strDate,
                                    String strSurname,
                                    String strAddress,
                                    String strPhone,
                                    String strbread,
                                    String strPrice,
                                    String strItem) {
        ManageTABLE objManageTABLE = new ManageTABLE(this);
        objManageTABLE.addNewOrder(strName, strDate, strSurname,
                strAddress, strPhone, strbread, strPrice, strItem);

        Toast.makeText(showMenuActivity.this, "เพิ่มสินค้าสำเร็จ",Toast.LENGTH_SHORT ).show();
    }


}   // Main Class
