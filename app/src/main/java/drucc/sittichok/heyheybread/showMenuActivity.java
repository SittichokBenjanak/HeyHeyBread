package drucc.sittichok.heyheybread;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class showMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu);

        //ListView Controller
        ListViewController();

    }   //  onCreate

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

        CharSequence[] mySequences = {"1 ชิ้น" ,"2 ชิ้น" ,"3 ชิ้น" ,"4 ชิ้น" ,"5 ชิ้น" ,
                "6 ชิ้น" ,"7 ชิ้น" ,"8 ชิ้น" ,"9 ชิ้น" ,"10 ชิ้น" ,};
        final int intItem = 0;

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle(breadString);
        objBuilder.setSingleChoiceItems(mySequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int intItem = i + 1;
                UpdateOrderToSQLit(breadString, priceString,intItem);

                dialogInterface.dismiss();
            }   // event
        });

        objBuilder.show();


    }   // ChooseItem

    private void UpdateOrderToSQLit(String breadString, String priceString, int intItem) {



    }   //UpdateOrderToSQLit

}   // Main Class
