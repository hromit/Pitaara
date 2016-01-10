package pitaara.offers.com.pitaara.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by piro on 3/1/16.
 */
public class MyAccountDataModel implements Serializable {



   VisitorModel useraccountdetails;

    private static MyAccountDataModel ttsDataModel;



    private MyAccountDataModel() { }


    public static MyAccountDataModel getInstance() {

        if(ttsDataModel == null) ttsDataModel = new MyAccountDataModel();
        return ttsDataModel;
    }

    public VisitorModel getUseraccountdetails() {
        return useraccountdetails;
    }

    public void setUseraccountdetails(VisitorModel useraccountdetails) {
        this.useraccountdetails = useraccountdetails;
    }
}
