package com.heung.forme.custom.recommendation;

public class CardRec implements Recommendation {
    @Override
    public void setCardRecommendation(CardType type) {

    }

    @Override
    public void setAccountRecommendation(BankAccType type) {}

    @Override
    public Recommendation getRecommendation() {
        return null;
    }
}
