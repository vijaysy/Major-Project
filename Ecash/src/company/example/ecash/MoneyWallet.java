package company.example.ecash;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoneyWallet extends SQLiteOpenHelper {

	public MoneyWallet(Context context){
	      super(context, "MyECash2" , null, 1);
	      
	   }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(
			      "create table money10 " +
			      "(Coin text primary key , Amt text , S text )"
			      );
		
		
		db.execSQL(
			      "create table money50 " +
			      "(Coin text primary key , Amt text , S text )"
			      );
		
		
		db.execSQL(
			      "create table money100 " +
			      "(Coin text primary key , Amt text , S text )"
			      );
		//###########################################################################
		
		db.execSQL(
			      "create table history " +
			      "(Stm text key )"
			      );
		
		//############################################################################
		db.execSQL(
			      "create table uniqueNum " +
			      "(Num text primary key)"
			      );	
		
		  db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('4');"); 		 
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('6');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('10');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('7');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('1');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('5');");
          db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('9');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('3');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('8');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('2');");		
	      
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('11');"); 		 
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('12');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('13');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('14');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('15');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('16');");
          db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('17');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('18');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('19');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('20');");
	      
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('21');"); 		 
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('22');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('23');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('24');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('25');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('26');");
          db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('27');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('28');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('29');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('30');");
	      
	      
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('31');"); 		 
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('32');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('33');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('34');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('35');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('36');");
          db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('37');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('38');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('39');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('40');");
	      
	      
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('41');"); 		 
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('42');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('43');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('44');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('45');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('46');");
          db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('47');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('48');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('49');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('50');");
	      
	      
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('51');"); 		 
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('52');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('53');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('54');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('55');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('56');");
          db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('57');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('58');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('59');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('60');");
	      
	      
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('61');"); 		 
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('62');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('63');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('64');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('65');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('66');");
          db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('67');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('68');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('69');");
	      db.execSQL("INSERT INTO uniqueNum (Num)  VALUES ('70');");
		  

	}
	
	


	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		 db.execSQL("DROP TABLE IF EXISTS money10");
		 db.execSQL("DROP TABLE IF EXISTS money50");
		 db.execSQL("DROP TABLE IF EXISTS money100");
		 db.execSQL("DROP TABLE IF EXISTS history");
		 db.execSQL("DROP TABLE IF EXISTS uniqueNum");
	      onCreate(db);

	}
	
	
	//****************************Inserting Money******************************************
	 public boolean insertMoney  (String key1, String key2 , String S ){
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();

	      contentValues.put("Coin", key1);
	      contentValues.put("Amt", key2);
	      contentValues.put("S", S);
	     
	      
	       db.insert("money"+key2, null, contentValues);
	      //Toast.makeText(null, "Inserted", Toast.LENGTH_SHORT).show();
	      return true;
	   }
	 
 //************************************ Histroy *****************************************************
	 public boolean putHistory  (String s){
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	       contentValues.put("Stm", s); 
	       db.insert("history", null, contentValues);
	      //Toast.makeText(null, "Inserted", Toast.LENGTH_SHORT).show();
	      return true;
	   }
	 
	 public ArrayList<String> getHistory(){
		  try{
		 ArrayList<String> array_list = new ArrayList<String>();
	     SQLiteDatabase db = this.getReadableDatabase();
	     Cursor res =  db.rawQuery( "SELECT  * FROM  history",null);
	      res.moveToFirst();
	    
	      while(res.isAfterLast() == false){
	      array_list.add(res.getString(res.getColumnIndex("Stm")));
	      res.moveToNext();
	      }
	      return array_list;
	      }catch (Exception e){
	    	  e.printStackTrace();
	      }
		  
		  return new ArrayList<String>();
	   
	   }
	 
//*************************** Count of ECash**********************************************
	 
	 public int getCoinCount(String Amt) {
		    String countQuery = "SELECT  * FROM " + "money"+Amt;
		    SQLiteDatabase db = this.getReadableDatabase();
		    Cursor cursor = db.rawQuery(countQuery, null);
		    int cnt = cursor.getCount();
		    cursor.close();
		    return cnt;
		}
	 
//******************************************************************************************	 
	 
	 
	 
	 public ArrayList<String> getAllMoney(String Amt){
		  try{
		 ArrayList<String> array_list = new ArrayList<String>();
	     SQLiteDatabase db = this.getReadableDatabase();
	     Cursor res =  db.rawQuery( "SELECT  * FROM  money"+Amt+"",null);
	      res.moveToFirst();
	    
	      while(res.isAfterLast() == false){
	      array_list.add(res.getString(res.getColumnIndex("Coin")));      
	      array_list.add(res.getString(res.getColumnIndex("Amt")));
	      array_list.add(res.getString(res.getColumnIndex("S")));
	      res.moveToNext();
	      }
	      return array_list;
	      }catch (Exception e){
	    	  e.printStackTrace();
	      }
		  
		  return new ArrayList<String>();
	   
	   }
	 
	 
	//********************************Deleting the money*********************************************
	 public void deleteMoney (String id){	 
		 ArrayList<String> array_list = new ArrayList<String>();
	     SQLiteDatabase db = this.getReadableDatabase();
	     
	      Cursor res =  db.rawQuery( "select * from money"+id+" where Amt="+id, null );
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	      array_list.add(res.getString(res.getColumnIndex("Coin")));      
	      array_list.add(res.getString(res.getColumnIndex("Amt")));
	      res.moveToNext();
	      }
	       db = this.getWritableDatabase();
	       db.delete("money"+id, 
	      "Coin = ? ", 
	      new String[] { array_list.get(0) });
	   }
	 
	
	 
	//*************************************************************************************** 
	 
	 public String GetNum(){
		 ArrayList<String> array_list = new ArrayList<String>();
	     SQLiteDatabase db = this.getReadableDatabase();
	     
	      Cursor res =  db.rawQuery( "select * from uniqueNum ", null );
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	      array_list.add(res.getString(res.getColumnIndex("Num")));      
	      res.moveToNext();
	      }
	       db = this.getWritableDatabase();
	       db.delete("uniqueNum", 
	       "Num = ? ", 
	       new String[] { array_list.get(0) });
		 
		 
		 return array_list.get(0);
	 }
	 
	 
	 
	 
	 public Cursor getData(String id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery("select * from money"+id+"", null );
	      return res;
	   }
	
	

}
