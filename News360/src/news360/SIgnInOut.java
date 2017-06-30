package news360;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SIgnInOut {
	static WebDriver driver;
	//variables xpath
	static String passwordXpath="//div[@class='simpleauth']//form[@class='signin ng-pristine ng-valid']//input[@class='email textbox'][@type='email']//following :: input[@class='password textbox']";
	static String btnSignIn="//div[@class='simpleauth']//form[@class='signin ng-pristine ng-valid']//input[@class='email textbox'][@type='email']//following :: input[@class='password textbox']//following:: button[text()='Sign in']";
	static String txtEmail="//div[@class='simpleauth']//form[@class='signin ng-pristine ng-valid']//input[@class='email textbox'][@type='email']";
	static String btnCancel="//div[@class='simpleauth']//form[@class='signin ng-pristine ng-valid']//input[@class='email textbox'][@type='email']//following :: input[@class='password textbox']//following:: button[text()='Cancel']";
	public static void loadAuth(){
		driver.findElement(By.xpath("//a[text()='Start reading']")).click();//Test Case ID: TC02
		driver.findElement(By.xpath("//a[@class='expand fancybox login-signin ng-binding'][text()='Sign in with email']")).click();//Test Case ID: TC03

	}
	public static void errorcheck(String email, String pass, String actualerrorXpath, String expectederrorText )
	{
		driver.findElement(By.xpath(txtEmail)).sendKeys(email);
		driver.findElement(By.xpath(passwordXpath)).sendKeys(pass);
		driver.findElement(By.xpath(btnSignIn)).click();
		String ActualError=driver.findElement(By.xpath(actualerrorXpath)).getAttribute("innerHTML");
		assertTrue("Verification failed: Actual error and expected error messages are not same", ActualError.contains(expectederrorText));
		driver.findElement(By.xpath("//div[@class='simplepopup expand']//div[@class='close']")).click();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver(); 
		driver.manage().window().maximize();
				
		driver.get("https://news360.com/");//Test Case ID: TC01
		loadAuth();
		
		//Test Case ID: TC04
		driver.findElement(By.xpath(txtEmail)).sendKeys("ahsan.shabnam@gmail.com");
		driver.findElement(By.xpath(passwordXpath)).sendKeys("123456");
		driver.findElement(By.xpath(btnSignIn)).click();
	    driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//a[@class='user default']")).click();//Test Case ID: TC06
		driver.findElement(By.xpath("//div[@class='ebutton logout ng-binding']")).click();//Test Case ID: TC07
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		
		//Test case ID: TC08
		loadAuth();
		errorcheck("", "", "//div[@class='simpleauth']//form[@class='signin ng-pristine ng-valid']//li[@class='required']","This value is required.");
		
		//Test case ID:TC09
		driver.navigate().refresh();
		loadAuth();
		errorcheck("testatgmail.com", "123456", "//ul[@class='parsley-error-list']//li[@class='type']", "This value should be a valid email.");
		
		//Test case ID: TC10
		driver.navigate().refresh();
		loadAuth();
		errorcheck( "ahsan.shabnam@gmail.com","abc", "//div[@class='simplepopup expand']//div[@class='error-message message ng-binding']", "Invalid login or password" );
		
		//Test case ID: TC11
		driver.navigate().refresh();
		loadAuth();
		driver.findElement(By.xpath(btnCancel)).click();
		
	}

}
