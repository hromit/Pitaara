package pitaara.offers.com.pitaara.callbacks;

import pitaara.offers.com.pitaara.pojo.DataModel;
import pitaara.offers.com.pitaara.pojo.SignUpResponseModel;
import pitaara.offers.com.pitaara.pojo.SignupModel;

/**
 * Created by piro on 3/1/16.
 */
public interface RegisterUserListener {

    public void onUserRegisterSuccess(SignUpResponseModel signUpResponseModel);

}
