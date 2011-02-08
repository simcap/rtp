package fr.rtp.utilities.chainconstructors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import org.junit.Test;

public class BankLoanTest {

    @Test
    public void test_first_constructor() throws Exception {
        Date expiry = new Date();
        float national = 678.09f, outstanding = 65.98f;
        int rating = 87;
        BankLoan bankLoan = new BankLoan(national, outstanding, rating, expiry);
        assertTrue(bankLoan.strategy instanceof TermROC);
        assertEquals(national, bankLoan.national, 0.0);
        assertEquals(outstanding, bankLoan.outstanding, 0.0);
        assertEquals(rating, bankLoan.rating);
        assertSame(expiry, bankLoan.expiry);
    }

    @Test
    public void test_second_constructor() throws Exception {
        Date expiry = new Date(), maturity = new Date();
        float national = 678.09f, outstanding = 65.98f;
        int rating = 87;
        BankLoan bankLoan = new BankLoan(national, outstanding, rating, expiry, maturity);
        assertTrue(bankLoan.strategy instanceof RevolvingTermROC);
        assertEquals(national, bankLoan.national, 0.0);
        assertEquals(outstanding, bankLoan.outstanding, 0.0);
        assertEquals(rating, bankLoan.rating);
        assertSame(expiry, bankLoan.expiry);
        assertSame(maturity, bankLoan.maturity);
    }

    @Test
    public void test_third_constructor() throws Exception {
        Date expiry = new Date(), maturity = new Date();
        float national = 678.09f, outstanding = 65.98f;
        int rating = 87;
        BankCapitalStrategy strategy = new DefaultBankStrategy();
        BankLoan bankLoan = new BankLoan(strategy, national, outstanding, rating, expiry, maturity);
        assertEquals(national, bankLoan.national, 0.0);
        assertEquals(outstanding, bankLoan.outstanding, 0.0);
        assertEquals(rating, bankLoan.rating);
        assertSame(expiry, bankLoan.expiry);
        assertSame(maturity, bankLoan.maturity);
        assertTrue(bankLoan.strategy instanceof DefaultBankStrategy);
    }
}
