package com.crawler.services;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.arch.domainobjects.PropertyDetails;
import com.google.gson.Gson;

public class DemoClass {

	public static void main(String str[]){
		List<PropertyDetails> propDetlsList = new ArrayList<PropertyDetails>(); 
		SpiderLeg sl = new SpiderLeg();
		Spider spider = new Spider();
		propDetlsList = spider.getAllPropertiesDtls("http://www.iproperty.com.my/property/searchresult.aspx?t=S&gpt=AR&st=&ct=&k=&pt=&mp=&xp=&mbr=&xbr=&mbu=&xbu=&lo=&wp=&wv=&wa=&ht=&au=&ft=&sby=&ns=1");
		Gson gson = new Gson();
		String json = gson.toJson(propDetlsList);
		try{
		PrintWriter writer = new PrintWriter("d:\\properties.txt", "UTF-8");
		writer.println(json);
		writer.close();
		}catch(Exception e){
			System.out.println("Exception occured in Writing file to FS -- "+e);
		}
		/*SpiderLeg sl = new SpiderLeg();
		sl.downloadImages();*/
		//--WORKING----http://www.propwall.my/
		//--WORKING---http://www.propertyguru.com.my/
		//-- MAP ISSUE -- NOT WORKING--http://www.starproperty.my --
		//--WORKING-------http://homes.trovit.my/
		//--WORKING-------http://thinkproperty.com.my/
		//--WORKING-------http://homes.mitula.my/
		//--WORKING-------http://property.malaysiamostwanted.com/ use --http://property.malaysiamostwanted.com/show/search?q=malaysia&page=2
		//--INCAPSULA ---NOT WORKING --http://www.iproperty.com.my/property/searchresult.aspx?t=S&gpt=AR&st=&ct=&k=&pt=&mp=&xp=&mbr=&xbr=&mbu=&xbu=&lo=&wp=&wv=&wa=&ht=&au=&ft=&sby=&ns=1
	}
}
