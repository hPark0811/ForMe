package com.heung.forme.custom.recommendation;

import com.heung.forme.R;

import java.util.ArrayList;
import java.util.List;

public class CardRec implements Recommendation {
    List<String> description;
    private String type;
    private int drawableImage;

    public CardRec(String type){
        description = new ArrayList<>();
        switch (type){
            case "aeroplane_visa_infinite":
                this.type = "TD Aeroplan Visa Infinite Card";
                drawableImage = R.drawable.aeroplan_visa_infinite_card;
                description.add("Earn up to 40,000 Aeroplan Miles1 & a 1st year Annual Fee Rebate1 for the Primary Cardholder. Conditions Apply. Offer ends December 1, 2019.");
                description.add("$120 Annual Fee");
                description.add("19.99% Interest: Purchases");
                description.add("22.99% Interest: Cash Advances");
                break;
            case "cashback_infinite":
                this.type = "TD Cash Back Visa Infinite Card";
                drawableImage = R.drawable.cash_back_infinite_visa_card;
                description.add("Earn 10% Cash Back on all Purchases for the first 3 months5, up to a total spend of $2,000. Plus, no annual fees in the first year. Conditions apply. Offer ends December 1, 2019.");
                description.add("$120 Annual Fee");
                description.add("20.99% Interest: Purchases");
                description.add("22.99% Interest: Cash Advances");
                break;
            case "cashback":
                this.type = "TD Cash Back Visa Card";
                drawableImage = R.drawable.cash_back_visa;
                description.add("Earn Cash Back Dollars. Redeem them to help pay down your Account balance.");
                description.add("$0 Annual Fee");
                description.add("19.99% Interest: Purchasess");
                description.add("22.99% Interest: Cash Advances");
                break;
            case "rewards":
                this.type = "TD Rewards Visa Card";
                drawableImage = R.drawable.td_rewards_visa_card;
                description.add("Earn Cash Back Dollars. Redeem them to help pay down your Account balance.");
                description.add("$0 Annual Fee");
                description.add("19.99% Interest: Purchasess");
                description.add("22.99% Interest: Cash Advances");
                break;
            default:
                this.type = "TD Cash Back Visa Card";
                drawableImage = R.drawable.cash_back_visa;
                description.add("Earn Cash Back Dollars. Redeem them to help pay down your Account balance.");
                description.add("$0 Annual Fee");
                description.add("19.99% Interest: Purchasess");
                description.add("22.99% Interest: Cash Advances");
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
