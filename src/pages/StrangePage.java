package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StrangePage extends BasePage{
	
    public WebDriver driver;
    public WebDriverWait wait;


	
	
    public StrangePage(WebDriver driver, String strangeURL) throws Exception {
    	super(driver, strangeURL);
    	this.driver = driver;
	}
    
	public StrangePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}


	public static StrangePage visitPage(WebDriver driver, String strangeURL) throws Exception {
    	StrangePage page = new StrangePage(driver, strangeURL);
       // StrangePage.irAStrangerListPage(driver);
        Thread.sleep(10000);
        PageFactory.initElements(driver, page);
        return page;
    }


	
	
    //*********Page variables*********
   // public static String strangeURL = "http://immense-hollows-74271.herokuapp.com/";
	
    //********* web elements*********
    
        
    String titleListPage = "h1"; //page title - contains amount of items of the list	
    
    //elements related to adding a new item
    String inputAddImageNewItem = "#inputImage";   //image file input field of a new item
    String textboxNewItemDescription = "[name='text']";   // text field of a new item
    String buttonCreateItem = "button.btn.pull-right.btn-success";   //  Create button - on click adds item to list 
 
   //elements of the list
    String listStringItem = "li"; //each item of the list
    
   //elements of an item in a list
    String itemTituloXpath = "//P[@class='story ng-binding']"; //item description text - xpath
    String itemTituloTag = "p"; //item description text - tagname
    String itemEditButton = "//BUTTON[@type='button'][text()='Edit']";  //item edit button - xpath 
    
    //elements of an item available to edit
    String itemEditText = "//TEXTAREA[@rows='10']";  //edit text field in xpath by atribute rows
    String textEdit ="//TEXTAREA[@name='text']";	//edit text field in xpath by atribute name = text
    String textDetailEdit = "//*[@id='content']/div[2]/div/div/form/div[2]/div/textarea"; //edit text field by xpath 
    
    String itemEditImagen = "//*[@id='inputImage']";  // edit/change image
    String itemUpdateButtonX = "//BUTTON[@class='btn pull-right btn-primary'][text()='Update Item']"; //update botton by xpath
    

    // Add button WebElement
    @FindBy(css = "#inputImage") 
    private WebElement buttonAddImageNewItem;
    
    //Edit form WebElement
    @FindBy(xpath = "//DIV[@class='form-area col-md-3 ng-scope']")
    private WebElement editFormUpdateItem;
    
    

    String itemDeleteButton = "(//BUTTON[@type='button'][text()='Delete']"; //delete botton by xpath
    
    //List WebElement
    @FindBy(xpath = "//DIV[@class='col-md-9 ng-scope']/UL[@ui-sortable='strangerlist.sortableOptions']")
    private WebElement listStrange;
    
    //list by xpath 
    private String listStrangeString = "//DIV[@class='col-md-9 ng-scope']/UL[@ui-sortable='strangerlist.sortableOptions']";
    
    //list items - each item
	private List< WebElement> sList = listStrange.findElements(By.tagName(listStringItem));
    
    //*********Page Methods*********
	 
    //Navigate to StrangeList
    public static void irAStrangerListPage (WebDriver driver, String strangeURL){
		driver.get(strangeURL);   	
    }
    
  //Methods to manage items list
		    
		    //list size
		    public int countListItems() {
		         	return sList.size();
		    }
		    
		    /*Pick amount of items from the title page		    */
		    public int amountOnCounterTitle() {
		    	String pTitle = readText(By.cssSelector(titleListPage));  // complete title  
		    	String pTitleAmountItems = pTitle.substring(pTitle.indexOf("(")+1, pTitle.indexOf(")")); // get amount of items from title 
		    	return  Integer.parseInt(pTitleAmountItems);
		    }
		    
		  
			//Select item from the items list by description-text
			public WebElement itemListSelected (String itemDescription) throws Exception {
				
		    	int indx = itemListOrder(itemDescription); 
		    	
		    	System.out.println("indx = " + indx);
		    	WebElement resultItem = sList.get(indx);
    	
				return resultItem;
		    }
			
			//Search in the list if there is an item by text 
			//returns true if item exists in list else false
			public boolean itemInList(String itemTextDetail) {
				boolean result = false;
				
				WebElement sListRefreshed = listElement(By.xpath(listStrangeString));
				List< WebElement> sListItemsRefreshed = sListRefreshed.findElements(By.tagName(listStringItem));
				
				/*search item in list*/
				for(WebElement item : sListItemsRefreshed){
					//System.out.println(item.findElement(By.tagName(itemTituloTag)).getText() +" = "+itemTextDetail);
					//
					if(item.findElement(By.tagName(itemTituloTag)).getText().equals(itemTextDetail)) {
						result = true;
						return result;
					}
				}
				return result;
			}
			
			
			//Search in the list if there is an item by text 
			//returns a webelement
			public WebElement itemInListWebEl(String itemTextDetail) throws Exception {
				WebElement result = null;
				
				WebElement sListRefreshed = listElement(By.xpath(listStrangeString));
				List< WebElement> sListItemsRefreshed = sListRefreshed.findElements(By.tagName(listStringItem));
				
				/*search item in list*/
				for(WebElement item : sListItemsRefreshed){
					String textItem = item.findElement(By.tagName(itemTituloTag)).getText();
					Thread.sleep(500);
					if(textItem.equals(itemTextDetail.toString())) {
						result = item;
						System.out.println(result.getText());
						return result;
					}
				}
				return result;
			}
			
					
    
		    //Find an item index by text
			public int itemListOrder (String itemDescription) throws Exception {
		    	//WebElement resultItem = null;
				
				System.out.println("1: order = " + itemDescription);
		    	int resultItemIndex=0;
		    	boolean notFound=false;
		    	
		    	
				WebElement sListRefreshed = listElement(By.xpath(listStrangeString));
				List< WebElement> sListItemsRefreshed = sListRefreshed.findElements(By.tagName(listStringItem));
	
    			
		    	for(WebElement item : sListItemsRefreshed){
		    		//System.out.println("itemListOrder = " + resultItemIndex);

		    		Thread.sleep(500);
		    			String a = item.findElement(By.tagName(itemTituloTag)).getText();
			    			
		    			// System.out.println(a + " = " + itemDescription);
			    		if(a.equals(itemDescription)) {
			    			notFound=false;
			    			return resultItemIndex;
			    		} 		
			    			    		
		    			if(a.isEmpty()) {
				    			notFound=true;
				    			resultItemIndex = sListItemsRefreshed.size()+1;
				    			return resultItemIndex;
				    		}
			    			
		    			resultItemIndex++;
			    		}
   		
		    		System.out.println("resultItemIndex = " + resultItemIndex);
				return resultItemIndex;
		    }
			
			

    //Methods to add a new item
		    //add text to item
		    private void addItemText (String newText) {
		    	writeText(By.cssSelector(textboxNewItemDescription), newText);
		    }
		    
		    //add image file to item
		    private void addImageToItem (String imageFile) {
		    	//click(By.cssSelector(buttonAddImageNewItem));
		    	writeText(By.cssSelector(inputAddImageNewItem),imageFile);
		    }
		    
		    //add item - click
		    public void addItemToList (String itemTitle, String picturePath){   	
		    	addItemText(itemTitle);
		    	addImageToItem(picturePath);
		    	//insert item 
		        click(By.cssSelector(buttonCreateItem));
		    }
		    
		    
		    
    //Methods to edit an item
		    //update an item and save it
			public void editItem(String itemTitle, String newTitle, String newFilePath) throws Exception {
			    	//buscar item de la lista con el titulo buscado
					int itemIndex = itemListOrder(itemTitle);
					
					Thread.sleep(5000);
					
					//button click and button click action
					String buttonEdit = "//*[@id='content']/div[1]/div/ul/li[" + itemIndex  +"]/div/div/div[1]/button[text()='Edit']";  // "(//BUTTON[@type='button'][text()='Edit'][text()='Edit'])["+ indx + "]" ;

					Thread.sleep(5000);
					
					click(By.xpath(buttonEdit));
					Thread.sleep(5000);
					
			    	//update title by clearing field and adding new text
					writeText(By.xpath(textDetailEdit),Keys.chord( Keys.CONTROL, "a")); // clear field 
					Thread.sleep(5000);
					writeText(By.xpath(textDetailEdit),newTitle); // update text

					Thread.sleep(5000);
					
					//Add new image file
					writeText(By.xpath(itemEditImagen),newFilePath);
					Thread.sleep(5000);
	
					//click update button
					editFormUpdateItem.findElement(By.xpath(itemUpdateButtonX)).click();
					
					Thread.sleep(5000);
			}

    //Methods to delete an item
     
				//
			    public void itemDeleteAt(String itemName) throws Exception {
			    	
			    	int itemIndex = itemListOrder(itemName) ;
			    	Thread.sleep(500);

			    	String deleteButton = "//*[@id='content']/div[1]/div/ul/li[" + itemIndex  +"]/div/div/div[1]/button[text()='Delete']";  // "//BUTTON[@text()='Delete'][" +  itemIndex + "]";

			    	click(By.xpath(deleteButton));
			    	
			    	Thread.sleep(500);
			    	//confirm delete item
			    	click(By.xpath("//*[@id='top']/div[4]/div/div/div[3]/button[1]"));

			    //page refresh
			    	writeText(By.cssSelector(textboxNewItemDescription), Keys.chord(Keys.LEFT_CONTROL, Keys.F5));
			    		
			    }
			    
			    //drag and drop an item 
			    public void DragAndDrop (String itemDrag, String itemDrop) throws Exception {
			    	Thread.sleep(500);
					 Actions builder = new Actions(driver);
					 
					 WebElement toMove = itemListSelected(itemDrag);
					 System.out.println(toMove.getText());
					 Thread.sleep(500);
					 new Actions(driver).moveToElement(toMove).perform();
					 
					 Thread.sleep(500);
					 WebElement toRelease = itemListSelected(itemDrop);
					 System.out.println(toRelease.getText());
					 

					 Thread.sleep(500);
					 Action dragAndDrop = builder.clickAndHold(toMove)
					    .dragAndDrop(toMove, toRelease)
					    .build();
					 Thread.sleep(500);
					 dragAndDrop.perform();
			    	
			    }
    
    
    

	
	
	
	

}
