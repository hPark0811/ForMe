package com.heung.forme.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.heung.forme.custom.APIManager;
import com.heung.forme.custom.recommendation.BankAccRec;
import com.heung.forme.custom.recommendation.CardRec;
import com.heung.forme.custom.recommendation.Recommendation;

import java.util.Map;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private String userID;

    private LiveData<Recommendation> mRecommendation = Transformations.map(mIndex, input -> {
        String cardType;
        String bankType;
        try {
            Map<String, String> recMap = APIManager.getCustomerRecommendation(userID);
            cardType = recMap.get("creditCard");
            bankType = recMap.get("bankAccount");
            System.out.println("RESTCALL MADE!");
        } catch (Exception e) {
            cardType = "cashback_infinite";
            bankType = "chqueing_acc";
            System.out.println("RESTCALL ERROR!: " + e.getMessage());
        }

        Recommendation rec;
        switch (input){
            case 1:
                rec = new CardRec(cardType);
                break;
            case 2:
                rec = new BankAccRec(bankType);
                break;
            default:
                rec = null;
        }
        return rec;
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }
    public void setUserID(String userID) {this.userID = userID; }

    public LiveData<Recommendation> getmRecommendation() {
        return mRecommendation;
    }


}