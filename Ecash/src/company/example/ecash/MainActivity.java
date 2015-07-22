package company.example.ecash;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

	EditText  username=null,password=null;
	Button login;
	CheckBox chkB;
	Boolean f=false;
	SharedPreferences shf;
	Intent it;
	String Uname="112",Pass="123";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		ActionBar ab=getActionBar();
		ab.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
		//ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33B5E5")));
		//ab.hide();
		
		username = (EditText)findViewById(R.id.mobilno);
		password = (EditText)findViewById(R.id.WithDrawAmt);  
		login = (Button)findViewById(R.id.WtDraSub);
		chkB=(CheckBox)findViewById(R.id.checkBox1);
		
		shf = getSharedPreferences("MyTmp", Context.MODE_PRIVATE);
		
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Toast.makeText(getApplicationContext(), "Pressed"+"***"+chkB.isChecked(), Toast.LENGTH_SHORT).show();
				
//				 it =new Intent(getBaseContext(),MyBalance.class);
//				 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				 startActivity(it);
				
				
				if(chkB.isChecked())
					new login().execute("");
				
				else if(!shf.contains("m")){
						Toast.makeText(getBaseContext(),"It is your first login" ,Toast.LENGTH_SHORT).show();
						
					}
					
				else{			
					
					SharedPreferences prefs = getSharedPreferences("Ecash_Password_File", MODE_PRIVATE); 
				    String restoredText = prefs.getString("Uname", null);
				    
				     if (restoredText != null) {
				          Uname = prefs.getString("Uname", "112");
				          Pass = prefs.getString("Pass", "123"); 
				          StaticData.setID(Uname);
				          StaticData.setPass(Pass);
				     	}
				     
				     
				    // Toast.makeText(getApplicationContext(), Uname+"***"+Pass, Toast.LENGTH_LONG).show();
					if(password.getText().toString().equals(Pass)&&
							username.getText().toString().equals(Uname)){
						
						 Toast.makeText(getApplicationContext(), "Authenticated", Toast.LENGTH_LONG).show();
						  it =new Intent(getBaseContext(),MyBalance.class);
						 it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						 startActivity(it);
						 finish();
					}
					
					
					
				}
				 
				 
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	//@SuppressWarnings("unused")
	private class login extends AsyncTask<String, Void, JSONObject>{
		HttpResponse respons;

	ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "", "Authenticating, Please wait...");

	@Override
	protected JSONObject doInBackground(String... params) {
	    Log.i("thread", "Doing Something...");
	   //authentication operation
	try{

	   
		HttpClient client=new DefaultHttpClient();
		HttpPost request = new HttpPost();
		StaticData.setID(username.getText().toString());
		
//		request.setURI(new URI("http://"+StaticData.getIp()+"/ecash/signin.php?accountno="
//		+username.getText().toString()+"&password="+password.getText().toString()));
//		
		  request.setURI(new URI("http://"+StaticData.getIp()+"/signin.php?"));				
		
		   List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
	        nameValuePair.add(new BasicNameValuePair("accountno",username.getText().toString()));
	        nameValuePair.add(new BasicNameValuePair("password",password.getText().toString()));
	        
	        // Url Encoding the POST parameters
	        try {
	        	request.setEntity(new UrlEncodedFormEntity(nameValuePair));
	        } catch (UnsupportedEncodingException e) {
	            // writing error to Log
	            e.printStackTrace();
	        }  
	        
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
	    	String RcvData=inBuff.readLine();
	    	 String lines[] = RcvData.split("~");
	    	 Toast.makeText(getApplicationContext(), RcvData, Toast.LENGTH_LONG).show();
	    	 
	    	 if(lines[0].equals("true")){
	    			
	    			StaticData.setM(Integer.parseInt(lines[1]));
	    		
	    			
	    			
	    			Toast.makeText(getApplicationContext(), "Authenticated", Toast.LENGTH_LONG).show();
	    			
	    			 SharedPreferences.Editor editor = getSharedPreferences("Ecash_Password_File", MODE_PRIVATE).edit();
	    			 editor.putString("Uname", username.getText().toString());
	    			 editor.putString("Pass", password.getText().toString());
	    			 editor.commit();
	    			 
	    			 
	    			 SharedPreferences.Editor editor2 = getSharedPreferences("MyTmp", MODE_PRIVATE).edit();
	    			 editor2.putString("m",lines[1]);   
	    			 editor2.putString("email",lines[2]); 
	    			 editor2.putString("name",lines[3]);   
	    			 editor2.putString("accno",lines[4]);   
	    			 editor2.commit();
	    			
					Intent it =new Intent(getBaseContext(),MyBalance.class);
					it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(it);
					finish();
	    	 }
	    	
	    	 else  	Toast.makeText(getApplicationContext(), "Not Authenticated try again", Toast.LENGTH_LONG).show();
	    	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	   }

	  }
	
	
	@Override
	public void onBackPressed() {
	    // do nothing.
	}

	

}
