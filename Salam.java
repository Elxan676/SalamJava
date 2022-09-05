package mypackage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class Salam {
	static WebDriver driver;
public static void main(String[] args) throws MalformedURLException, IOException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\elxan\\Downloads\\chromedriver_last\\chromedriver.exe");

	driver = new ChromeDriver();

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.get("https://kapitalbank.udemy.com/organization/home/");
	 SoftAssert a= new SoftAssert(); 
	List<WebElement> allLinks=driver.findElements(By.tagName("a"));
	int count=1;
	for(WebElement links:allLinks) {
		String url=links.getAttribute("href");
	
	 String ad=links.getText();
	 System.out.println(count+") " +ad);
	 count ++;
	 
		 HttpURLConnection   conn= (HttpURLConnection)new URL(url).openConnection();		
		conn.setRequestMethod("HEAD");
		conn.connect();
		int respCode=conn.getResponseCode();
		System.out.println(respCode);
		
		if(respCode>=400){
			System.out.println(count+") " +"This is broken code: "+ad+"."+ "  Broken code with number: " +respCode);
		}
		a.assertTrue(respCode<400, "The link with Text"+ links.getText()+"the broken links with code" + respCode );
		count ++;
	}
	a.assertAll();
	
	
	
	}

}
