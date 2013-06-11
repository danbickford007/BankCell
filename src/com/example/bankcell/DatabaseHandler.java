package com.example.bankcell;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "banking_app";
	private static final String DATABASE_CREATE_CELLS = "create table cells (id integer primary key autoincrement, name text not null);";
	private static final String DATABASE_CREATE_BALANCES = "create table if not exists balances (id integer primary key autoincrement, amount real, category integer, cell_id integer);";
	 
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        
    }
    @Override
	public void onCreate(SQLiteDatabase db) {
    	db.execSQL(DATABASE_CREATE_CELLS);
    	db.execSQL(DATABASE_CREATE_BALANCES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS cells");
		db.execSQL("DROP TABLE IF EXISTS balances");
        onCreate(db);
	}
    
    public void createCell(Editable editable){
    	SQLiteDatabase db = this.getWritableDatabase();
    	String sqlDataStore = "insert into cells (name) values ('"+editable+"')";
    	db.execSQL(sqlDataStore);
    }
    
    public String[] getCells(){
    	String countQuery = "SELECT  * FROM cells;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        String[] array = new String[cursor.getCount()];
        int i = 0;
        while(cursor.moveToNext()){
            String uname = cursor.getString(cursor.getColumnIndex("name"));
            array[i] = uname;
            i++;
        }
        cursor.close();
        return array;
    }
	
	public void createDeposit(Float amount){
		float current = Float.parseFloat(getTotalBalance());
		amount = current + amount;
		SQLiteDatabase db = this.getWritableDatabase();
    	String sqlDataStore = "update balances set amount = "+amount+" where category = 1";
    	db.execSQL(sqlDataStore);
	}
	
	public String getTotalBalance(){
		SQLiteDatabase db = this.getReadableDatabase();
		String total = "0.00";
        Cursor cursor = db.query("balances", new String[] {"id", "amount", "category"}, "category" + "=?",
                new String[] { String.valueOf(1) }, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            total = cursor.getString(1);
        }
        return String.format("%.2f", Float.valueOf(total));
	}
	
	public void destroyCell(String cellName){
		SQLiteDatabase db = this.getWritableDatabase();
    	String sqlDataStore = "delete from cells where name = '"+cellName+"'";
    	db.execSQL(sqlDataStore);
	}

}
