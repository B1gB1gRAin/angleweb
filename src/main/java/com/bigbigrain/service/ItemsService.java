package com.bigbigrain.service;

import java.util.List;

import com.bigbigrain.pojo.Items;

public interface ItemsService {

	
	public List<Items> list() throws Exception;
	
	public Items findItemsById(Integer id) throws Exception;
	
	public void updateItems(Items items) throws Exception;
}
