package fr.rtp.creation.creationmethods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Date;

import org.junit.Test;


public class LoanTest {
    
    @Test
    public void test_first_loan_constructor() throws Exception {
        int riskRating = 30; double commitment = 45.9;
        Date maturity = new Date();
        Loan loan = new Loan(commitment, riskRating , maturity);
        assertEquals(riskRating, loan.riskRating);
        assertEquals(commitment, loan.commitment, 0);
        assertSame(maturity, loan.maturity);
    }
    
    @Test
    public void test_second_loan_constructor() throws Exception {
        int riskRating = 30; double commitment = 45.9;
        Date maturity = new Date(),  expiry = new Date();
        Loan loan = new Loan(commitment, riskRating , maturity, expiry );
        assertEquals(riskRating, loan.riskRating);
        assertEquals(commitment, loan.commitment, 0);
        assertSame(maturity, loan.maturity);
        assertSame(expiry, loan.expiry);
    }
    
    @Test
    public void test_third_loan_constructor() throws Exception {
        int riskRating = 30; double commitment = 45.9, outstanding = 3.7;
        Date maturity = new Date(),  expiry = new Date();
        Loan loan = new Loan(commitment, outstanding, riskRating , maturity, expiry );
        assertEquals(riskRating, loan.riskRating);
        assertEquals(commitment, loan.commitment, 0);
        assertEquals(outstanding, loan.outstanding, 0);
        assertSame(maturity, loan.maturity);
        assertSame(expiry, loan.expiry);
    }
    
    @Test
    public void test_fourth_loan_constructor() throws Exception {
        int riskRating = 30; double commitment = 45.9;
        Date maturity = new Date(),  expiry = new Date();
        CapitalStrategy strategy = new CapitalStrategy();
        Loan loan = new Loan(strategy , commitment, riskRating , maturity, expiry );
        assertSame(strategy, loan.capitalStrategy);
        assertEquals(riskRating, loan.riskRating);
        assertEquals(commitment, loan.commitment, 0);
        assertEquals(0.00, loan.outstanding, 0);
        assertSame(maturity, loan.maturity);
        assertSame(expiry, loan.expiry);
    }

    @Test
    public void test_fifth_loan_constructor() throws Exception {
        int riskRating = 30; double commitment = 45.9, outstanding = 2.87;
        Date maturity = new Date(),  expiry = new Date();
        CapitalStrategy strategy = new CapitalStrategy();
        Loan loan = new Loan(strategy , commitment, outstanding, riskRating , maturity, expiry );
        assertSame(strategy, loan.capitalStrategy);
        assertEquals(riskRating, loan.riskRating);
        assertEquals(commitment, loan.commitment, 0);
        assertEquals(2.87, loan.outstanding, 0);
        assertSame(maturity, loan.maturity);
        assertSame(expiry, loan.expiry);
    }
}
