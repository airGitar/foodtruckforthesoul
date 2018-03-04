package com.foodie.sf.helper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.foodie.sf.util.FoodTruck;
import com.foodie.sf.util.SoqlUtility;
import com.google.common.collect.Lists;
import com.socrata.api.Soda2Consumer;
import com.socrata.builders.SoqlQueryBuilder;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.SoqlQuery;


/*** Helper class providing functions to fetch results with desired order and filter, and display them in paged form ***/

@Component
public class FoodTruckFinder{

	@Autowired
	SoqlUtility soqlUtility;

	@Value("${foodtrucks.page.size}")
	public int PAGE_SIZE;

	@Value("${socrata.sfgov.url}")
	public String SF_GOV_URL;


	/* Function to retrieve all relevant results from Socrata API in sorted form
	 * Uses Stream to filter relevant data
	 * Uses Guava to partition the result into 'pages' 
	 */
	public List<List<FoodTruck>> findOpenFoodTrucks() throws InterruptedException, SodaError{

		/* Socrata API configuration and query - fetches all results in sorted order */
		Soda2Consumer consumer = Soda2Consumer.newConsumer(SF_GOV_URL);	
		SoqlQuery openFoodTruckQuery = null;
		List<FoodTruck> allFoodTrucks = null;

		openFoodTruckQuery = new SoqlQueryBuilder()
				.addSelectPhrases(soqlUtility.getSelectPhrases())
				.setOffset(0)
				.setLimit(SoqlUtility.PAGE_LIMIT)
				.addOrderByPhrases(soqlUtility.getOrderByClauses())
				.build();

		allFoodTrucks = consumer.query(SoqlUtility.SF_FOODTRUCK_RESOURCE, openFoodTruckQuery, FoodTruck.LIST_DATATYPE);


		/* Ensure current datetime for comparison is San Francisco Time */
		ZonedDateTime todaySfDate = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

		/* Filter the previous result - to find trucks open at the current time*/
		List<FoodTruck> openFoodTrucks = allFoodTrucks.stream()
				.filter( ft->
				(ft.getStartHour()<ft.getEndHour())?
						todaySfDate.getHour()>= ft.getStartHour() && todaySfDate.getHour()<= ft.getEndHour():
							todaySfDate.getHour()>= ft.getStartHour() || todaySfDate.getHour()<= ft.getEndHour()
						)
				.filter( ft-> ft.getDayofweekstr().equalsIgnoreCase(todaySfDate.getDayOfWeek().toString()) )
				.collect(Collectors.toList()); 

		/* Guava function to partition the results into sublists of configurable size */
		return Lists.partition(openFoodTrucks, PAGE_SIZE);

	}

	/* Display a single page to console*/
	public void displayPage(List<FoodTruck> foodTruckPage){

		if(foodTruckPage!=null && foodTruckPage.size()>0){
			displayHeader();

			for(FoodTruck foodTruck:foodTruckPage){
				System.out.println(foodTruck.toString());

			}
		}

	}

	/* Header display for page */
	public void displayHeader(){
		StringJoiner sj = new StringJoiner("  		| 		 ");
		sj.add("NAME");
		sj.add("ADDRESS");
		System.out.println(sj);
	}

}

