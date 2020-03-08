package com.lendico.borrower.planner.core.service;

import java.util.List;

import com.lendico.borrower.planner.api.dto.RepaymentDto;
import com.lendico.borrower.planner.domain.Loan;

public interface PlanManager {
	/**
     * calculate plan of repayments for a loan with specified information
     *
     * @param loanInfo containing information of requested data for planning
     *  event published using {@link PlanManager#caculateBorrowerPlan(Loan)}
     *  
     * @return return list of installment with format of {@link RepaymentDto}
     */
	public List<RepaymentDto> caculateBorrowerPlan(Loan loanInfo);
}
