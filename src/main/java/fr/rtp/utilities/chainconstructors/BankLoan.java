package fr.rtp.utilities.chainconstructors;

import java.util.Date;

public class BankLoan {

    BankCapitalStrategy strategy;
    float national;
    float outstanding;
    int rating;
    Date expiry;
    Date maturity;

    public BankLoan(float national, float outstanding, int rating, Date expiry) {
        this.strategy = new TermROC();
        this.national = national;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
    }

    public BankLoan(float national, float outstanding, int rating, Date expiry, Date maturity) {
        this.strategy = new RevolvingTermROC();
        this.national = national;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
        this.maturity = maturity;
    }

    public BankLoan(BankCapitalStrategy strategy, float national, float outstanding, int rating, Date expiry, Date maturity) {
        this.strategy = strategy;
        this.national = national;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
        this.maturity = maturity;
    }
}
