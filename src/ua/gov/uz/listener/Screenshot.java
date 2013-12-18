/**
 * 
 */
package ua.gov.uz.listener;

/**
 * @author msks
 *
 */
import java.io.File;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import ua.gov.uz.test.BaseTest;

public class Screenshot extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult result) {
		File file = new File("");

		Reporter.setCurrentTestResult(result);
//		System.out.println(file.getAbsolutePath());
//		Reporter.log(file.getAbsolutePath());
		
		//----------------------------------------
		//		Reporter.log("screenshot saved at " + file.getAbsolutePath()
//				+ "//screenshots//" + result.getName() + ".jpg");
//		Reporter.log("<a href='../" + result.getName() + ".jpg' <img src='../"	+ result.getName() + ".jpg' hight='100' width='100'/> </a>");
//		Reporter.log("screenshot for "+ result +"<div style=\"height:768px; width: 1024px; overflow:scroll\"><img src=\"../"+result.getName() +".jpg"+"\"></div>", true);
		
		try {
			BaseTest.CaptureScreenshot(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.setCurrentTestResult(null);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// will be called after test will be skipped
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// will be called after test will pass
	}

}
