package com.lv.design.builder;

public class KingstonMemory extends Memory {
	@Override  
    public Memory getMemory() {  
        return new KingstonMemory();  
    }  
      
     public String toString(){    
            return " KingstonMemory ";    
        } 

}
