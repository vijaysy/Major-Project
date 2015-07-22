package company.example.ecash;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.NumberPicker.OnValueChangeListener;

public class Withdraw  extends Activity {
	
	Button sb;
	EditText amt2;
	MoneyWallet db1;
	String Coin,Yint,Amt;
	NumberPicker pik10,pik50,pik100;
	int amt=0,cnt10,cnt50,cnt100;
	
	//###########################################
		 String[] menu;
	     DrawerLayout dLayout;
	     ListView dList;
	     ArrayAdapter<String> adapter;
	     ActionBarDrawerToggle mDrawerToggle;
	     Intent it;
	     TextView SpendAmt;
	//#####################################
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.withdraw);
		
		db1=new MoneyWallet(this);
		amt2=(EditText)findViewById(R.id.WithDrawAmt);
		sb=(Button)findViewById(R.id.WtDraSub);
		SpendAmt=(TextView)findViewById(R.id.SpndAmt);
		
		
		pik10=(NumberPicker)findViewById(R.id.DnumberPicker1);
		pik50=(NumberPicker)findViewById(R.id.DnumberPicker2);
		pik100=(NumberPicker)findViewById(R.id.DnumberPicker3);
		
		sb.setEnabled(false);
		
		
		
		SharedPreferences prefs = getSharedPreferences("Ecash_Password_File", MODE_PRIVATE);
		StaticData.setPass(prefs.getString("Pass", "123"));
		
		pik10.setMaxValue(10);
		pik50.setMaxValue(10);
		pik100.setMaxValue(10);
		
		this.setTitle("Withdrawal");
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
		
		
		sb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it =new Intent(getBaseContext(),MakePayment.class);
				it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				it.putExtra("C10", cnt10);
				it.putExtra("C50", cnt50);
				it.putExtra("C100", cnt100);
				startActivity(it);
				finish();
				
			}
		});
		
		
		
		//#################################Number Pickers###########################################
		pik10.setOnValueChangedListener(new OnValueChangeListener() {
			
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			// TODO Auto-generated method stub
			
			cnt10=newVal;
			cnt10=newVal;
			amt=amt-oldVal*10;
			amt=amt+newVal*10;
			SpendAmt.setText("Total Amount:"+" "+amt);
			
			if(amt==0)
				sb.setEnabled(false);
			else
				sb.setEnabled(true);
			
			
		}
	});
		
		
		pik50.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
				
				cnt50=newVal;
				cnt50=newVal;
				amt=amt-oldVal*50;
				amt=amt+newVal*50;
				SpendAmt.setText("Total Amount:"+" "+amt);
				
				if(amt==0)
					sb.setEnabled(false);
				else
					sb.setEnabled(true);
				
				
				
			}
		});
		
		
		pik100.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
				
				cnt100=newVal;
				cnt100=newVal;
				amt=amt-oldVal*100;
				amt=amt+newVal*100;
				SpendAmt.setText("Total Amount:"+" "+amt);
				
				if(amt==0)
					sb.setEnabled(false);
				else
					sb.setEnabled(true);
				
				
				
			}
		});
		
		//###########################################################################################

		
		//###############################################################
     	menu = new String[]{"My Balance","Deposit","Spend","History","User Details","About Us","Logout"};
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
	                getActionBar().setTitle("Withdrawal");
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
				    	 it =new Intent(getBaseContext(),MyBalance.class);
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
		
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.options, menu);
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
	
	
}