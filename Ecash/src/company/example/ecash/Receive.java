package company.example.ecash;



import javax.crypto.Cipher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class Receive extends BroadcastReceiver {
	
	
	Cipher cipher,cipher1;
    String encrypted,decrypted;
    int sum=0;
    MoneyWallet db1;
    byte [] encryptedBytes,decryptedBytes;

	 private static final String TAG = "Message recieved";

	 @Override
	 public void onReceive(Context context, Intent intent) {   
		
		 db1=new MoneyWallet(context); 
		 
	     Bundle pudsBundle = intent.getExtras();
	     Object[] pdus = (Object[]) pudsBundle.get("pdus");
	     SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);    
	     Log.i(TAG,  messages.getMessageBody());
	     Toast.makeText(context, "SMS Received ",Toast.LENGTH_LONG).show();
	     
	  
			decrypted=messages.getMessageBody();
			
			if(decrypted.charAt(0)=='*'){
						try {
							String tmp=Encryption.decipher("Ya",decrypted.substring(1, decrypted.length()));
							
							String xLines[]=tmp.split("@");
							
							for(int i=0;i<xLines.length;i++)
								InsrtMoney(xLines[i]);
					
							db1.putHistory("Money Rs "+sum+" recived from " +messages.getOriginatingAddress());
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
			
					
			}else Toast.makeText(context, "Invalid Format", Toast.LENGTH_LONG).show();
			
	 }

	
	 private void InsrtMoney(String a) {
		 String lines[] = a.split("\\r?\\n");
			
			try{
					db1.insertMoney(lines[0], lines[1],lines[2]);
					sum+=Integer.parseInt(lines[1]);
				
			} catch(Exception e){
				e.printStackTrace();
				
			}
	}
	 

	}