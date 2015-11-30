package com.lv.design.builder;

public class IntelCPU extends CPU {

	@Override  
    public CPU getCPU() {  
        // TODO Auto-generated method stub  
        return new IntelCPU();  
    }  
      
     public String toString(){    
            return " IntelCPU ";    
        }

}
