package com.heung.forme.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.heung.forme.custom.recommendation.BankAccRec;
import com.heung.forme.custom.recommendation.CardRec;
import com.heung.forme.custom.recommendation.Recommendation;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private String userID;

    private LiveData<Recommendation> mRecommendation = Transformations.map(mIndex, input -> {
        Recommendation rec;
        switch (input){
            case 1:
                rec = new CardRec(this.userID);
                break;
            case 2:
                rec = new BankAccRec(this.userID);
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