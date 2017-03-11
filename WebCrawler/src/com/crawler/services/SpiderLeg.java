package com.crawler.services;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.arch.domainobjects.PropertyDetails;

public class SpiderLeg {

	private List<String> links = new LinkedList<String>(); // Just a list of
															// URLs
	private Document htmlDocument; // This is our web page, or in other words,
									// our document
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36";

	// Give it a URL and it makes an HTTP request for a web page
	public void crawl(String url) {
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			connection.timeout(500000);// 100 seconds
			Document htmlDocument = connection.get();
			this.htmlDocument = htmlDocument;
			System.out.println("Received web page at " + url);
			Elements linksOnPage = htmlDocument.select("a[href]");
			System.out.println("Found (" + linksOnPage.size() + ") links");
			for (Element link : linksOnPage) {
				this.links.add(link.absUrl("href"));
			}
			System.out.println("position 8");
		} catch (IOException ioe) {
			// We were not successful in our HTTP request
			System.out.println("Error in out HTTP request " + ioe);
		}
	}

	// Tries to find a word on the page
	public List<PropertyDetails> getPropertyDetailsRent(List<String> idsToSearch) {
		List<PropertyDetails> propertyDetailsLst = new ArrayList<PropertyDetails>();
		for (String id : idsToSearch) {
			Element elementImp = this.htmlDocument.body().getElementById(id);
			if (null != elementImp) {
				Elements itemInfoElems = elementImp.getElementsByAttributeValue("class", "item-info");
				if(null != itemInfoElems && !itemInfoElems.isEmpty()){
					for(Element temp: itemInfoElems){
						PropertyDetails propDetailsTemp = new PropertyDetails();
						Elements itemTempAll =  null;
						
						propDetailsTemp.setRentOrSale("RENT");
						// -- getting property Name
						itemTempAll = temp.getElementsByTag("a");
						if(null != itemTempAll && !itemTempAll.isEmpty()){
							Element tempElement = itemTempAll.get(0);
							propDetailsTemp.setName(tempElement.text());
						}
						// -- getting property Desc
						itemTempAll = temp.getElementsByAttributeValue("itemprop", "description");
						if(null != itemTempAll && !itemTempAll.isEmpty()){
							Element tempElement = itemTempAll.get(0);
							propDetailsTemp.setDescription(tempElement.text());
						}

						itemTempAll= temp.getElementsByAttributeValue("class", "room");
						if(null != itemTempAll && !itemTempAll.isEmpty()){
							Element tempElement = itemTempAll.get(0);
							propDetailsTemp.setRooms(tempElement.text());
						}

						itemTempAll = temp.getElementsByAttributeValue("class", "price");
						if(null != itemTempAll && !itemTempAll.isEmpty()){
							Element tempElement = itemTempAll.get(0);
							propDetailsTemp.setRentalPrice(tempElement.text());
						}
						
						itemTempAll = temp.getElementsByAttributeValue("class", "no-price");
						if(null != itemTempAll && !itemTempAll.isEmpty()){
							propDetailsTemp.setRentalPrice("On Request");
						}
						
						itemTempAll = temp.getElementsByAttributeValue("class", "floorArea");
						if(null != itemTempAll && !itemTempAll.isEmpty()){
							Element tempElement = itemTempAll.get(0);
							propDetailsTemp.setFloorArea(tempElement.text());
						}
						
						itemTempAll = temp.getElementsByAttributeValue("class", "bath");
						if(null != itemTempAll && !itemTempAll.isEmpty()){
							Element tempElement = itemTempAll.get(0);
							propDetailsTemp.setBathrooms(tempElement.text());
						}
						propertyDetailsLst.add(propDetailsTemp);
						//---Find similar listing ...later...
					}
					
				}
			
			}
		}
		System.out.println("Properties Found on page < "+this.htmlDocument.baseUri()+" >"+propertyDetailsLst.size());
		return propertyDetailsLst;
	}

	// Returns a list of all the URLs on the page
	public List<String> getLinks() {
		return this.links;
	}
	
	public void downloadImages(){
		//Image img = new Image();
		URL imgUrl = null;
		try{
		imgUrl = new URL("https://my1-cdn.pgimgs.com/listing/14469770/UPHO.53648747.V800/Country-Heights-Bayu-Country-Heights-Malaysia.jpg");
		File imgLocation = new File("G:\\Hadoop_2015\\Crawler_images\\new.jpg");
		FileUtils.copyURLToFile(imgUrl, imgLocation);
		
		}catch(Exception e){
			System.out.println("---Exception Occured While downloading image. Image URl ==>"+imgUrl);
		}
	}

}
