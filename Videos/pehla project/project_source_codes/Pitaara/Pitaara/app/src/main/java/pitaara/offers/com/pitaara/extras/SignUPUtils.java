package pitaara.offers.com.pitaara.extras;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pitaara.offers.com.pitaara.json.Endpoints;
import pitaara.offers.com.pitaara.json.Parser;
import pitaara.offers.com.pitaara.json.RequestorData;
import pitaara.offers.com.pitaara.logging.L;
import pitaara.offers.com.pitaara.pojo.MyAccountDataModel;
import pitaara.offers.com.pitaara.pojo.SignUpResponseModel;
import pitaara.offers.com.pitaara.pojo.SignupModel;

/**
 * Created by piro on 3/1/16.
 */
public class SignUPUtils {

    public static void SignUPUser(RequestQueue requestQueue) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getRequestUrlSignUp(50),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

                        L.m("response" + response);

                        //DataModel dataModel = DataModel.getInstance();
                        //  dataModel.setStatus("sss");
                        try {
                            L.m("inside try");

                            JSONObject jsonResponse = new JSONObject(response);
                            SignUpResponseModel signUpResponseModel = Parser.parseSignUptJSON(jsonResponse);
                      


                            Log.d("inside signup user", "inside signup user:");

                        } catch (JSONException e) {

                            L.m("inside catch"); e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("sign up error","errot", error);
                        //Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("device_id","ddd");
                params.put("name","ddd");
                params.put("email","firoj@gmail.com");
                params.put("msisdn","8882405463");
                params.put("gender","male");
                params.put("dob","15021990");
             /*   params.put(KEY_PASSWORD,password);
                params.put(KEY_EMAIL, email);*/
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

/*
        JSONObject response = RequestorData.requestHomeDataJSON(requestQueue, Endpoints.getRequestUrlSignUp(50));
*/



        //  MyApplication.getWritableDatabase().insertMovies(DBMovies.BOX_OFFICE, listMovies, true);
    }





}
