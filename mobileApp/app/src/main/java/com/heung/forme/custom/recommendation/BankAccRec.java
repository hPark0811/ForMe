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
            case "chequeing_account":
                this.type = "TD Unlimited Chequing Account";
                drawableImage = R.drawable.unlimited_chequing_acc;
                description.add("Peace of mind that comes with unlimited transactions");
                description.add("Unlimited transactions");
                description.add("No TD fee on any ATM in Canada");
                description.add("Free  Interac e-Transfer® transactions");
                description.add("$15.95 Monthly Fee");
                break;
            case "saving_account":
                this.type = "TD Everyday Savings Account Card";
                drawableImage = R.drawable.everyday_savings_acc;
                description.add("Ideal if you’re starting to save or want frequent access to your funds");
                description.add("$0 Monthly Fee");
                description.add("Every dollar earns interest calculated daily");
                description.add("1 transaction1 per month");
                break;
            default:
                this.type = "TD Unlimited Chequing Account";
                drawableImage = R.drawable.unlimited_chequing_acc;
                description.add("Peace of mind that comes with unlimited transactions");
                description.add("Unlimited transactions");
                description.add("No TD fee on any ATM in Canada");
                description.add("Free  Interac e-Transfer® transactions");
                description.add("$15.95 Monthly Fee");
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
