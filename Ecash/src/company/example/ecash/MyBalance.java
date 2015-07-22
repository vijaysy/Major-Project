package company.example.ecash;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyBalance extends Activity {
	     String[] menu;
	     DrawerLayout dLayout;
	     ListView dList;
	     ArrayAdapter<String> adapter;
	     ActionBarDrawerToggle mDrawerToggle;
	     Intent it;
	     MoneyWallet db1;
	     TextView t10,t50,t100,tt;
	 	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mybalance);
		
		
	     	menu = new String[]{"Withdrawal","Deposit","Spend","History","User Details","About Us","Logout"};
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
			this.setTitle("Welcome to eWalet");
			
		    
		    
			//################### Demo Insert ######################
			
			db1=new MoneyWallet(this);
			
//			if(StaticData.isF()){
//				db1.insertMoney("121", "50", "45");
//				db1.insertMoney("122", "50", "45");
//				db1.insertMoney("125", "50", "45");
//				db1.insertMoney("127", "50", "45");
//				db1.insertMoney("152", "50", "45");
//				db1.insertMoney("158", "50", "45");
//				
//				db1.insertMoney("121", "10", "45");
//				db1.insertMoney("122", "10", "45");
//				db1.insertMoney("123", "10", "45");
//				db1.insertMoney("124", "10", "45");
//				db1.insertMoney("125", "10", "45");
//				db1.insertMoney("127", "10", "45");
//				db1.insertMoney("152", "10", "45");
//				db1.insertMoney("158", "10", "45");
//				
//				db1.insertMoney("121", "100", "45");
//				db1.insertMoney("122", "100", "45");
//				db1.insertMoney("123", "100", "45");
//				db1.insertMoney("124", "100", "45");
//				StaticData.setF(false);
//				
//			}
			
	//##################################################
		    
			
			t10=(TextView)findViewById(R.id.amt10);
			t50=(TextView)findViewById(R.id.amt50);
			t100=(TextView)findViewById(R.id.amt100);
			tt=(TextView)findViewById(R.id.ttAmt);
			
			
			int Cont10=db1.getCoinCount("10");
			int Cont50=db1.getCoinCount("50");
			int Cont100=db1.getCoinCount("100");
			
			t10.append(" "+Cont10);
			t50.append(" "+Cont50);
			t100.append(" "+Cont100);
			tt.append(" :"+ (Cont10*10 + Cont100*100 +Cont50*50) );
		    
			
			
		       mDrawerToggle = new ActionBarDrawerToggle(this, dLayout,
		                R.drawable.ic_drawer, //nav menu toggle icon
		                R.string.app_name, // nav drawer open - description for accessibility
		                R.string.app_name // nav drawer close - description for accessibility
		        ){
		            public void onDrawerClosed(View view) {
		                getActionBar().setTitle("Welcome to eWalet");
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
							 startActivityForResult(it,1);
					         break;
					      
					    case 1:
					    	 it =new Intent(getBaseContext(),Deposit.class);
							 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							 startActivityForResult(it,1);
							 break;
							 
					    case 2:
					    	it =new Intent(getBaseContext(),Spend.class);
							it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivityForResult(it,1);
							break;
							
					    case 3:
					    	it =new Intent(getBaseContext(),HistoryActivity.class);
							it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivityForResult(it,1);
							break;
							
							
					    case 4:
					    	it =new Intent(getBaseContext(),ProfileActivity.class);
							it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivityForResult(it,1);
							break;
							
					    case 5:
					    	it =new Intent(getBaseContext(),AboutUsActivity.class);
							it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivityForResult(it,1);
							break;
							
					    case 6:
					    	it =new Intent(getBaseContext(),MainActivity.class);
							it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivityForResult(it,1);
							break;
									
							
					    default:
					      break;
					    }
					
					
					
				}
	        	
	        });
	}


	
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
	        	Toast.makeText(MyBalance.this,"Refreshing...",Toast.LENGTH_SHORT).show();
	        	int Cont10=db1.getCoinCount("10");
				int Cont50=db1.getCoinCount("50");
				int Cont100=db1.getCoinCount("100");
				
				t10.setText("Available 10's: "+Cont10);
				t50.setText("Available 50's: "+Cont50);
				t100.setText("Available 100's: "+Cont100);
				tt.setText("Total Amount :"+(Cont10*10 + Cont100*100 +Cont50*50) );
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
	
	 @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
						
			int Cont10=db1.getCoinCount("10");
			int Cont50=db1.getCoinCount("50");
			int Cont100=db1.getCoinCount("100");
			
			t10.setText("Available 10's: "+Cont10);
			t50.setText("Available 50's: "+Cont50);
			t100.setText("Available 100's: "+Cont100);
			tt.setText("Total Amount :"+ (Cont10*10 + Cont100*100 +Cont50*50) );
		}
	 

	 public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.after_login3, menu);
			return true;
		}
	 
	 
	 /* *
		 * Called when invalidateOptionsMenu() is triggered
		 */
		@Override
		public boolean onPrepareOptionsMenu(Menu menu) {
			// if nav drawer is opened, hide the action items
			boolean drawerOpen = dLayout.isDrawerOpen(dList);
			menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
			return super.onPrepareOptionsMenu(menu);
		}
	 
		
		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
		    super.onPostCreate(savedInstanceState);
		    mDrawerToggle.syncState();
		}
		
}
