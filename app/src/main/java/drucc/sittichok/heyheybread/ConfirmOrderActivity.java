package drucc.sittichok.heyheybread;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ConfirmOrderActivity extends AppCompatActivity {

    // Explicit
    private TextView dateTextView, nameTextView,addressTextView,
            phoneTextView,totalTextView;
    private String dateString,nameString,surnameString, addressString,
            phoneString,totalString;
    private ListView orderListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        // Bind Widget
        bindWidget();

        // Read All Data
        readAllData();

        //Show View
        showView();


    }   // Main Method

    private void showView() {
        dateTextView.setText("วันที่ " + dateString);
        nameTextView.setText(nameString + " " + surnameString);
        addressTextView.setText("ที่อยู่ " + addressString);
        phoneTextView.setText("เบอร์โทร " + phoneString );
    }

    private void readAllData() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);
        objCursor.moveToFirst();
        dateString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Date));
        nameString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Name));
        surnameString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Surname));
        addressString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Address));
        phoneString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Phone));



        objCursor.close();
    }   // readAllData

    private void bindWidget() {

        dateTextView = (TextView) findViewById(R.id.textView19);
        nameTextView = (TextView) findViewById(R.id.textView20);
        addressTextView = (TextView) findViewById(R.id.textView21);
        phoneTextView = (TextView) findViewById(R.id.textView22);
        totalTextView = (TextView) findViewById(R.id.textView23);
        orderListView = (ListView) findViewById(R.id.listView2);

    }   //bindWidget

}   // Main class
