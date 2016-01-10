package pitaara.offers.com.pitaara.pojo;

import java.io.Serializable;

/**
 * Created by piro on 3/1/16.
 */
public class SignupModel implements Serializable {


 //   http://192.168.1.3:8092/pitaara/piro_app/users//userRegistration?device_id=dddd&name=hromit&email=fcd&msisdn=8010679779&gender=male&dob=10081989


    private static SignupModel signupModel;

    String device_id;
    String name;
    String email;
    String msisdn;
    String gender;
    String dob;


    private SignupModel() { }


    public static SignupModel getInstance() {

        if(signupModel == null) signupModel = new SignupModel();
        return signupModel;
    }

   /* public static SignupModel getSignupModel() {
        return signupModel;
    }

    public static void setSignupModel(SignupModel signupModel) {
        SignupModel.signupModel = signupModel;
    }*/

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
