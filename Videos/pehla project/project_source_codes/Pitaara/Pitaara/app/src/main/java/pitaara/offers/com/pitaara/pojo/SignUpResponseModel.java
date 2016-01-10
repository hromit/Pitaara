package pitaara.offers.com.pitaara.pojo;

import java.io.Serializable;

/**
 * Created by piro on 3/1/16.
 */
public class SignUpResponseModel implements Serializable {


    //   http://192.168.1.3:8092/pitaara/piro_app/users//userRegistration?device_id=dddd&name=hromit&email=fcd&msisdn=8010679779&gender=male&dob=10081989


    private static SignUpResponseModel signUpResponseModel;

    String temp_result;


    public SignUpResponseModel() {
    }


    public static SignUpResponseModel getInstance() {

        if (signUpResponseModel == null) signUpResponseModel = new SignUpResponseModel();
        return signUpResponseModel;
    }

    public String getTemp_result() {
        return temp_result;
    }

    public void setTemp_result(String temp_result) {
        this.temp_result = temp_result;
    }
}
