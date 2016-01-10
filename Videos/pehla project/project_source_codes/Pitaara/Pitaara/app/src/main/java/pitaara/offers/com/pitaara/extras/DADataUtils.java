package pitaara.offers.com.pitaara.extras;

import android.util.Log;

import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import pitaara.offers.com.pitaara.json.Endpoints;
import pitaara.offers.com.pitaara.json.Parser;
import pitaara.offers.com.pitaara.json.RequestorData;
import pitaara.offers.com.pitaara.logging.L;
import pitaara.offers.com.pitaara.pojo.DataModel;
import pitaara.offers.com.pitaara.pojo.DhaasuappsDataModel;

/**
 * Created by piro on 3/1/16.
 */
public class DADataUtils {


    public static DhaasuappsDataModel loadAllDAData(RequestQueue requestQueue) {
        JSONObject response = RequestorData.requestHomeDataJSON(requestQueue, Endpoints.getRequestUrlDAData(50));
        L.m("response" + response);
        DhaasuappsDataModel dhaasuappsDataModel = null;
        //DataModel dataModel = DataModel.getInstance();
        //  dataModel.setStatus("sss");
        try {
            L.m("inside try");
            dhaasuappsDataModel = Parser.parseDhaasuAppsJSON(response);
        } catch (JSONException e) {

            L.m("inside catch"); e.printStackTrace();
        }
      ///  Log.d("home data utils", "home data utils user type:" + dhaasuappsDataModel.getTopAppsFree().get(0).getOfferId());

        //  MyApplication.getWritableDatabase().insertMovies(DBMovies.BOX_OFFICE, listMovies, true);
        return dhaasuappsDataModel;
    }
}
