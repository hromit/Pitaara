package pitaara.offers.com.pitaara.extras;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pitaara.offers.com.pitaara.MainActivity;
import pitaara.offers.com.pitaara.json.Endpoints;
import pitaara.offers.com.pitaara.json.Parser;
import pitaara.offers.com.pitaara.json.Requestor;
import pitaara.offers.com.pitaara.json.RequestorData;
import pitaara.offers.com.pitaara.logging.L;
import pitaara.offers.com.pitaara.pojo.DataModel;
import pitaara.offers.com.pitaara.pojo.MyAccountDataModel;

/**
 * Created by piro on 3/1/16.
 */
public class MyAccountDataUtils {

    public static MyAccountDataModel loadMyAccountData(RequestQueue requestQueue) {



        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getRequestUrlMYAccountData(50),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("device_id","ddd");
             /*   params.put(KEY_PASSWORD,password);
                params.put(KEY_EMAIL, email);*/
                return params;
            }

        };

        requestQueue.add(stringRequest);
        JSONObject response = RequestorData.requestHomeDataJSON(requestQueue, Endpoints.getRequestUrlMYAccountData(50));
        L.m("response" + response);
        MyAccountDataModel myAccountDataModel = null;
        //DataModel dataModel = DataModel.getInstance();
        //  dataModel.setStatus("sss");
        try {
            L.m("inside try");
            myAccountDataModel = Parser.parseMyAccountJSON(response);
        } catch (JSONException e) {

            L.m("inside catch"); e.printStackTrace();
        }
        Log.d("home data utils", "home data utils user type:" + myAccountDataModel.getUseraccountdetails().getDevice_id());

        //  MyApplication.getWritableDatabase().insertMovies(DBMovies.BOX_OFFICE, listMovies, true);
        return myAccountDataModel;
    }
}
