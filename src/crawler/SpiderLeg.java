package crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Contains the functionality to explore web pages and look for the specific word.
 * @author Bennett Clapp
 *
 */
public class SpiderLeg {
	
	/**List of all the links found so far. */
	private List<String> links = new LinkedList<String>();
	
	/**Private reference to the html page currently being explored. */
	private Document htmlDocument;
	
	/**Agent that allows the web page to return in the correct format. */
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	
	/**
	 * Connects to the given url and adds links to the list.
	 * @param url The URL of the web page being searched.
	 */
	public void crawl(String url) 
	{
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = connection.get();
			this.htmlDocument = htmlDocument;
			
			System.out.println("Received web page at " + url);
			Elements linksOnPage = htmlDocument.select("a[href]");
			System.out.println("Found (" + linksOnPage.size() + ") links");
			for(Element link : linksOnPage) {
				this.links.add(link.absUrl("href"));
			}
		} catch (IOException ioe) {
			System.out.println("Error in out HTTP request " + ioe);
		}
		
	}
	
	/**
	 * Searches for a specific word in the HTML Document.
	 * @param word The word being searched.
	 * @return True if the word was found, false if not.
	 */
	public boolean searchForWord(String word)
	{
		System.out.println("Searching for the word " + word + "...");
		String bodyText = this.htmlDocument.body().text();
		return bodyText.toLowerCase().contains(word.toLowerCase());
	}
	
	/**
	 * Retrieves all of the links that have been found so far.
	 * @return List representation of the links.
	 */
	public List<String> getLinks() 
	{
		return this.links;
	}
}
