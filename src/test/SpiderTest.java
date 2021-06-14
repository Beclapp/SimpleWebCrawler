package test;

import crawler.Spider;

/**
 * Tests the Spider Class.
 * @author Bennett Clapp
 *
 */
public class SpiderTest {
	/**
	 * Creates the test object and runs it with two different words, one which can be found and the other
	 * which can't.
	 * @param args Command Line Arguments.
	 */
	public static void main(String[] args) 
	{
		//Create a new spider object for searching
		Spider spider = new Spider();
		
		//Should be found in 7 links
		spider.search("http://arstechnica.com/", "computer");
		
		//Should quit searching after visiting 10 links
		spider.search("http://arstechnica.com/", "asdhfjksha");
	}
}
