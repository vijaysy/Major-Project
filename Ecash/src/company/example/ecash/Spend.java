package company.example.ecash;


import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.NumberPicker.OnValueChangeListener;
import android.view.View.OnClickListener;

import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;  

public class Spend  extends Activity {
	
	Button b1,b2;
	TextView txtphoneNo,t10,t50,t100,SpendAmt;
	MoneyWallet db1;
	String phoneNo,name;
	Cursor rs;
	StringBuilder FRtnStr;
	int amt=0,cnt10,cnt50,cnt100;
	NumberPicker pik10,pik50,pik100;
	Boolean f=true;
	//###########################################
	 String[] menu;
     DrawerLayout dLayout;
     ListView dList;
     ArrayAdapter<String> adapter;
     ActionBarDrawerToggle mDrawerToggle;
     Intent it;
	//#####################################
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spend);
		
		b1=(Button)findViewById(R.id.WtDraSub);
		b2=(Button)findViewById(R.id.brws);
		txtphoneNo=(TextView)findViewById(R.id.mobilno);
		SpendAmt=(TextView)findViewById(R.id.SpndAmt);
		
		
		t10=(TextView)findViewById(R.id.Spndtv10);
		t50=(TextView)findViewById(R.id.Spndtv50);
		t100=(TextView)findViewById(R.id.Spndtv100);
		
		pik10=(NumberPicker)findViewById(R.id.numberPicker1);
		pik50=(NumberPicker)findViewById(R.id.numberPicker2);
		pik100=(NumberPicker)findViewById(R.id.numberPicker3);
		
		
		this.setTitle("Spending");
		
		b1.setEnabled(false);
		
		SharedPreferences shf = getSharedPreferences("MyTmp", Context.MODE_PRIVATE);
		
		StaticData.setM(Integer.parseInt(shf.getString("m","")));
		
		
		//###############################################################
     	menu = new String[]{"Withdrawal","Deposit","My Balance","History","User Details","About Us","Logout"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
        
        dList.setAdapter(adapter);
        dList.setCacheColorHint(Color.BLACK);
		//dList.setSelector(android.R.color.holo_blue_dark);
		
		ActionBar ab=getActionBar();
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));
		ab.setDisplayHomeAsUpEnabled(true);
	    ab.setHomeButtonEnabled(true);
	    ab.setIcon(R.drawable.smlic);
		ab.show();
		
		
		
		   mDrawerToggle = new ActionBarDrawerToggle(this, dLayout,
	                R.drawable.ic_drawer, //nav menu toggle icon
	                R.string.app_name, // nav drawer open - description for accessibility
	                R.string.app_name // nav drawer close - description for accessibility
	        ){
	            public void onDrawerClosed(View view) {
	                getActionBar().setTitle("Spending");
	                // calling onPrepareOptionsMenu() to show action bar icons
	                invalidateOptionsMenu();
	            }
	 
	            public void onDrawerOpened(View drawerView) {
	                getActionBar().setTitle("Menu");
	                // calling onPrepareOptionsMenu() to hide action bar icons
	                invalidateOptionsMenu();
	            }
	        };	
		
		
		dLayout.setDrawerListener(mDrawerToggle);

       dList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
				
				dLayout.closeDrawers();		
				
				  switch (position) {
				    case 0:
				    	 it =new Intent(getBaseContext(),Withdraw.class);
						 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						 startActivity(it);
							finish();
				         break;
				      
				    case 1:
				    	 it =new Intent(getBaseContext(),Deposit.class);
						 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						 startActivity(it);
							finish();
						 break;
						 
				    case 2:
				    	it =new Intent(getBaseContext(),MyBalance.class);
						it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(it);
						finish();
						break;
						
				    case 3:
				    	it =new Intent(getBaseContext(),HistoryActivity.class);
						it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(it);
						finish();
						break;	
						
				    case 4:
				    	it =new Intent(getBaseContext(),ProfileActivity.class);
						it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(it);
						finish();
						break;	
						
				    case 5:
				    	it =new Intent(getBaseContext(),AboutUsActivity.class);
						it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(it);
						finish();
						break;	
						
				    case 6:
				    	it =new Intent(getBaseContext(),MainActivity.class);
						it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(it);
						finish();
						break;	
						
						
				
				    default:
				      break;
				    }
				
				
				
			}
       	
       });
		
		//######################################################
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
		

		db1=new MoneyWallet(this);
		
		int Cont10=db1.getCoinCount("10");
		int Cont50=db1.getCoinCount("50");
		int Cont100=db1.getCoinCount("100");
		
		pik10.setMaxValue(Cont10);
		pik50.setMaxValue(Cont50);
		pik100.setMaxValue(Cont100);
		

		
		t10.append(" "+Cont10);
		t50.append(" "+Cont50);
		t100.append(" "+Cont100);
	
		//#################################Number Pickers###########################################
		pik10.setOnValueChangedListener(new OnValueChangeListener() {
			
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			// TODO Auto-generated method stub
			
			cnt10=newVal;
			amt=amt-oldVal*10;
			amt=amt+newVal*10;
			SpendAmt.setText("Total Amount:"+" "+amt);
			
			if(amt==0)
				b1.setEnabled(false);
			else
				b1.setEnabled(true);
			
			
		}
	});
		
		
		pik50.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
				
				cnt50=newVal;
				amt=amt-oldVal*50;
				amt=amt+newVal*50;
				SpendAmt.setText("Total Amount:"+" "+amt);
				
				if(amt==0)
					b1.setEnabled(false);
				else
					b1.setEnabled(true);
				
			}
		});
		
		
		pik100.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
				
				cnt100=newVal;
				amt=amt-oldVal*100;
				amt=amt+newVal*100;
				SpendAmt.setText("Total Amount:"+" "+amt);
				
				if(amt==0)
					b1.setEnabled(false);
				else
					b1.setEnabled(true);
				
				
			}
		});
		
		//###########################################################################################

		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				 phoneNo = txtphoneNo.getText().toString();
				 FRtnStr = new StringBuilder();
				 
        		 if(cnt10!=0){
			          FRtnStr.append(GetMoney("10", cnt10));
			          if(cnt100!=0||cnt50!=0)
			          FRtnStr.append("@");
        		 }
        		 if(cnt50!=0){
			          FRtnStr.append(GetMoney("50", cnt50));
			          if(cnt100!=0)
			          FRtnStr.append("@");
        		 }
        		 if(cnt100!=0){
			           FRtnStr.append(GetMoney("100", cnt100));
			           //FRtnStr.append("@");
        		 }
			  
        		int Cont10=db1.getCoinCount("10");
     			int Cont50=db1.getCoinCount("50");
     			int Cont100=db1.getCoinCount("100");
     			
     			pik10.setMaxValue(Cont10);
     			pik50.setMaxValue(Cont50);
     			pik100.setMaxValue(Cont100); 
        		 
			      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Spend.this);
			      if(!f)
			      alertDialogBuilder.setMessage("Are you sure, To send this money to " +name);
			      else
			      alertDialogBuilder.setMessage("Are you sure, To send this money to " +txtphoneNo.getText());

			      alertDialogBuilder.setPositiveButton(R.string.yes_button, 
			      new DialogInterface.OnClickListener() {
					
			         @Override
			         public void onClick(DialogInterface arg0, int arg1) {
			        	 
			        	 try {
					     SmsManager smsManager = SmsManager.getDefault();
					     smsManager.sendTextMessage(phoneNo, null, "*"+Encryption.cipher("Ya", FRtnStr.toString()), null, null);
					     Toast.makeText(getApplicationContext(), "SMS sent.",Toast.LENGTH_LONG).show();
					     
					     if(!f)
					        db1.putHistory("Money Spent Rs "+amt+" to " + name);
					     else
					    	 db1.putHistory("Money Spent Rs "+amt+" to " + txtphoneNo.getText());
					     
					     
					     Intent it =new Intent(getBaseContext(),MyBalance.class);
							it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(it);
							finish();
					      } catch (Exception e) {
					         Toast.makeText(getApplicationContext(),
					         "SMS faild, please try again.",
					         Toast.LENGTH_LONG).show();
					         e.printStackTrace();
					      }
				    
			           
						
			         }
			      });
			      alertDialogBuilder.setNegativeButton(R.string.no_button, 
			      new DialogInterface.OnClickListener() {
						
			         @Override
			         public void onClick(DialogInterface dialog, int which) {
			        	// Intent it =new Intent(getBaseContext(),Spend.class);
							//it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							//startActivity(it);
			        	 
					 }
			      });
				    
			      AlertDialog alertDialog = alertDialogBuilder.create();
			      
			      alertDialog.show();     
			    
				
			}
		});
		
		//###############################################################################################
		
			b2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,Contacts.CONTENT_URI);  
				     startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);  
					
				}
			});
		
		//################################################################################################
		
	}

	//###############################################################################################

private String GetMoney(String amt, int cnt) {
		
		int tmpCnt=0;
		StringBuilder RtnStr = new StringBuilder();
		ArrayList<String> Coin= new ArrayList<String>();
		ArrayList<String> Amt=new ArrayList<String>();
		ArrayList<String> newCoin=new ArrayList<String>();
		ArrayList<String> sndMsg=new ArrayList<String>();
		int[] c= new int[20];
		int[] x= new int[20];
		int[] y= new int[20];
		
		
		//Log.v("Deposit", "Hi....1");
		 rs = db1.getData(amt);
		// Log.v("Deposit", "Hi....1");
		 
		
         rs.moveToFirst();
         
         while(rs.isAfterLast() == false&&tmpCnt<cnt){
        	 //Log.v("Deposit", "Hi....12");
        	 Coin.add(rs.getString(rs.getColumnIndex("Coin")));
             Amt.add(rs.getString(rs.getColumnIndex("Amt")));
             c[tmpCnt]=Integer.parseInt(rs.getString(rs.getColumnIndex("S")));
             Log.v("Deposit", "Hi....13");
             x[tmpCnt]=Integer.parseInt(db1.GetNum());
             Log.v("Deposit", "Hi....14");
             y[tmpCnt]=StaticData.getM()*x[tmpCnt]+c[tmpCnt];
             newCoin.add(Coin.get(tmpCnt)+"h"+x[tmpCnt]+"~"+y[tmpCnt]); 
             sndMsg.add(newCoin.get(tmpCnt)+"\n"+Amt.get(tmpCnt)+"\n"+c[tmpCnt]);
             tmpCnt++;
             rs.moveToNext();
        	 
         }
          
		for(int i=0;i<cnt;i++){
			RtnStr.append(sndMsg.get(i));
			if(i<cnt)
			RtnStr.append("@");
			db1.deleteMoney(amt);
		}
			
			
		
		return RtnStr.toString();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.spendmenu, menu);
		return true;
	}

	//###################################################################
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // toggle nav drawer on selecting action bar app icon/title
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	        // Handle action bar actions click
	        switch (item.getItemId()) {
	        case R.id.action_settings:
	            return true;
	            
	        case R.id.action_refresh:
	        	
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
	 
	 
	 @Override
		protected void onPostCreate(Bundle savedInstanceState) {
		    super.onPostCreate(savedInstanceState);
		    mDrawerToggle.syncState();
		}
		
	//######################################################################
	 private static final int CONTACT_PICKER_RESULT = 1001;
		
		

	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	         String phone="";
	         Cursor contacts=null;
	         try{
	            if (resultCode == RESULT_OK) {  
	                switch (requestCode) {  
	                case CONTACT_PICKER_RESULT:  
	                    // gets the uri of selected contact
	                 Uri result = data.getData();
	                 // get the contact id from the Uri (last part is contact id) 
	                 String id = result.getLastPathSegment(); 
	                     //queries the contacts DB for phone no
	                 contacts=getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?", new String[] { id },  null);
	                    //gets index of phone no
	                 int phoneIdx = contacts.getColumnIndex(Phone.DATA); 
	                 int nameId=contacts.getColumnIndex(Phone.DISPLAY_NAME);
	                 if (contacts.moveToFirst()) {
	                       //gets the phone no  
	                	 	String p2=contacts.getString(phoneIdx); 
	                       phone = p2.replace(" ","");
	                       if(phone.length()>10)	                    	   
	                    	   txtphoneNo.setText(phone.substring(3, phone.length())); 
	                       else
	                    	   txtphoneNo.setText(phone);
	                       f=false;
	                       name=new String(contacts.getString(nameId));
	                 } 
	                 else {  
	                  Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
	                 }  
	                 break;  
	                }  
	          
	            } 
	            else {  
	                // gracefully handle failure  
	              //  Toast.makeText(this, "Warning: activity result not ok",Toast.LENGTH_SHORT).show();  
	            }  
	        }
	         catch (Exception e) {  
	            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();  
	         } 
	         finally{  
	            if (contacts != null) {  
	                contacts.close();  
	            }
	         }
	        }
//#######################################################################	
	
}
