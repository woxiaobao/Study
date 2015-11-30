package com.lv.design.factory;

public abstract class Factory {
	//运用了Java 中的泛型和反射技术 
    public abstract <T extends IProduct> T createProduct(Class<T> c); 

}
