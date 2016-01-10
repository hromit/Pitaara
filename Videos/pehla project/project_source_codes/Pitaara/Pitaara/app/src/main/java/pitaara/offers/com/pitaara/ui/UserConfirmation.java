package pitaara.offers.com.pitaara.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;






import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pitaara.offers.com.pitaara.R;
import pitaara.offers.com.pitaara.extras.ServiceHandler;

public class UserConfirmation extends BaseActivity{


	
	private  String url4 ;
	private  String url5 ;

	EditText co;
    String cof;
	TextView resend;

	 SharedPreferences sharedPreferences;
	ProgressDialog pt,pt1;
	List<NameValuePair> params, params1;




	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_confirmation);
		  sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
		  SharedPreferences.Editor editor = sharedPreferences.edit(); 
		     editor.putString("is_mobile_verified", "0");
			 editor.commit(); 
		 Button cancel = (Button) findViewById(R.id.cu);
		 Button submit = (Button) findViewById(R.id.s);
		      co = (EditText) findViewById(R.id.c);
		  url5 = "http://192.168.1.3:8092/pitaara/piro_app/users//resend" ;
	Log.d("resend", "ss" + url5);
		      resend = (TextView) findViewById(R.id.r);
		
		      resend.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method 
		
						
						new resend().execute();
						
					}
				});
		        
		      
		 
		 submit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					//mob = mo.getText().toString().trim();
					cof = co.getText().toString().trim();
					url4 = "http://192.168.1.3:8092/pitaara/piro_app/users//smsverifiaction";
Log.d("url4", "msg" + url4);
					if(TextUtils.isEmpty(cof))
					{
					Toast.makeText(getApplicationContext(), "Enter Confirmation Code", Toast.LENGTH_LONG).show();
					}
					else
					{
					//	url4 = "http://10.64.0.30:8080/CandyAppsWS/rest/getApps/verifyMobileUser/" + co +"/"+mo+"/" + sharedPreferences.getString("android_id", "yo")+"/" +sharedPreferences.getString("fbid", "yo");

						new code().execute();	
						
					;
					}
				
				}
			});
		      cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
					//	 SharedPreferences.Editor editor = sharedPreferences.edit();
					//	 editor.putString("userExist", "false");
							
					//	 editor.commit();
						
						/*	Intent intent = new Intent(getApplicationContext(),Myprofile.class);
				    	  startActivity(intent);
				    //	  finish();*/
					}
				});
		        
		 
		 
		
		
	}

	
	
	
	
	
	 /**
	 * Async task class to get json by making HTTP call
	 * */
	public class code extends AsyncTask<Void, Void, JSONObject> {

		
        @Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pt = new ProgressDialog(UserConfirmation.this);
			pt.setMessage("Please wait...");
			pt.setCancelable(false);
			pt.show();

		}

		@Override
		protected JSONObject doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			JSONObject jsonObj=null;
			params1 = new ArrayList<NameValuePair>();
			// Making a request to url and getting response
			params1.add(new BasicNameValuePair("device_id", "ddd"));
			params1.add(new BasicNameValuePair("sms_code", cof));
			params1.add(new BasicNameValuePair("msisdn","8882405462"));


			String jsonStr = sh.makeServiceCall(url4, ServiceHandler.POST,params1);

			// Making a request to url and getting response

			Log.d("Response uc: ", "> " + jsonStr + "wwwwwww");

				try {
					 jsonObj = new JSONObject(jsonStr);
	
				} catch (JSONException e) {
					e.printStackTrace();
				}
		
			return jsonObj;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);

			 SharedPreferences.Editor editor = sharedPreferences.edit();
			
			pt.dismiss();
			try {
		   
				 String res=  result.get("flag").toString();
				 if(res.contentEquals("false"))
				 {
					 editor.putString("userExist", res);
					 editor.putString("is_mobile_verified", result.get("is_mobile_verified").toString());	
					 editor.commit(); 
			
					 
			Toast.makeText(getBaseContext(), "something went wrong..",Toast.LENGTH_LONG).show();		 
					 
			
				 }
				 if(res.contentEquals("true"))
				 {
				
					 editor.putString("userExist", res);
					 editor.putString("is_mobile_verified", result.get("is_mobile_verified").toString());	
	
					 editor.commit();
					 Toast.makeText(getBaseContext(), result.getString("response").toString(),Toast.LENGTH_LONG).show();				 
			
					 Intent intent = new Intent(getApplicationContext(),DashBoardActivity.class);
			    	  startActivity(intent);
			    	  finish();
				 
				 }
				
		
			
          
			} catch (Exception e) {
			//	Log.d("bbb", 	"bbxxxxxxxxxxxxcatch1");
				
				// TODO: handle exception

				}
				
	}		






	}		
	
	
	
	 /**
	 * Async task class to get json by making HTTP call
	 * */
	public class resend extends AsyncTask<Void, Void, JSONObject> {

		
       @Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pt1 = new ProgressDialog(UserConfirmation.this);
		pt1.setMessage("Please wait...");
			pt1.setCancelable(false);
			pt1.show();

		}

		@Override
		protected JSONObject doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			JSONObject jsonObj=null;
			params1 = new ArrayList<NameValuePair>();
			// Making a request to url and getting response
			params1.add(new BasicNameValuePair("device_id", "ddd"));
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url5, ServiceHandler.POST);
			sharedPreferences.getString("android_id", "yo");
			Log.d("Response: ", "> " + jsonStr + "wwwwwwwyyy");

				try {
					 jsonObj = new JSONObject(jsonStr);
	
				} catch (JSONException e) {
					e.printStackTrace();
				}
		
			return jsonObj;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);

		pt1.dismiss();
			
			//	if (pDialog.isShowing())
			//				pDialog.dismiss();
			try {
		   
		
				 if(result.get("flag").toString().contentEquals("false"))
				 {
					 
			
					 
			Toast.makeText(getBaseContext(), "something went wrong..",Toast.LENGTH_LONG).show();		 
					 
			
					 
				 }
				 if(result.get("flag").toString().contentEquals("true"))
				 {
				
		Toast.makeText(getBaseContext(), "Done",Toast.LENGTH_LONG).show();				 
			
					// Intent intent = new Intent(getApplicationContext(),MainActivityfragment.class);
			    	//  startActivity(intent);
				 
				 }
				
				
				
				
		
			
         
			} catch (Exception e) {
			//	Log.d("bbb", 	"bbxxxxxxxxxxxxcatch1");
				
				// TODO: handle exception

				}
				
	}		






	}
	
	 @Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
	//...userexist is zero flag is true and is mobileverified is zero		
	
		 /*
		 if(sharedPreferences.getBoolean("fflag", true) && sharedPreferences.getString("is_mobile_verified", "dd").contentEquals("1") )
         {
      	   Intent intent = new Intent(UserConfirmation.this,Myprofile.class);
         //    intent.addCategory(Intent.CATEGORY_HOME);
             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(intent);
         }
	
		 
		 //	 if(sharedPreferences.getBoolean("fflag", true) && sharedPreferences.getString("is_mobile_verified", "dd").contentEquals("0") )
         {
      	   Intent intent = new Intent(UserConfirmation.this,Myprofile.class);
         //    intent.addCategory(Intent.CATEGORY_HOME);
             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(intent);
         }
		 if(!sharedPreferences.getBoolean("fflag", true) && sharedPreferences.getString("is_mobile_verified", "dd").contentEquals("1") )
         {
      	   Intent intent = new Intent(UserConfirmation.this,Myprofile.class);
         //    intent.addCategory(Intent.CATEGORY_HOME);
             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(intent);
         }
		
		 
		 
		 if(!sharedPreferences.getBoolean("fflag", true) && sharedPreferences.getString("is_mobile_verified", "dd").contentEquals("0"))
			  new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
	          .setMessage("Do you really want to exit?")
	          .setPositiveButton("yes", new DialogInterface.OnClickListener() {
	              @Override
	              public void onClick(DialogInterface dialog, int which) {

	            	  Intent intent = new Intent(Intent.ACTION_MAIN);
	            	  intent.addCategory(Intent.CATEGORY_HOME);
	            	  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            	  startActivity(intent);
	                  
	              }
	          }).setNegativeButton("no", null).show();
	*/
		}
	
	
}		
	
	
	
	
	
	
	
	
	

