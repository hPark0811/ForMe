package com.heung.forme.custom.recommendation;

import com.heung.forme.R;

import java.util.ArrayList;
import java.util.List;

public class BankAccRec implements Recommendation {
    List<String> description;
    private String type;
    private int drawableImage;

    public BankAccRec(String type){
        description = new ArrayList<>();
        switch (type){
            case "asdfasdfasfd":
                this.type = "TD Unlimited Chequing Account";
                drawableImage = R.drawable.unlimited_chequing_acc;
                description.add("default!");
                //add descriptions too
                break;
            case "Savings":
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
