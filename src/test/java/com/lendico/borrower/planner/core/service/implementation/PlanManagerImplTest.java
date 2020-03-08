package com.lendico.borrower.planner.core.service.implementation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendico.borrower.planner.api.dto.RepaymentDto;
import com.lendico.borrower.planner.core.service.implementation.PlanManagerImpl;
import com.lendico.borrower.planner.domain.Loan;

public class PlanManagerImplTest {

	PlanManagerImpl planManager;

	private Loan testLoan;
	
	public PlanManagerImplTest() {
		planManager = new PlanManagerImpl();
		
		try {
			testLoan = Loan.builder().duration(5).loanAmount(new BigDecimal(100)).nominalRate(5.0)
					.startDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse("2018-01-01T00:00:01Z")).build();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCaculateBorrowerPlan() {
		ObjectMapper mapper = new ObjectMapper();
		List<RepaymentDto> expectation = null;
		// Given
		String jsonString = "[{\"borrowerPaymentAmount\":\"20.25\",\"date\":\"2018-01-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"100.00\",\"interest\":\"0.42\",\"principal\":\"19.83\",\"remainingOutstandingPrincipal\":\"80.17\"},{\"borrowerPaymentAmount\":\"20.25\",\"date\":\"2018-02-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"80.17\",\"interest\":\"0.33\",\"principal\":\"19.92\",\"remainingOutstandingPrincipal\":\"60.25\"},{\"borrowerPaymentAmount\":\"20.25\",\"date\":\"2018-03-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"60.25\",\"interest\":\"0.25\",\"principal\":\"20.00\",\"remainingOutstandingPrincipal\":\"40.25\"},{\"borrowerPaymentAmount\":\"20.25\",\"date\":\"2018-04-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"40.25\",\"interest\":\"0.17\",\"principal\":\"20.08\",\"remainingOutstandingPrincipal\":\"20.17\"},{\"borrowerPaymentAmount\":\"20.25\",\"date\":\"2018-05-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"20.17\",\"interest\":\"0.08\",\"principal\":\"20.17\",\"remainingOutstandingPrincipal\":\"0.00\"}]";
		try {
			expectation = mapper.readValue(jsonString, new TypeReference<List<RepaymentDto>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// When
		
		// Then
		List<RepaymentDto> plan = planManager.caculateBorrowerPlan(testLoan);
		assertNotNull(plan);
		assertEquals(plan.size(), expectation.size());
	}

}
