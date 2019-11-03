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
    public static String cardType;
    public static String bankType;

    private LiveData<Recommendation> mRecommendation = Transformations.map(mIndex, input -> {
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

    public LiveData<Recommendation> getmRecommendation() {
        return mRecommendation;
    }


}