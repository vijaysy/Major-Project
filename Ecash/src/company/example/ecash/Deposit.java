package company.example.ecash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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

public class Deposit extends Activity{

	TextView SpendAmt;
	Button b;
	MoneyWallet db1;
	String newCoin,url;
	StringBuilder FRtnStr;
	Cursor rs;
	NumberPicker pik10,pik50,pik100;
	int amt=0,cnt10,cnt50,cnt100;
	
	//###########################################
	String[] menu;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> adapter;
    ActionBarDrawerToggle mDrawerToggle;
    Intent it;
   //#####################################
	
	public Deposit() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deposit);
		
		db1=new MoneyWallet(this);
		
		this.setTitle("Deposit");
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
		
	
		
		b=(Button)findViewById(R.id.DepositSub);
		b.setEnabled(false);
		
		SpendAmt=(TextView)findViewById(R.id.DSpndAmt);
		pik10=(NumberPicker)findViewById(R.id.DnumberPicker1);
		pik50=(NumberPicker)findViewById(R.id.DnumberPicker2);
		pik100=(NumberPicker)findViewById(R.id.DnumberPicker3);
		
		int Cont10=db1.getCoinCount("10");
		int Cont50=db1.getCoinCount("50");
		int Cont100=db1.getCoinCount("100");
		
		pik10.setMaxValue(Cont10);
		pik50.setMaxValue(Cont50);
		pik100.setMaxValue(Cont100);
		
		
		
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
						b.setEnabled(false);
					else
						b.setEnabled(true);
					
					
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
							b.setEnabled(false);
						else
							b.setEnabled(true);
						
						
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
							b.setEnabled(false);
						else
							b.setEnabled(true);
						
						
					}
				});
				
				//###########################################################################################
		
		
		
		
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Deposit.this);
			      alertDialogBuilder.setMessage("Are you sure, To deposit this money" );
			      alertDialogBuilder.setPositiveButton(R.string.yes_button, 
			      new DialogInterface.OnClickListener() {
					
			         @Override
			         public void onClick(DialogInterface arg0, int arg1) {
			        	 
			        	 try {
					      
			        		 FRtnStr = new StringBuilder();
			        		 if(cnt10!=0){
						          FRtnStr.append(GetMoney("10", cnt10));
						          if(cnt50!=0||cnt100!=0)
						          FRtnStr.append("@");
			        		 }
			        		 if(cnt50!=0){
						          FRtnStr.append(GetMoney("50", cnt50));
						          if(cnt100!=0)
						          FRtnStr.append("@");
			        		 }
			        		 if(cnt100!=0){
						           FRtnStr.append(GetMoney("100", cnt100));
						          // FRtnStr.append("@");
			        		 }
						   		     
						   
						       
						    	  
			        		    int Cont10=db1.getCoinCount("10");
			        			int Cont50=db1.getCoinCount("50");
			        			int Cont100=db1.getCoinCount("100");
			        			
			        			pik10.setMaxValue(Cont10);
			        			pik50.setMaxValue(Cont50);
			        			pik100.setMaxValue(Cont100); 
					         
					         Log.v("URL","https://"+StaticData.getIp()+"/ecash/deposit.php?accountno="+StaticData.getID()+"&coin="+FRtnStr.toString());
					         url="http://"+StaticData.getIp()+"/deposit.php?accountno="+StaticData.getID()+"&coin="+FRtnStr.toString();
					         //Log.v("InvalidChat",""+url.charAt(76));
					         new login().execute("");
					         
					     Toast.makeText(getApplicationContext(), "Deposited",Toast.LENGTH_LONG).show();
					      } catch (Exception e) {
					        
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
		
		
		//###############################################################
     	menu = new String[]{"Withdraw","My Balance","Spend","History","User Details","About Us","Logout"};
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
	                getActionBar().setTitle("Deposit");
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
				    	 it =new Intent(getBaseContext(),MyBalance.class);
						 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						 startActivity(it);
						 finish();
						 break;
						 
				    case 2:
				    	it =new Intent(getBaseContext(),Spend.class);
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
						startActivityForResult(it,1);
						finish();
						break;
						
				    case 5:
				    	it =new Intent(getBaseContext(),AboutUsActivity.class);
						it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivityForResult(it,1);
						finish();
						break;
						
				    case 6:
				    	it =new Intent(getBaseContext(),MainActivity.class);
						it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivityForResult(it,1);
						finish();
						break;
						
				
				    default:
				      break;
				    }
				
				
				
			}
       	
       });
		
		//######################################################
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	
	private class login extends AsyncTask<String, Void, JSONObject>{
		HttpResponse respons;

	ProgressDialog dialog = ProgressDialog.show(Deposit.this, "", "Authenticating2, Please wait...");

	@Override
	protected JSONObject doInBackground(String... params) {
	    Log.i("thread", "Doing Something...");
	   //authentication operation
	try{

	  
	   
		HttpClient client=new DefaultHttpClient();
		HttpGet request = new HttpGet();
		String xyz="http://"+StaticData.getIp()+"/deposit.php?accountno="+StaticData.getID()+"&coin="+FRtnStr.toString();
		String xyz2=xyz.replace("~","%7E");
		Log.v("Coin", FRtnStr.toString());
		//Log.v("Invalid", ""+xyz2.charAt(84));
		request.setURI(new URI(xyz2));
		
		respons=client.execute(request);
		db1.putHistory("Deposited Amount "+amt);
		
		Intent it =new Intent(getBaseContext(),MyBalance.class);
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(it);
		finish();
	
		
		
	}

	  catch(Exception e){
	    e.printStackTrace();   

	}

	    return null;
	}




	protected void onPreExecute(){
	    //dialog.dismiss();
	    Log.i("thread", "Started...");
	    dialog.show();
	}



	protected void onPostExecute(JSONObject result){
	    Log.i("thread", "Done...");
	    dialog.dismiss();
	    
	    try {
	    	BufferedReader inBuff=new BufferedReader(new InputStreamReader(respons.getEntity().getContent()));
	    	String Coin=null;
	    	Coin=inBuff.readLine();
	           
	            Toast.makeText(getApplicationContext(), "Deposited Status : "+Coin, Toast.LENGTH_LONG).show();
		   
	    	

	    	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	   }

	  }
	
	private String GetMoney(String amt, int cnt) {
		
		int tmpCnt=0;
		StringBuilder RtnStr = new StringBuilder();
		ArrayList<String> Coin= new ArrayList<String>();
		ArrayList<String> Amt=new ArrayList<String>();
		ArrayList<String> newCoin=new ArrayList<String>();
		int[] c= new int[20];
		int[] x= new int[20];
		int[] y= new int[20];
		
		
		Log.v("Deposit", "Hi....1");
		 rs = db1.getData(amt);
		 Log.v("Deposit", "Hi....1");
		 
		
         rs.moveToFirst();
         
         while(rs.isAfterLast() == false&&tmpCnt<cnt){
        	 Log.v("Deposit", "Hi....12");
        	 Coin.add(rs.getString(rs.getColumnIndex("Coin")));
             Amt.add(rs.getString(rs.getColumnIndex("Amt")));
             c[tmpCnt]=Integer.parseInt(rs.getString(rs.getColumnIndex("S")));
             Log.v("Deposit", "Hi....13");
             x[tmpCnt]=Integer.parseInt(db1.GetNum());
             Log.v("Deposit", "Hi....14");
             y[tmpCnt]=StaticData.getM()*x[tmpCnt]+c[tmpCnt];
             newCoin.add(Coin.get(tmpCnt)+"h"+x[tmpCnt]+"~"+y[tmpCnt]); 
             //sndMsg.add(newCoin.get(tmpCnt)+"\n"+Amt.get(tmpCnt)+"\n"+c[tmpCnt]);
             tmpCnt++;
             rs.moveToNext();
        	 
         }
          
		for(int i=0;i<cnt;i++){
			RtnStr.append(newCoin.get(i));
			if((i+1)!=cnt)
			RtnStr.append("@");
			db1.deleteMoney(amt);
		}
			
			
		
		return RtnStr.toString();
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent output = new Intent();
		setResult(RESULT_OK, output);
		finish();
		
		
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
	
			
	
	
}
