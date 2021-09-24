package testNG;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG {

	WebDriver wD;
	String browser;
	String prod_url;
	
	@BeforeTest
	public void config() {
		//BufferedReader InputStream FileReader Scanner
		Properties p = new Properties();
		try {
			InputStream i = new FileInputStream("src\\main\\java\\testNGConfig"
					+ "\\config.properties");
			p.load(i);
			 browser 	= p.getProperty("browser");
			 prod_url 		= p.getProperty("prod_url"); 
			 System.out.println(browser);
			 System.out.println(prod_url);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void launcher() {
		if(browser.equalsIgnoreCase("gc")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			wD = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("mse")){
			System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
			wD = new EdgeDriver();
		}

		wD.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test (priority = 1)
	public void dropDownTest() throws InterruptedException {
		wD.get(prod_url);
		wD.manage().window().maximize();
//		wD.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//Element Library
		WebElement USERNAME_FL 	= wD.findElement(By.xpath("//input[@id='email']"));
		WebElement PASSWORD_F 	= wD.findElement(By.xpath("//input[@id='passwd']"));
		WebElement LOGIN_B 		= wD.findElement(By.xpath("//button[@name='SubmitLogin'and @id='SubmitLogin']"));

		//Variable Values
		USERNAME_FL	.sendKeys("agentofforce07@gmail.com");
		PASSWORD_F	.sendKeys("12345");
		
		LOGIN_B		.click();
		Thread.sleep(2000);
	}
	
	@Test (priority = 2)
	public void secondPriority() throws InterruptedException {
		wD.get(prod_url);
		wD.manage().window().maximize();
		
		System.out.println("this has a lower priority "
				+ "bc its priority number is higher!");
	
		Thread.sleep(3000);
	}
	
	@AfterMethod
	public void close() {

		
//		Launcher l = new Launcher();
//		l.wD.quit();
		wD.close();
		wD.quit();

//		String systemType = System.getProperty("os.name").toLowerCase();
//
//		if (systemType.contains("win")) {
//			try {
//				// Selenium drivers don't always close properly, kill them
//				System.out.println("Close one or more driver exe files");
//				Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
//				Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
//				Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
//				Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
//			} catch (IOException e) {
//				System.out.println("Failed to close one or more driver exe files");
//			}
//		}
	}
	
}
