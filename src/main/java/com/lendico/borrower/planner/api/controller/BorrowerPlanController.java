package com.lendico.borrower.planner.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lendico.borrower.planner.api.dto.RepaymentDto;
import com.lendico.borrower.planner.core.service.PlanManager;
import com.lendico.borrower.planner.domain.Loan;

@RestController
@RequestMapping(value = "/generate-plan")
public class BorrowerPlanController {

	@Autowired
	private PlanManager planManager;
	
	
	@PostMapping
    public ResponseEntity<List<RepaymentDto>> borrowerPlan(@Valid @RequestBody Loan loanDetail) {
		List<RepaymentDto> plan = planManager.caculateBorrowerPlan(loanDetail);
		return new ResponseEntity<List<RepaymentDto>>(plan, HttpStatus.OK);
    }
}
