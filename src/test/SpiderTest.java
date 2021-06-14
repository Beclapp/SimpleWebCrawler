package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import crawler.Spider;

/**
 * Tests the Spider Class.
 * @author Bennett Clapp
 *
 */
public class SpiderTest {
	
	Spider spider;
	
	@Before
	public void initialize() {
		spider = new Spider();
	}
	
	@Test
	public void successTest() {
		//Create a new spider object for searching
		Spider spider = new Spider();
				
		//Should be found in 7 links
		assertEquals("**Done** Visited 7 web page(s)", spider.search("http://arstechnica.com/", "computer"));
	}
	
	@Test
	public void failureTest() {
		//Should quit searching after visiting 10 links
		assertEquals("**Done** Visited 10 web page(s)", spider.search("http://arstechnica.com/", "asdhfjksha"));
	}
}
