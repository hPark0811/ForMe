package com.heung.forme.custom.recommendation;

public interface Recommendation {
    void setCardRecommendation(CardType type);
    void setAccountRecommendation(BankAccType type);
    Recommendation getRecommendation();

}
