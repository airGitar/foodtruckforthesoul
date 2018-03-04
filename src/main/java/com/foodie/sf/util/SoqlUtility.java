package com.foodie.sf.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.socrata.model.soql.OrderByClause;
import com.socrata.model.soql.SortOrder;

@Component
public class SoqlUtility {

	/*Arbitrary max result set size to fetch all in a single API call*/
	public static final int PAGE_LIMIT = 10000;

	/*Mobile Food Schedule Datasource*/
	public static final String SF_FOODTRUCK_RESOURCE = "bbb8-hzi6"; 


	/* Sorting by multiple fields, since 'applicant' by itself is not unique in the Food Truck DataSet */
	public List<OrderByClause> getOrderByClauses(){

		List<OrderByClause> orderByClauses = new ArrayList<>();
		orderByClauses.add(new OrderByClause(SortOrder.Ascending, "applicant"));
		orderByClauses.add(new OrderByClause(SortOrder.Ascending, "location"));
		orderByClauses.add(new OrderByClause(SortOrder.Ascending, "dayorder"));
		orderByClauses.add(new OrderByClause(SortOrder.Ascending, "start24"));
		return orderByClauses;

	}

	/* List of fields to be fetched in the $select clause of the Socrata API */
	public List<String> getSelectPhrases(){

		return Collections.unmodifiableList(
				Arrays.asList("applicant","location","dayofweekstr","starttime","endtime","start24","end24"));
	}

}
