package pitaara.offers.com.pitaara.tasks;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import pitaara.offers.com.pitaara.callbacks.GetAllDADataLoadedListener;
import pitaara.offers.com.pitaara.callbacks.GetMyAccountDataLoadedListener;
import pitaara.offers.com.pitaara.extras.DADataUtils;
import pitaara.offers.com.pitaara.extras.MyAccountDataUtils;
import pitaara.offers.com.pitaara.network.VolleySingleton;
import pitaara.offers.com.pitaara.pojo.DhaasuappsDataModel;
import pitaara.offers.com.pitaara.pojo.MyAccountDataModel;

/**
 * Created by piro on 3/1/16.
 */
public class TaskLoadMyAccountData extends AsyncTask<Void,Void ,MyAccountDataModel> {

    private GetMyAccountDataLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;


    public TaskLoadMyAccountData(GetMyAccountDataLoadedListener myComponent)
    {
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }



    @Override
    protected MyAccountDataModel doInBackground(Void... voids) {


        MyAccountDataModel myAccountDataModel = MyAccountDataUtils.loadMyAccountData(requestQueue);

       /* String dataModel = HomeDataUtils.loadAllHomeData(requestQueue);*/

        return myAccountDataModel;
    }

    @Override
    protected void onPostExecute(MyAccountDataModel myAccountDataModel) {
        if (myComponent != null) {
            myComponent.onGetMyAccountDataLoaded(myAccountDataModel);
        }
    }
}

