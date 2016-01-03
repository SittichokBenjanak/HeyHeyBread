package drucc.sittichok.heyheybread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        String[] breadStrings = objManageTABLE.readAllBread(1);
        String[] priceStrings = objManageTABLE.readAllBread(2);
        String[] stockStrings = objManageTABLE.readAllBread(3);
        String[] iconStrings = objManageTABLE.readAllBread(4);

        ListView menuListView = (ListView) findViewById(R.id.listView);
        MenuAdapter objMenuAdapter = new MenuAdapter(showMenuActivity.this,
                stockStrings, priceStrings, breadStrings, iconStrings);
        menuListView.setAdapter(objMenuAdapter);


    }   //  ListViewController

}   // Main Class
