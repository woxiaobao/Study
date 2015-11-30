package com.lv.design.builder;

public class Test {
	public static void main(String[] args) {
		Computer lenoveComputer;
		ComputerBuilder lenoveComputerBuilder = new LenoveComputerBuilder();
		Director director;
		director = new Director(lenoveComputerBuilder);
		lenoveComputer = director.construct();
		System.out.println("lenoveComputer is made by:"
				+ lenoveComputer.getCpu() + lenoveComputer.getMemory()
				+ lenoveComputer.getMainboard());
	}

}
