package com.cg.ngoportal.dao;

import com.cg.ngoportal.model.NeedyPeople;

public interface NeedyPeopleDao {
	public int createNeedyPerson(NeedyPeople person);
	public boolean readLoginData(NeedyPeople person);
	public boolean requestForHelp(NeedyPeople person);
}
