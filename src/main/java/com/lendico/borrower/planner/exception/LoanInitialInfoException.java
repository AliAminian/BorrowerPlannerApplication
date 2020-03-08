package com.lendico.borrower.planner.exception;

public class LoanInitialInfoException  extends RuntimeException {
	public LoanInitialInfoException(String isbn) {
        super("Mandotry fields for borrower plan are  duration, nominal rate, loan amount, Date of Disbursement/Payout");
    }
}
