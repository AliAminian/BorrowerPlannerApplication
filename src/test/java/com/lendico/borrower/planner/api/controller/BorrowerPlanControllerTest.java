package com.lendico.borrower.planner.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendico.borrower.planner.api.controller.BorrowerPlanController;
import com.lendico.borrower.planner.api.dto.RepaymentDto;
import com.lendico.borrower.planner.core.service.PlanManager;
import com.lendico.borrower.planner.domain.Loan;

@RunWith(SpringRunner.class)
@WebMvcTest(BorrowerPlanController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class BorrowerPlanControllerTest {

	public static final String URL_POST = "/generate-plan";
	private Loan testLoan;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private PlanManager planManager;

    @Autowired 
    private ObjectMapper mapper;
	
	public BorrowerPlanControllerTest() {
		try {
			testLoan = Loan.builder().duration(5).loanAmount(new BigDecimal(100)).nominalRate(5.0)
					.startDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse("2018-01-01T00:00:01Z")).build();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBorrowerPlan() throws Exception {

		// Given
		String json = mapper.writeValueAsString(testLoan);
		
		List<RepaymentDto> expectation = new ArrayList<>();
		RepaymentDto r1 = RepaymentDto.builder().date(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse("2018-01-01T00:00:01Z"))
				.borrowerPaymentAmount(new BigDecimal("33.61")).initialOutstandingPrincipal(new BigDecimal("100.00"))
				.interest(new BigDecimal("0.42")).principal(new BigDecimal("33.19"))
				.remainingOutstandingPrincipal(new BigDecimal("66.81")).build();
		
		RepaymentDto r2 = RepaymentDto.builder().date(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse("2018-02-01T00:00:01Z"))
				.borrowerPaymentAmount(new BigDecimal("33.61")).initialOutstandingPrincipal(new BigDecimal("66.81"))
				.interest(new BigDecimal("0.28")).principal(new BigDecimal("33.33"))
				.remainingOutstandingPrincipal(new BigDecimal("33.48")).build();
		
		RepaymentDto r3 = RepaymentDto.builder().date(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse("2018-03-01T00:00:01Z"))
				.borrowerPaymentAmount(new BigDecimal("33.61")).initialOutstandingPrincipal(new BigDecimal("33.48"))
				.interest(new BigDecimal("0.14")).principal(new BigDecimal("33.47"))
				.remainingOutstandingPrincipal(new BigDecimal("0.01")).build();
		
		expectation.add(r1);
		expectation.add(r2);
		expectation.add(r3);
		
		// When
		when(planManager.caculateBorrowerPlan(Mockito.any(Loan.class))).thenReturn(expectation);

		// Then
		mvc.perform(post(URL_POST).contentType(MediaType.APPLICATION_JSON_UTF8).content(json)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().json(mapper.writeValueAsString(planManager.caculateBorrowerPlan(testLoan))))
				.andDo(document("generate-plan"));
	}
	
	
	

}
