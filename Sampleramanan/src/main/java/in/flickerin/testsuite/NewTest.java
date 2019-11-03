package in.flickerin.testsuite;

import org.testng.annotations.Test;

import in.flickerin.browseractions.launchBrowser;
import in.flickerin.excelactions.excelActions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class NewTest {
	
	
	String browsername;
	String url;
	Object[] storeobject;
	Object[][] returnobject;
	ArrayList<Object[]> arrayList;
	WebDriver driverlaunch;
	
	
  @Test(dataProvider = "dp")
  public void f(String browsernameval, String urlvalue) {
	  
	  System.out.println(browsernameval+"   "+urlvalue);
	  launchBrowser lb = new launchBrowser();
	  driverlaunch = lb.OpenBrowser(driverlaunch, browsernameval);
	  lb.implicitWait(driverlaunch, 15, TimeUnit.SECONDS);
	  lb.loadsite(driverlaunch,urlvalue);
	  lb.implicitWait(driverlaunch, 15, TimeUnit.SECONDS);
	  lb.closedriver(driverlaunch);
	  
  }

  @DataProvider
  public Object[][] dp() {
   
	  	excelActions sheetdetails = new excelActions();
	  	Sheet sheetvariable = sheetdetails.getexcelworkbook(System.getProperty("user.dir")+"\\input\\firstinput.xlsx", "ramanan");
	  	
	  	int rowcount = sheetvariable.getLastRowNum();
	  	 
	  	returnobject = new Object[rowcount][];
		arrayList = new ArrayList<Object[]>();
	  	
	  	for(int i=1;i<=rowcount;i++) {
	  		
	  		int coulmncount = sheetvariable.getRow(i).getLastCellNum();
	  		
	  		for(int j=0;j<coulmncount;j++) {
	  			
	  			
	  			
	  			if(j==0) {
	  				browsername = sheetvariable.getRow(i).getCell(j).getStringCellValue();
	  			}else if (j==1) {
	  				url = sheetvariable.getRow(i).getCell(j).getStringCellValue();	  		
	  			}
	  			
	  		}
	  		
	  		storeobject = new Object[] {browsername,url};
	  		
	  		arrayList.add(storeobject);
	  		
	  	}
	  	
	  	
	  	returnobject = arrayList.toArray(new Object[arrayList.size()][]);
	  	
	  	
	  	
	  return returnobject;
	  
  }
  
}
