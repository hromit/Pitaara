package pitaara.offers.com.pitaara.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piro on 3/1/16.
 */
public class TTSDataModel implements Serializable {


ArrayList<Offer> ttsList;
ArrayList<Offer> titDPGallery;

    private static TTSDataModel ttsDataModel;



    private TTSDataModel() { }


    public static TTSDataModel getInstance() {

        if(ttsDataModel == null) ttsDataModel = new TTSDataModel();
        return ttsDataModel;
    }


    public ArrayList<Offer> getTtsList() {
        return ttsList;
    }

    public void setTtsList(ArrayList<Offer> ttsList) {
        this.ttsList = ttsList;
    }

    public List<Offer> getTitDPGallery() {
        return titDPGallery;
    }

    public void setTitDPGallery(ArrayList<Offer> titDPGallery) {
        this.titDPGallery = titDPGallery;
    }
}
