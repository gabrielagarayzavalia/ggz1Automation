package tests;

import org.apache.commons.logging.impl.Log4JLogger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.StrangePage;

public class StrangeTest extends BaseTest {

	
	public StrangePage strangeListPageO;
	 //Log4JLogger log;

	 @Test (priority = 4)
	    public void tc01_orderStrangeList () throws Exception {
		 strangeListPageO = new StrangePage(driver, strangeURL  );
	    	Thread.sleep(500);
		 	
		 String itemDrag = "Mike plays the guitar";
		 String itemDrop = "Mike hides the mysterious girl in his house. Joyce gets a strange phone call.";
		 

		 strangeListPageO.DragAndDrop(itemDrag, itemDrop);
		 
		 Thread.sleep(500);
		//TODO: Assert item order
	 }
	
	    @Test (priority = 4)
	    public void tc02_SameAmountOfItemsInListAndInCounter () throws Exception {
			 strangeListPageO = new StrangePage(driver, strangeURL  );

			 Thread.sleep(5000);
			 if(driver.getTitle().contains("Stranger List")) {

		    	 int totalAumountListitems =	 strangeListPageO.countListItems();
		     	 int amountFromTitle = strangeListPageO.amountOnCounterTitle();
			 
		         Assert.assertEquals(totalAumountListitems, amountFromTitle);
			 }
			 else {
				 Assert.fail("Page is not displayed");
			 }
			 
	    }
	    

	    
	    @Test (priority = 1)
	    public void tc03_AddItemToTheList () throws Exception {
	    	strangeListPageO = new StrangePage(driver, strangeURL  );
	    	Thread.sleep(5000);
	    	
	    	int listSizeBase = strangeListPageO.countListItems();
	    	String addedItem = "This is test: TC03 - adding an item to list";
	    	String addedFile = "C:\\automationExs\\ggz01Automation\\src\\resources\\pictures\\test02-300x300.jpg";
	    	
	    	strangeListPageO.addItemToList(addedItem, addedFile);
	    	
	    	//Verify that Item was added in list
	    	Thread.sleep(5000);
	    	Assert.assertTrue(strangeListPageO.itemInList(addedItem));
	    	
	    	//Thread.sleep(15000);
	    //	System.out.println(listSizeBase + " = " +  strangeListPageO.countListItems());
	    	//Assert.assertEquals(listSizeBase, strangeListPageO.countListItems(), "Counter was not updated"); // needs refactor is countListItems() is retrieving old value
	    		    	
	    		       
	    }
	

	    
	    @Test (priority = 2)
	    public void tc04_UpdateItemTitleFromList () throws Exception {
	    	
	    	strangeListPageO = new StrangePage(driver, strangeURL  );
	    	
	    	Thread.sleep(5000);
	    	//adding a new item to Update - to keep existing list original items
	    	String newFile = "C:\\ggAutomation\\ggzAutomation01\\resourses\\pictures\\testing320x320.png";
	    	String toUpdateTextItem = "This is test: TC04";
	    	strangeListPageO.addItemToList(toUpdateTextItem, newFile);
	    	Thread.sleep(5000);
	    	
	    	//item details to update 
	    	String fileUpdate = "C:\\ggAutomation\\ggzAutomation01\\resourses\\pictures\\test02-300x300.jpg";
	    	String itemUpdateTexto = toUpdateTextItem + "-Updated Text";
	    	String itemUpdateTextoL = itemUpdateTexto; 

	    	    	
	    	//edit and update item in list
	    	strangeListPageO.editItem(toUpdateTextItem, itemUpdateTextoL,fileUpdate);
   	
	    	
	    	// Assert item was updated 
	    	Thread.sleep(5000);
	  		Assert.assertTrue(strangeListPageO.itemInList(itemUpdateTextoL), "Item text was not updated");
	  		
	    }
	
		
	    @Test (priority = 2)
	    public void tc05_DeleteItemFromList () throws Exception {
	    	
	    	strangeListPageO = new StrangePage(driver, strangeURL  );
	    	Thread.sleep(500);
	    	
	    	//adding a new item to Update - to keep existing list original items
	    	String delFile = "C:\\ggAutomation\\ggzAutomation01\\resourses\\pictures\\testing320x320.png";
	    	String itemToRemove = "This is test: TC05 ";
	    	strangeListPageO.addItemToList(itemToRemove, delFile);
	    	
	    	Thread.sleep(5000);
	    		    	
	    	
	     	//delete item from list
	    	strangeListPageO.itemDeleteAt(itemToRemove);
	    	
	    	Thread.sleep(5000);
	    	//verifies item is no longer in the list
	    	Assert.assertFalse(strangeListPageO.itemInList(itemToRemove), "Item was not deleted");
    		    	

	    }
	


	
	
	
}
