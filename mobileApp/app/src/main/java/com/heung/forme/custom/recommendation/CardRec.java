package com.heung.forme.custom.recommendation;

import com.heung.forme.R;

import java.util.ArrayList;
import java.util.List;

public class CardRec implements Recommendation {
    private String customerId;
    List<String> description;
    private String type;
    private int drawableImage;

    public CardRec(String customerId){
        this.customerId = customerId;
        generateRecommendation();
    }

    private void generateRecommendation() {
        //@TODO: Make REST call, and get recommendation back
        CardType type = CardType.INFINITE_CASH_BACK;
        description = new ArrayList<>();
        //@TODO: ADD DESCRPTION
        switch (type){
            case AEROPLAN:
                this.type = "TD Aeroplan Visa Infinite Card";
                drawableImage = R.drawable.aeroplan_visa_infinite_card;
                description.add("AEROPLAN");
                //add descriptions too
                break;
            case INFINITE_CASH_BACK:
                this.type = "TD Cash Back Visa Infinite Card";
                drawableImage = R.drawable.cash_back_infinite_visa_card;
                description.add("AEROPLAN");
                break;
            default:
                description.add("AEROPLAN");
                break;
        }
    }

    @Override
    public List<String> getDescription() {
        return description;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getDrawableImage() {
        return drawableImage;
    }


}
