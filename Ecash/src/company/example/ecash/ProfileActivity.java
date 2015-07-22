package company.example.ecash;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ProfileActivity extends Activity {

	
	TextView Tname,Tacc,Temai;
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
		setContentView(R.layout.activity_profile);
		
		
		Tname=(TextView)findViewById(R.id.AbtUs);
		Tacc=(TextView)findViewById(R.id.PrAcc);
		Temai=(TextView)findViewById(R.id.PrEm);
		
		
		SharedPreferences shf = getSharedPreferences("MyTmp", Context.MODE_PRIVATE);
		Tname.append(" "+shf.getString("name", "Not Available"));
		Tacc.append(" "+shf.getString("accno", "Not Available"));
		Temai.append(" "+shf.getString("email","Not Available"));
		
		this.setTitle("Current User");
		
		
		//###############################################################
     	menu = new String[]{"Withdrawal","Deposit","My Balance","History","Spend","About Us","Logout"};
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
	                getActionBar().setTitle("Current User");
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
				    	it =new Intent(getBaseContext(),Spend.class);
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
		//getMenuInflater().inflate(R.menu.profile, menu);
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
