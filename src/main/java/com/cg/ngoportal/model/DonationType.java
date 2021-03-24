package com.cg.ngoportal.model;

public enum DonationType {
    MONEY (1), CLOTHS(1000), BOOKS(100), EDIBLE(100), OTHER(500);
    private int val;

 

    private DonationType() {
    }

 

    private DonationType(int val) {
    	this.val = val;
        
    }

 

    public int getVal() {
        return val;
    }

 

}