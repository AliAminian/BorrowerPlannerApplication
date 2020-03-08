package com.lendico.borrower.planner;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lendico.borrower.planner.BorrowerPlanApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowerPlanApplicationTests {

	@Test
	public void contextLoads() {
		BorrowerPlanApplication.main(Arrays.array());
	}

}
