package org.ui.cucumberTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ShopStyleTest {
	WebDriver driver = null;

	@Before
	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/mageswari/Downloads/chromedriver20");
		driver = new ChromeDriver();
		driver.navigate().to("https://www.shopstyle.com");
	}

	@Test
	public void demo() throws IOException {

		// Reading the links from shopstyle
		String links = driver.findElement(By.className("mega-menu__top-links")).getText();
		System.out.println("top menu:::" + links);
		String[] link = links.split("\\s+");
		List targetList = Arrays.asList(link);

		// Initializing list
		List list = new ArrayList();
		try(InputStream is =ClassLoader.getSystemClassLoader().getResourceAsStream("topmenu.txt")) {
			// Read the menus from a text file . If any link is removed from the
			// website , just need to update the text file for test case to pass
			//BufferedReader br = new BufferedReader(new FileReader(
					//"/Users/mageswari/Documents/selenium_workspace/TestProject/src/shopstyle/top-menus.txt"));
			;
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				list.add(sCurrentLine);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// compare the links from the website and from the text file
		assertEquals(targetList, list);

	}

	@After
	public void tearDown() {
		
		driver.quit();
	}
}
