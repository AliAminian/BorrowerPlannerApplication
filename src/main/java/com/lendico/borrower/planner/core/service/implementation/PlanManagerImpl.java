package com.lendico.borrower.planner.core.service.implementation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lendico.borrower.planner.api.dto.RepaymentDto;
import com.lendico.borrower.planner.core.service.PlanManager;
import com.lendico.borrower.planner.domain.Loan;
import com.lendico.borrower.planner.util.DateUtil;

@Service
public class PlanManagerImpl implements PlanManager{
	
	public List<RepaymentDto> caculateBorrowerPlan(Loan loanInfo) {
		List<RepaymentDto> plan = new ArrayList<>();
		
		int duration = loanInfo.getDuration();
		BigDecimal principal = loanInfo.getLoanAmount();
		BigDecimal annuity = null;
		double rate = loanInfo.getNominalRate();
		Date date = loanInfo.getStartDate();
		
		BigDecimal remainingOutstandingPrincipal = loanInfo.getLoanAmount();
		BigDecimal initialOutstandingPrincipal = loanInfo.getLoanAmount();
		for (int i = duration; i > 0; i--) {

			BigDecimal interest = this.calculateInterest(rate, initialOutstandingPrincipal);
			
			//for first installment we use formula (http://financeformulas.net/Annuity_Payment_Formula.html)
			if (i == duration) { 
				annuity = calculateFirstAnnuity(loanInfo.getLoanAmount(), loanInfo.getDuration(), loanInfo.getNominalRate());
				principal = this.calculatePrincipal(interest, annuity);
			} else {
				principal = this.calculatePrincipal(interest, annuity);
				annuity = this.calculateAnnuity(interest, principal);
			}
			
			// probably in last installment
			if (principal.subtract(initialOutstandingPrincipal)
					.setScale(2, RoundingMode.HALF_UP).doubleValue() > 0.00) {
				principal = initialOutstandingPrincipal;
				annuity = this.calculateAnnuity(interest, principal);
			}
			
			remainingOutstandingPrincipal = initialOutstandingPrincipal.subtract(principal).setScale(2, RoundingMode.HALF_UP);;
			
			RepaymentDto repayment = RepaymentDto.builder().date(date).interest(interest)
					.principal(principal).borrowerPaymentAmount(annuity)
					.remainingOutstandingPrincipal(remainingOutstandingPrincipal)
					.initialOutstandingPrincipal(initialOutstandingPrincipal).build();
			
			initialOutstandingPrincipal = remainingOutstandingPrincipal;
			date = DateUtil.nextMonth(date);
			
			plan.add(repayment);
		}

		return plan;
	}
	
	
	private BigDecimal calculateFirstAnnuity(BigDecimal presentValue, int numberOfPeriods, double rate) {
		//(http://financeformulas.net/Annuity_Payment_Formula.html)
		double ratePerMonth = rate / 12; //12 months in year
		BigDecimal numerator = presentValue.multiply(new BigDecimal(ratePerMonth));
		
		double tmp = Math.pow(1+ratePerMonth, -1 * numberOfPeriods);

		double res = numerator.doubleValue() / (1-tmp);
		return new BigDecimal(res).setScale(2, RoundingMode.HALF_UP);
	}
	
	private BigDecimal calculateAnnuity(BigDecimal interest, BigDecimal principal) {
		return interest.add(principal).setScale(2, RoundingMode.HALF_UP);
	}
	
	private BigDecimal calculatePrincipal(BigDecimal interest, BigDecimal annuity) {
		return annuity.subtract(interest).setScale(2, RoundingMode.HALF_UP);
	}
	
	private BigDecimal calculateInterest(double rate, BigDecimal principal) {
		return new BigDecimal((rate * DateUtil.DAY_IN_MONTH * principal.doubleValue()) / DateUtil.DAY_IN_YEAR)
				.setScale(2, RoundingMode.HALF_UP);
	}
}
