package com.lendico.borrower.planner.domain;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lendico.borrower.planner.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

	@NotNull(message = "Please provide an amout")
	private BigDecimal loanAmount;
	
	@NotNull(message = "Please provide a nominal rate")
	private Double nominalRate;
	
	@NotNull(message = "Please provide a duration")
	private Integer duration;

	@NotNull(message = "Please provide a start date")
	private Date startDate;
	
	public void setNominalRate(double nominalRate) {
		this.nominalRate = nominalRate / 100.0;
	}
	
	public void setStartDate(String date) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(DateUtil.DateFormat);
		this.startDate = dateFormat.parse(date);
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = new BigDecimal(loanAmount).setScale(2);
	}
}
