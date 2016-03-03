package com.mikkysoft.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DbTable {
	private Map<String, Object> row;
	private List<Map<String, Object>> table;
	
	
	public DbTable() {
		row = new HashMap<>();
		table = new ArrayList<>();
		table.add(row);
	}
	
	public void put(String key,Object value){
		row.put(key, value);
	}
	
	public void next(){
		row = new HashMap<>();
		table.add(row);
	}
	
	public List<Map<String , Object>> getTable(){
		return table;
	}
	

}
