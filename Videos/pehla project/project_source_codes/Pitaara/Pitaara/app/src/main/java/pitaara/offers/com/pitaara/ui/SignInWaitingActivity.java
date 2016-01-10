package pitaara.offers.com.pitaara.ui;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pitaara.offers.com.pitaara.R;
import pitaara.offers.com.pitaara.extras.ServiceHandler;

public class SignInWaitingActivity extends Activity {
	private BroadcastReceiver mIntentReceiver;
	TextView timerTv;
	TextView mobNoVeryfyTv;

	private ProgressBar progressBar;
	static Boolean timeOut = true;
	String bodyy;
	String url4;
	SharedPreferences sharedPreferences;
	CountDownTimer countDownTimer;
	IntentFilter intentFilter;
	List<NameValuePair> params, params1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_sign_in_waiting);
		sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
		mobNoVeryfyTv = (TextView) findViewById(R.id.SW_MobNoVeryfyDesctxt);
		timerTv = (TextView) findViewById(R.id.SW_TimeRemainigTv);
		progressBar = (ProgressBar) findViewById(R.id.SW_progressBar);
		// show 30 second time count down
	countDownTimer=	new CountDownTimer(30000, 1000) {

			public void onTick(long millisUntilFinished) {
				timerTv.setText("Seconds Remaining : " + millisUntilFinished
						/ 1000);
			}

			public void onFinish() {

				timerTv.setText("Time Over");

				  Intent intent = new Intent(SignInWaitingActivity.this,UserConfirmation.class);
		    	  startActivity(intent);
		    	  finish();	
				
				// SignInWaitingActivity.this.finish();
			}
		}.start();

	}

	@Override
	protected void onResume() {
		super.onResume();

		try{
			intentFilter = new IntentFilter("SmsMessage.intent.MAIN");
			intentFilter.setPriority(999);
			mIntentReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
			
				Log.d("cccccccc", "cccccccc");
				String msg = intent.getStringExtra("get_msg");

				msg = msg.replace("\n", "");
				String body = msg.substring(msg.lastIndexOf(":") + 1,
						msg.length());

				bodyy= body.substring(body.indexOf("code") + 8,
						body.length());
				System.out.println("body----   " + bodyy);
				if (bodyy.length()>0) {

/*
url4 = "http://54.210.147.153:8062/OfferWorldWS/rest/getApps/verifyMobileUser/" + bodyy.trim() +"/" + sharedPreferences.getString("android_id", "yo");
*/
				Log.d("url4", "msg" + url4);

			    url4="http://192.168.1.3:8092/pitaara/piro_app/users//smsverifiaction";
                Log.d("sms_verification_execute","sms_verification_execute");
				new SmsVerification().execute();

				}
				 else {
					Log.d("bodyyy", "else of second");
					// if message is contains some invalide code
					 mobNoVeryfyTv.setText("Authentication Fails.");

					 Intent intentb = new Intent(SignInWaitingActivity.this,UserConfirmation.class);
			    	 countDownTimer.cancel();
					 startActivity(intentb);
			    	  finish();	
					
					// SignInWaitingActivity.this.finish();

				
				}
				
				
			}
		};
	
	} catch (Exception e) {
		// TODO: handle exception
	
		Toast.makeText(getBaseContext(), "catch is called", Toast.LENGTH_LONG).show();

		Intent intentbb= new Intent(SignInWaitingActivity.this,UserConfirmation.class);
    	 countDownTimer.cancel();
		 startActivity(intentbb);
    	  finish();
	}
		
		this.registerReceiver(mIntentReceiver, intentFilter);
		//this.re
	}

	@Override
	protected void onPause() {

		super.onPause();
		this.unregisterReceiver(this.mIntentReceiver);
	}
///////////////////	
	
	

	private class SmsVerification  extends AsyncTask<Void, Void, JSONObject> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
	//		pDialog = new ProgressDialog(MainActivity.this);
			//pDialog.setMessage("Please wait...");
			//pDialog.setCancelable(false);
			//pDialog.show();

		}

		@Override
		protected JSONObject doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			JSONObject jsonObj=null;
			params1 = new ArrayList<NameValuePair>();
			// Making a request to url and getting response
			params1.add(new BasicNameValuePair("device_id", "ddd"));
			params1.add(new BasicNameValuePair("sms_code", "1234"));
			params1.add(new BasicNameValuePair("msisdn","8010679779"));


			String jsonStr = sh.makeServiceCall(url4, ServiceHandler.POST,params1);

			Log.d("Response: ", "> " + jsonStr + "wwwwwww");

				try {
					 jsonObj = new JSONObject(jsonStr);
	
				} catch (JSONException e) {
					e.printStackTrace();
				}
       
			// Making a request to url and getting response
	
						

			return jsonObj;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);
///////////////////////////////////////////////////

			 SharedPreferences.Editor editor = sharedPreferences.edit();
			
			//pt.dismiss();
			try {
		   
				 String res=  result.get("result").toString();
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
				
					 Toast mytoast= Toast.makeText(getApplicationContext(), 
							 result.getString("response").toString(), 1);  
							    mytoast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);  // for center horizontal
							// mytoast.setGravity(Gravity.CENTER_VERTICAL);       // for center vertical 
							//  mytoast.setGravity(Gravity.TOP);                       // for top
							    mytoast.show(); 
					 
					 
				//	 Toast.makeText(getBaseContext(), result.getString("response").toString(),Toast.LENGTH_LONG).setGravity(Gravity.CENTER, 0, 0).;				 
			
					 Intent intent = new Intent(getApplicationContext(),DashBoardActivity.class);
					 countDownTimer.cancel();
					 
					 startActivity(intent);
			    	  finish();
				 
				 }
				 if(res.contentEquals("false"))
				 {
				
					 editor.putString("userExist", res);
						
					 editor.commit();
					 Toast.makeText(getBaseContext(), result.getString("response").toString(),Toast.LENGTH_LONG).show();				 
			
					 Intent intent = new Intent(getApplicationContext(),UserConfirmation.class);
			    	 countDownTimer.cancel() ;
					 startActivity(intent);
			    	  finish();
				 
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
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//////////////
}
