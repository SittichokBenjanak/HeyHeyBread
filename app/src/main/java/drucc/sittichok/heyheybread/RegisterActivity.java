package drucc.sittichok.heyheybread;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    public void clickSave(View view) {

        //Check Space
        userString = UserEditText.getText().toString().trim();
        passwordString = PasswordEditText.getText().toString().trim();
        nameString = NameEditText.getText().toString().trim();
        surnameString = SurnameEditText.getText().toString().trim();
        addressString = AddressEditText.getText().toString().trim();
        phoneString = PhonEditText.getText().toString().trim();

        if (userString.equals("") ||
                passwordString.equals("") ||
                nameString.equals("") ||
                surnameString.equals("") ||
                addressString.equals("") ||
                phoneString.equals("")) {

            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(RegisterActivity.this, "มีช่องว่าง", "กรุณากรอกทุกช่อง");

        } else {

            //No Space
            confirmRegister();
        } // if

    }   //clickSave

    private void confirmRegister() {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_myaccount);
        objBuilder.setTitle("โปรตรวจสอบข้อมูล");
        objBuilder.setMessage("User = " + userString + "\n" +
                "Password = " + passwordString + "\n" +
                "Name = " + nameString + "\n" +
                "Surname = " + surnameString + "\n" +
                "Address = " + addressString + "\n" +
                "Phone = " + phoneString + "\n");
        objBuilder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                upDateMySQL();
                dialog.dismiss();
            }
        });
        objBuilder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        objBuilder.show();

    }   // confirmRegister

    private void upDateMySQL() {

        // Intent To MainActivity
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));


    }   // upDateMySQL

    private void bindWidget() {
        UserEditText = (EditText) findViewById(R.id.edtUser);
        PasswordEditText= (EditText) findViewById(R.id.edtPass);
        NameEditText = (EditText) findViewById(R.id.edtName);
        SurnameEditText = (EditText) findViewById(R.id.edtSurname);
        AddressEditText = (EditText) findViewById(R.id.edtAddress);
        PhonEditText = (EditText) findViewById(R.id.edtPhone);
    }
}   // Main class
