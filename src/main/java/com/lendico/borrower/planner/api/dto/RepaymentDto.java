package com.lendico.borrower.planner.api.dto;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lendico.borrower.planner.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentDto {
	private BigDecimal borrowerPaymentAmount;
	private Date date;
	private BigDecimal initialOutstandingPrincipal;
	private BigDecimal interest;
	private BigDecimal principal;
	private BigDecimal remainingOutstandingPrincipal;

	public String getBorrowerPaymentAmount() {
		return borrowerPaymentAmount.toString();
	}

	public String getDate() {
		DateFormat format = new SimpleDateFormat(DateUtil.DateFormat);
		return format.format(date);
	}

	public String getInitialOutstandingPrincipal() {
		return initialOutstandingPrincipal.toString();
	}

	public String getInterest() {
		return interest.toString();
	}

	public String getPrincipal() {
		return principal.toString();
	}

	public String getRemainingOutstandingPrincipal() {
		return remainingOutstandingPrincipal.toString();
	}
	
	
	
}
