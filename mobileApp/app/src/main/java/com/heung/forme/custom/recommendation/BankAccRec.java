package com.heung.forme.custom.recommendation;

import com.heung.forme.R;

import java.util.ArrayList;
import java.util.List;

public class BankAccRec implements Recommendation {
    private String customerId;
    List<String> description;
    private String type;
    private int drawableImage;

    public BankAccRec(String customerId){
        this.customerId = customerId;
        generateRecommendation();
    }

    private void generateRecommendation() {
        //@TODO: Make REST call, and get recommendation back
        BankAccType type = BankAccType.CHEQUING;
        description = new ArrayList<>();
        //@TODO: ADD DESCRPTION
        switch (type){
            case CHEQUING:
                this.type = "TD Unlimited Chequing Account";
                drawableImage = R.drawable.unlimited_chequing_acc;
                description.add("default!");
                //add descriptions too
                break;
            case SAVINGS:
                this.type = "TD Everyday Savings Account Card";
                drawableImage = R.drawable.everyday_savings_acc;
                description.add("default");
                break;
            default:
                description.add("default");
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
