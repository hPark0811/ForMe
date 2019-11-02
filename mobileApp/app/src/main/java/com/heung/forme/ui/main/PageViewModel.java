package com.heung.forme.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            String returnText;
            switch (input){
                case 1:
                    returnText = "11111111111111111111";
                    break;
                case 2:
                    returnText = "22222222222222222222";
                    break;
                default:
                    returnText = "WHO A U";
            }
            return returnText;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }


}