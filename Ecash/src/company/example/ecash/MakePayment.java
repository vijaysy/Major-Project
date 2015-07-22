package company.example.ecash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MakePayment extends Activity {
	Button sb;
	MoneyWallet db1;
	String Coin,Yint,Amt;
	String J1,J2;
	int sum=0;
	int c10,c50,c100;
	Intent it;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_payment);
		
		db1=new MoneyWallet(this);
		sb=(Button)findViewById(R.id.WtDraSub);
		
		this.setTitle("Make Payment");
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
		ActionBar ab=getActionBar();
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));
		ab.setIcon(R.drawable.smlic);
		ab.show();
		
		c10=getIntent().getIntExtra("C10",-1);
		c50=getIntent().getIntExtra("C50",-1);
		c100=getIntent().getIntExtra("C100",-1);
		
		SharedPreferences prefs = getSharedPreferences("Ecash_Password_File", MODE_PRIVATE);
		StaticData.setPass(prefs.getString("Pass", "123"));
		
		
		sb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new login().execute("");
				
			}
		});
		
	}
	
	
	
	//######################################################################################
	
	private class login extends AsyncTask<String, Void, JSONObject>{
		HttpResponse respons;

	ProgressDialog dialog = ProgressDialog.show(MakePayment.this, "", "Wait Receiving Ecash from Company");

	@Override
	protected JSONObject doInBackground(String... params) {
	    Log.i("thread", "Doing Something...");
	   //authentication operation
	try{

	   
		HttpClient client=new DefaultHttpClient();
		HttpGet request = new HttpGet();
		request.setURI(new URI("http://"+StaticData.getIp()+"/withdraw.php?accountno="+StaticData.getID()+"&password="+StaticData.getPass()+"&count10="+String.valueOf(c10)+"&count50="+String.valueOf(c50)+"&count100="+String.valueOf(c100)));
		respons=client.execute(request);
		
		
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
	    	 J1=inBuff.readLine();
             Toast.makeText(getApplicationContext(), J1, Toast.LENGTH_LONG).show();
	         new login2().execute("");
	    	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	   }

	  }


	//##########################################################################
	private class login2 extends AsyncTask<String, Void, JSONObject>{
		HttpResponse respons;

	ProgressDialog dialog = ProgressDialog.show(MakePayment.this, "", "Connecting to TTP");

	@Override
	protected JSONObject doInBackground(String... params) {
	    Log.i("thread", "Doing Something...2");
	   //authentication operation
	try{

	   
		HttpClient client=new DefaultHttpClient();
		HttpGet request = new HttpGet();
		String xyz="http://"+StaticData.getIp2()+"/store_coin.php?coin="+J1;
		String xyz2=xyz.replace("{", "%7B");
		String xyz3=xyz2.replace("}", "%7D");
		String xyz4=xyz3.replace("\"","%22");
		Log.i("Index",""+ xyz3.charAt(51));
		request.setURI(new URI(xyz4));
		respons=client.execute(request);
	
		
	}

	  catch(Exception e){
	    e.printStackTrace();   

	}

	    return null;
	}




	protected void onPreExecute(){
	    //dialog.dismiss();
	    Log.i("thread", "Started...2");
	    dialog.show();
	}



	protected void onPostExecute(JSONObject result){
	    Log.i("thread", "Done...2");
	    dialog.dismiss();
	    
	    try {
	    	BufferedReader inBuff=new BufferedReader(new InputStreamReader(respons.getEntity().getContent()));
	    	J2=inBuff.readLine();
	         //String TmpCoin=b+"@"+Coin;
	    	 Toast.makeText(getApplicationContext(), J2, Toast.LENGTH_LONG).show();
					//db1.insertMoney(Coin, Amt,Yint);
					
					JSONObject obj=new JSONObject(J2);
					String  s1=obj.getString("coin");
					String s2=obj.getString("cvalue");
					
					String xLines[]=s1.split("@");
					String cLines[]=s2.split("@");
					
					for(int i=0;i<xLines.length;i++)
						InsrtMoney(xLines[i],cLines[i]);
					
					db1.putHistory("Withdraw amount Rs "+sum);
					
					it =new Intent(getBaseContext(),MyBalance.class);
					it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(it);
					finish();
				
	    	

	    	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	   }

	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.make_payment, menu);
		return true;
	}
	
	

	
	
	
	
	 @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		 
		 
		 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MakePayment.this);
	      alertDialogBuilder.setMessage("Are you sure, To cancle this payment" );
	      alertDialogBuilder.setPositiveButton(R.string.yes_button, 
	      new DialogInterface.OnClickListener() {
			
	         @Override
	         public void onClick(DialogInterface arg0, int arg1) {
	        	 
	        	 it =new Intent(getBaseContext(),MyBalance.class);
				 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				 startActivity(it);
				 finish();
	           
				
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




	private void InsrtMoney(String a,String b) {
		 String lines[] = a.split("~");
			
			try{
					db1.insertMoney(a, lines[1],b);
					sum+=Integer.parseInt(lines[1]);
				
			} catch(Exception e){
				e.printStackTrace();
				
			}
	}

}
