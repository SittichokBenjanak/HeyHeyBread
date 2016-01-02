package drucc.sittichok.heyheybread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    //Explicit
    private EditText UserEditText,PasswordEditText,NameEditText,
    SurnameEditText,AddressEditText, PhonEditText;

    private String userString,passwordString, nameString,
    surnameString,addressString, phoneString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Bind Widget
        bindWidget();

    }   // onCreate

    private void bindWidget() {

    }
}   // Main class
