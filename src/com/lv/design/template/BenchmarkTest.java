package com.lv.design.template;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BenchmarkTest {

	private static Logger LOG = LogManager.getLogger(BenchmarkTest.class);  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Benchmark operation = new MethodBenchmark();
		long duration = operation.repeat(50);
		LOG.info("The operation took " + duration + " milliseconds");
	}

}
