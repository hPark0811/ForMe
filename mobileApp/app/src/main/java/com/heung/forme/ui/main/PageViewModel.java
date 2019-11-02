package com.heung.forme.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.heung.forme.custom.recommendation.BankAccRec;
import com.heung.forme.custom.recommendation.CardRec;
import com.heung.forme.custom.recommendation.Recommendation;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    private LiveData<Recommendation> mRecommendation = Transformations.map(mIndex, new Function<Integer, Recommendation>() {
        @Override
        public Recommendation apply(Integer input) {
            Recommendation rec;
            switch (input){
                case 1:
                    rec = new CardRec("CUSTOMER_ID");
                    break;
                case 2:
                    rec = new BankAccRec("CUSTOMER_ID");
                    break;
                default:
                    rec = null;
            }
            return rec;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<Recommendation> getmRecommendation() {
        return mRecommendation;
    }


}