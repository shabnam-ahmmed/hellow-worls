package news360;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SignUp {
	static WebDriver driver;
	static String linkSignUp="//form[@class='signin ng-pristine ng-valid']//input[@class='email textbox'][@type='email']//following :: a[text()='Sign up']";
	static String btnSignUp="//input[@class='email textbox'][@type='email']//following :: button[text()='Sign up']";
	static String txtEmail="//form[@class='signup ng-pristine ng-valid']//input[@class='email textbox'][@type='email']";
	static String passwordXpath="//form[@class='signup ng-pristine ng-valid']//following::input[@class='email textbox'][@type='email']// following :: input[@id='popuppassword']";
	static String confirmPasswordXpath="//form[@class='signup ng-pristine ng-valid']//following::input[@class='email textbox'][@type='email']// following :: input[@class='confirmpassword textbox']";
	static String ActualError;
	static String expectedError;
	public static void loadAuth(){
		driver.findElement(By.xpath("//a[text()='Start reading']")).click();//Test Case ID: TC13
		driver.findElement(By.xpath("//a[@class='expand fancybox login-signin ng-binding'][text()='Sign in with email']")).click();//Test Case ID: TC14
		driver.findElement(By.xpath(linkSignUp)).click(); //Test case ID: 15
		
	}
	public static void errorMsgCheck(String ActualError, String ExpectedError)
	{
		try {
			assertTrue("Verification failed: Actual error and expected error messages are not same", ActualError.contains(ExpectedError));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void setParameter(String emailadd, String Pass, String ConPass)
	{
		driver.findElement(By.xpath(txtEmail)).sendKeys(emailadd);
		driver.findElement(By.xpath(passwordXpath)).sendKeys(Pass);
		driver.findElement(By.xpath(confirmPasswordXpath)).sendKeys(ConPass);
		driver.findElement(By.xpath(btnSignUp)).click();
	}
	public static void main(String[] args) {
		String errorRequiredEmail="html/body/div[2]/div[2]/div[4]/form[3]/fieldset[1]/ul/li";
		String errorRequiredPassword="html/body/div[2]/div[2]/div[4]/form[3]/fieldset[2]/ul/li";
		String errorRequiredConfirm="html/body/div[2]/div[2]/div[4]/form[3]/fieldset[3]/ul/li";
		
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver(); 
		driver.manage().window().maximize();
		driver.get("https://news360.com/");//Test Case ID: TC012
		
		//Test case ID: TC16
		loadAuth();
		driver.findElement(By.xpath(btnSignUp)).click();
		ActualError=driver.findElement(By.xpath(errorRequiredEmail)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value is required.");
		
		ActualError=driver.findElement(By.xpath(errorRequiredPassword)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value is required.");
		
		ActualError=driver.findElement(By.xpath(errorRequiredConfirm)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value is required.");
		
		
		//Test case ID:TC18
		driver.navigate().refresh();
		loadAuth();
		setParameter("ahsan.shabnam@gmail.com", "123", "456");
		ActualError=driver.findElement(By.xpath(errorRequiredPassword)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value is too short. It should have 6 characters or more.");
		ActualError=driver.findElement(By.xpath(errorRequiredConfirm)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value should be the same.");
		
		//Test case ID:TC19
		driver.navigate().refresh();
		loadAuth();
		setParameter("ahsan.shabnam@gmail.com", "123456", "456789");
		ActualError=driver.findElement(By.xpath(errorRequiredConfirm)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value should be the same.");
		
		//Test case ID:TC20
		driver.navigate().refresh();
		loadAuth();
		setParameter("ahsan.shabnam@gmail", "123", "456789");
		ActualError=driver.findElement(By.xpath(errorRequiredEmail)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value should be a valid email.");
		ActualError=driver.findElement(By.xpath(errorRequiredPassword)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value is too short. It should have 6 characters or more.");
		ActualError=driver.findElement(By.xpath(errorRequiredConfirm)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value should be the same.");	
		
		//Test case ID:TC21 and TC22
		driver.navigate().refresh();
		loadAuth();
		setParameter("ahsan", "123456", "456");
		ActualError=driver.findElement(By.xpath(errorRequiredEmail)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value should be a valid email.");
		ActualError=driver.findElement(By.xpath(errorRequiredConfirm)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value should be the same.");
		
		//Test case ID:TC23
		driver.navigate().refresh();
		loadAuth();
		setParameter("ahsan.shabnam@gmail.com", "123", "123");
		ActualError=driver.findElement(By.xpath(errorRequiredPassword)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value is too short. It should have 6 characters or more.");
		
		//Test case ID:TC24
		driver.navigate().refresh();
		loadAuth();
		setParameter("ahsan.shabnam@gmail.com", "123", "456789");
		ActualError=driver.findElement(By.xpath(errorRequiredPassword)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value is too short. It should have 6 characters or more.");
		ActualError=driver.findElement(By.xpath(errorRequiredConfirm)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value should be the same.");	

		//Test case ID:TC25
		driver.navigate().refresh();
		loadAuth();
		setParameter("ahsan.shabnam@gmail.com", "123456", "456");
		ActualError=driver.findElement(By.xpath(errorRequiredConfirm)).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "This value should be the same.");	
								
		//Test case ID:TC26
		driver.navigate().refresh();
		loadAuth();
		setParameter("ahsan.shabnam@gmail.com", "123456", "123456");
		ActualError=driver.findElement(By.xpath("html/body/div[2]/div[2]/div[1]")).getAttribute("innerHTML");
		errorMsgCheck(ActualError, "Invalid login or password");
				
		//Test case ID:TC17
		driver.navigate().refresh();
		loadAuth();
		setParameter("ahsanahsanahsan.shabnam@gmail.com", "123456", "123456");
		
				
		System.out.println("All test cases were passed!!!");
		
		
	}

}
