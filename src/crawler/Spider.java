package crawler;

import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Searches a web page for a specific search word.  If not found, the spider will visit
 * the other web pages in links to find the given word.
 * @author Bennett Clapp
 *
 */
public class Spider {

	//Fields
	/**The number of pages to search before quitting the program. */
	private static final int MAX_PAGES_TO_SEARCH = 10;
	
	/**A set of pages already visited, which won't have duplicates. */
	private Set<String> pagesVisited;
	
	/**A list of pages that can still be visited, which allows for duplicates. */
	private List<String> pagesToVisit;
	
	/**
	 * Constructor for the Spider, which initializes the pagesVisited and pagesToVisit lists.
	 */
	public Spider() {
		pagesVisited = new HashSet<String>();
		pagesToVisit = new LinkedList<String>();
	}
	
	/**
	 * Determines the next URL to visit, so that there aren't any duplicates.
	 * @return A string of the next URL to visit.
	 */
	private String nextUrl() 
	{
		String nextUrl;
		do {
			nextUrl = this.pagesToVisit.remove(0);
		} while(this.pagesVisited.contains(nextUrl));
		this.pagesVisited.add(nextUrl);
		return nextUrl;
	}
	
	/**
	 * Searches a web page through the leg functions and acts accordingly if the word is found.
	 * @param url The URL being searched.
	 * @param searchWord The word being searched for.
	 */
	public void search(String url, String searchWord) 
	{
		while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
			String currentUrl;
			SpiderLeg leg = new SpiderLeg();
			if(this.pagesToVisit.isEmpty()) {
				currentUrl = url;
				this.pagesVisited.add(url);
			} else {
				currentUrl = this.nextUrl();
			}
			leg.crawl(currentUrl);
			boolean success = leg.searchForWord(searchWord);
			if(success) {
				System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
				break;
			}
			this.pagesToVisit.addAll(leg.getLinks());
		}
		System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
	}
}
