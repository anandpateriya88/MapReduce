package com.crawler.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.arch.domainobjects.PropertyDetails;

public class Spider {

	private static final int MAX_PAGES_TO_SEARCH = 20;
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();

	private String nextUrl() {
		String nextUrl;
		do {
			nextUrl = this.pagesToVisit.remove(0);
		} while (this.pagesVisited.contains(nextUrl));
		this.pagesVisited.add(nextUrl);
		return nextUrl;
	}

	public List<PropertyDetails> getAllPropertiesDtls(String url) {
		
		List<PropertyDetails> allProperties = new ArrayList<PropertyDetails>(); 
		//-- Ids to search ---
		List<String> idsToSearch = new ArrayList<String>();
		idsToSearch.add("wrapper_listing");
		String success = "INITIALIZED";
		while (this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
			System.out.println("position 1");
			String currentUrl;
			SpiderLeg leg = new SpiderLeg();
			if (this.pagesToVisit.isEmpty()) {
				currentUrl = url;
				this.pagesVisited.add(url);
			} else {
				currentUrl = this.nextUrl();
			}
			System.out.println("position 2");
			leg.crawl(currentUrl);
			List<PropertyDetails> tempPropdtls = leg.getPropertyDetailsRent(idsToSearch);
			if(null != tempPropdtls && !tempPropdtls.isEmpty()){
				allProperties.addAll(tempPropdtls);
			}
			System.out.println("position 3");
			this.pagesToVisit.addAll(leg.getLinks());
			System.out.println("position 7");
		}
		System.out.println(String.format("== Properties List Creation END == Properties Found --->", allProperties.size()));
		return allProperties;
	}

}
