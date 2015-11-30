package com.lv.design.builder;

public class LenoveComputerBuilder implements ComputerBuilder {
	private Computer lenoveComputer = null;

	public LenoveComputerBuilder() {
		lenoveComputer = new Computer();
	}

	@Override
	public void buildCPU() {
		lenoveComputer.setCpu(new IntelCPU());
	}

	@Override
	public void buildMemory() {
		lenoveComputer.setMemory(new KingstonMemory());
	}

	@Override
	public void buildMainboard() {
		lenoveComputer.setMainboard(new AsusMainboard());
	}

	@Override
	public Computer getComputer() {
		buildCPU();
		buildMemory();
		buildMainboard();
		return lenoveComputer;
	}

}