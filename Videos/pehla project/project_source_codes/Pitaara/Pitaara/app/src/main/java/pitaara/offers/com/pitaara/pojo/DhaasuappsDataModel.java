package pitaara.offers.com.pitaara.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by piro on 3/1/16.
 */
public class DhaasuappsDataModel implements Serializable {


    ArrayList<Offer> topAppsFree;
    ArrayList<Offer> topAppsPaid;
    ArrayList<Offer> topGames;

    private static DhaasuappsDataModel dhaasuappsDataModel;

    // Private constructor prevents instantiation from other classes
    private DhaasuappsDataModel() { }

    public static DhaasuappsDataModel getInstance() {

        if(dhaasuappsDataModel == null) dhaasuappsDataModel = new DhaasuappsDataModel();
        return dhaasuappsDataModel;
    }


    public ArrayList<Offer> getTopAppsFree() {
        return topAppsFree;
    }

    public void setTopAppsFree(ArrayList<Offer> topAppsFree) {
        this.topAppsFree = topAppsFree;
    }

    public ArrayList<Offer> getTopAppsPaid() {
        return topAppsPaid;
    }

    public void setTopAppsPaid(ArrayList<Offer> topAppsPaid) {
        this.topAppsPaid = topAppsPaid;
    }

    public ArrayList<Offer> getTopGames() {
        return topGames;
    }

    public void setTopGames(ArrayList<Offer> topGames) {
        this.topGames = topGames;
    }
}
