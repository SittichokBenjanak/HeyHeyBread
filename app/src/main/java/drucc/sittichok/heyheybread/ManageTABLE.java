package drucc.sittichok.heyheybread;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mosza_000 on 2/1/2559.
 */
public class ManageTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public ManageTABLE(Context context) {

        //Create & Connected
        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();


    } //Constructor



}   //Main class
