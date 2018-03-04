package com.foodie.sf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.foodie.sf.helper.FoodTruckFinder;
import com.foodie.sf.util.FoodTruck;


/*** SfFoodRunner implements CommandLineRunner and is used for Command Line input processing from the user ***/

@Component
public class SfFoodRunner implements CommandLineRunner{

	@Autowired
	FoodTruckFinder foodTruckFinder;

	@Override
	public void run(String... args) throws Exception {

		try{
			System.out.println();
			System.out.println(">> Hungry? I can tell you what Food Trucks are open in San Francisco right now! >>");
			System.out.println(">> Press any key to continue, Q/q to Quit >>");

			/* Exit if user enters 'Q/q' */
			if("Q".equalsIgnoreCase(System.console().readLine())){
				return;
			}
			processUserInput();
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private void processUserInput() throws Exception{

		String userInput = null;

		System.out.println();
		System.out.println(">> Loading..");
		
		/* First get a list of all open Food trucks today using the Socrata API**/
		List<List<FoodTruck>> openFoodTrucks = foodTruckFinder.findOpenFoodTrucks();

		/* Exit with a message if no results found*/
		if(openFoodTrucks==null || openFoodTrucks.size()==0){
			System.out.println(">> Sorry. No food trucks open right now :( >>");
			System.exit(0);

		}else{ /* Display paged result, depending on user input till end of list is reached */
			int pageIndex=0;
			do{
				foodTruckFinder.displayPage(openFoodTrucks.get(pageIndex));
				pageIndex++;

				if(pageIndex!=openFoodTrucks.size()){
					System.out.println();
					System.out.println(">> Press [Enter] to view the next page, Q/q to quit >>");
				}
			} while ((pageIndex<openFoodTrucks.size()) 
					&& !("Q".equalsIgnoreCase(userInput=System.console().readLine())));
		}

	}

}
