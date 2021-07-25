import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class TC10_DemoCartProject {
	WebDriver driver;
	HSSFSheet sheet2,sheet3,sheet4;
	String name,lname,email,mobile,pword,pwordcnf;
	Double number;
	HSSFWorkbook wb,wb1;
	
	public void takeScreeenShot(String filepath) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		
		File src = screenshot.getScreenshotAs(OutputType.FILE); 
				
		File dest = new File(filepath);
		
		FileHandler.copy(src, dest);
	}
	
	public void getExcelData(String filepath) throws IOException {
		File file = new File(filepath);
		
		FileInputStream fis = new FileInputStream(file);
		
		 wb = new HSSFWorkbook(fis);
	}
	 public void sendExcelData(String filepath) throws FileNotFoundException {
		 File file = new File(filepath);
		 
		 FileOutputStream fos = new FileOutputStream(file);
		 
	 }
	
  @BeforeSuite
  public void openBrowser() {
	  System.setProperty("webdriver.chrome.driver", "D:\\Testing Batch Software\\chromedriver.exe");
	  driver = new ChromeDriver();
  }
  
//  @Ignore
  @Test(priority = 1)
  public void openSite() throws InterruptedException {
	  driver.get("https://demo.opencart.com/");
	  Thread.sleep(2000);
  }
  
//  @Ignore
  @Test(priority = 2)
  public void createAccount() throws IOException, InterruptedException {
	  //validation pending
	  driver.manage().window().maximize();
	  
	  driver.findElement(By.xpath("//a[@title='My Account']")).click();
	  
	  driver.findElement(By.linkText("Register")).click();
	 
	  getExcelData("C:\\Users\\TA20127558\\Desktop\\project.xls");
	  
	  sheet2 = wb.getSheetAt(1);
	  
	   name = sheet2.getRow(0).getCell(0).getStringCellValue();
	  
	   lname = sheet2.getRow(1).getCell(0).getStringCellValue();
	  
	   email = sheet2.getRow(2).getCell(0).getStringCellValue();
	  
	   number = sheet2.getRow(3).getCell(0).getNumericCellValue();
	   mobile = String.valueOf(number);
	  
	   pword = sheet2.getRow(4).getCell(0).getStringCellValue();
	  
	   pwordcnf = sheet2.getRow(5).getCell(0).getStringCellValue();
	  
	  driver.findElement(By.name("firstname")).sendKeys(name);
	  driver.findElement(By.name("lastname")).sendKeys(lname);
	  driver.findElement(By.name("email")).sendKeys(email);
	  driver.findElement(By.name("telephone")).sendKeys(mobile);
	  driver.findElement(By.name("password")).sendKeys(pword);
	  driver.findElement(By.name("confirm")).sendKeys(pwordcnf);
	  
	  driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']")).click();
	  
	  takeScreeenShot("C:\\Users\\TA20127558\\Desktop\\project_screenshots\\1registrationform.png");
	  
	  driver.findElement(By.xpath("//input[@type='submit' and @value='Continue']")).click();
	  
	  takeScreeenShot("C:\\Users\\TA20127558\\Desktop\\project_screenshots\\2registrationformdone.png");
	  
	  Thread.sleep(2000);
	  
  }
  
  @Test(priority = 3)
//  @Ignore
  public void contactUs() throws InterruptedException, IOException {
	  //driver.get("https://demo.opencart.com/index.php?route=information/contact");
	  driver.findElement(By.linkText("Contact Us")).click();
	  //driver.findElement(By.name("name")).sendKeys("afsffds");
	  //driver.findElement(By.name("email")).sendKeys("dfsdf@ggddg.dgdg");
	  driver.findElement(By.xpath("//textarea[@name = 'enquiry']")).sendKeys("This is to Change of Address/Phone number");
	  
	  takeScreeenShot("C:\\Users\\TA20127558\\Desktop\\project_screenshots\\3contactform.png");
	  
	  driver.findElement(By.xpath("//input[@type = 'submit' and @value='Submit']")).click();
	  
	  takeScreeenShot("C:\\Users\\TA20127558\\Desktop\\project_screenshots\\4contactformdone.png");
	  
	  driver.findElement(By.linkText("Continue")).click();
	  driver.findElement(By.xpath("//*[@id='slideshow0']")).click();
	//*[@id="slideshow0"]/div/div[3]
	  Thread.sleep(2000);
  }
  
//  @Ignore
  @Test (priority = 4)
  public void giveReview() throws IOException, InterruptedException {
	 //driver.get("https://demo.opencart.com/index.php?route=product/product&path=57&product_id=49");
	  driver.findElement(By.linkText("Write a review")).click();

	  getExcelData("C:\\Users\\TA20127558\\Desktop\\project.xls");
	  sheet3 = wb.getSheetAt(2);
	  sheet2 = wb.getSheetAt(1);
	  String review = sheet3.getRow(1).getCell(0).getStringCellValue();
	  String review2 = sheet3.getRow(2).getCell(0).getStringCellValue();
	  String name2 = sheet2.getRow(0).getCell(0).getStringCellValue();
	  int rating = (int) sheet3.getRow(3).getCell(0).getNumericCellValue();
	  String rate = String.valueOf(rating);
	  
	  driver.findElement(By.id("input-review")).sendKeys(review);
	  
	  switch(rating) {
	  case 1:
		  driver.findElement(By.xpath("//input[@type='radio' and @value = '1']")).click();
		  break;
	  case 2:
		  driver.findElement(By.xpath("//input[@type='radio' and @value = '2']")).click();
		  break;
	  case 3:
		  driver.findElement(By.xpath("//input[@type='radio' and @value = '3']")).click();
		  break;
	  case 4:
		  driver.findElement(By.xpath("//input[@type='radio' and @value = '4']")).click();
		  break;
	  case 5:
		  driver.findElement(By.xpath("//input[@type='radio' and @value = '5']")).click();
		  break;
		  
	  }
	  driver.findElement(By.id("button-review")).click();
	  
	 // Thread.sleep(2000);
	  
	  driver.findElement(By.id("input-name")).sendKeys(name2);
	  
	  driver.findElement(By.id("input-review")).sendKeys(review2);
	  
	  Thread.sleep(2000);
	  
	  driver.findElement(By.id("button-review")).click();
	  
	  Thread.sleep(2000);
	  
  }
  
//  @Ignore
  @Test (priority = 5)
  public void addToWishList() throws InterruptedException, IOException {
	  //driver.get("https://demo.opencart.com/index.php?route=product/product&path=57&product_id=49");
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//button[@data-original-title='Add to Wish List']")).click();
	  driver.findElement(By.id("wishlist-total")).click();
	 
	  takeScreeenShot("C:\\Users\\TA20127558\\Desktop\\project_screenshots\\5wishlist.png");
	  
	  Thread.sleep(2000);
  }
  
//  @Ignore
  @Test (priority = 6)
  public void currencySelect() throws IOException, InterruptedException {
	  String val;
	  FileOutputStream fos,fos2;
	  
	 //driver.get("https://demo.opencart.com/index.php?route=product/product&path=57&product_id=49");
	  //driver.findElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']")).click();
	  
	  driver.findElement(By.xpath("//*[@id=\"form-currency\"]/div/button")).click();
		
      driver.findElement(By.xpath("//*[@id=\"form-currency\"]/div/ul/li[2]/button")).click();

      Thread.sleep(5000);
      
    //*[@id="content"]/div[1]/table/tbody/tr/td[5]/div
      
       val = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[5]/div")).getText();
       fos = new FileOutputStream("C:\\Users\\TA20127558\\Desktop\\price.txt");
       
       byte b[] = val.getBytes();
       fos.write(b);
       
       driver.findElement(By.xpath("//*[@id=\"form-currency\"]/div/button")).click();
       driver.findElement(By.xpath("//*[@id=\"form-currency\"]/div/ul/li[3]/button")).click();
       
       Thread.sleep(5000);
     //*[@id="content"]/div/div[2]/ul[2]/li[1]/h2
       val = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[5]/div")).getText();
       fos2 = new FileOutputStream("C:\\Users\\TA20127558\\Desktop\\price.txt");
       byte bb[] = val.getBytes();
       fos2.write(bb);
      
  }
  
  	//@Ignore
  	@Test (priority = 6)
  public void addToCart() throws IOException {
  		driver.findElement(By.xpath("//button[@data-original-title='Add to Cart']")).click();

  		driver.findElement(By.linkText("Continue")).click();
  		
  		driver.findElement(By.xpath("//a[@title='Shopping Cart']")).click();
  		
  		takeScreeenShot("C:\\Users\\TA20127558\\Desktop\\project_screenshots\\6cart.png");
  		
  		driver.findElement(By.xpath("//button[@data-original-title='Remove']")).click();
 
  	}
  	
  //@Ignore
  	@Test (priority = 7)
  	public void logOut() {
  		
  		driver.findElement(By.linkText("My Account")).click();
  		driver.findElement(By.linkText("Logout")).click();
  		
  		driver.findElement(By.linkText("Continue")).click();
  		
  	}
  	
  	 //@Ignore
  	@Test (priority = 8)
  	public void logIn() throws InterruptedException, IOException {
  		
  		driver.findElement(By.linkText("My Account")).click();
  		driver.findElement(By.linkText("Login")).click();
  		
  		Thread.sleep(2000);
  		//driver.findElement(By.linkText("Continue")).click();
  		
  		getExcelData("C:\\Users\\TA20127558\\Desktop\\project.xls");
  		
  	   sheet2 = wb.getSheetAt(1);
  		  
  	   email = sheet2.getRow(2).getCell(0).getStringCellValue();
  	   pword = sheet2.getRow(4).getCell(0).getStringCellValue();
  		
  		
  	  
  	  driver.findElement(By.name("email")).sendKeys(email);
	  driver.findElement(By.name("password")).sendKeys(pword);
	  
	  takeScreeenShot("C:\\Users\\TA20127558\\Desktop\\project_screenshots\\7login.png");
	  
	  driver.findElement(By.xpath("//input[@value='Login']")).click();
	  
	  takeScreeenShot("C:\\Users\\TA20127558\\Desktop\\project_screenshots\\8login_done.png");
  		
  		
  		
  		
  	}
  
  
  
  
  
}
