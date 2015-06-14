package com.schenker.zeus.utils;


import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author vani.li@dbschenker.com
 * @version 1.0
 * @date
 * @desc This class handle the Ehcache operation
 * Lazy singleton
 */
public class EhcacheUtil {
	
	private final static CacheManager cm = CacheManager.getInstance();
	
	private static EhcacheUtil ehcache;
	
	private Cache cache;
	
	private EhcacheUtil(){
		cm.addCache("ZEUS");
		cache = cm.getCache("ZEUS");
	}
	public static  EhcacheUtil getInstance(){
		
		if(ehcache==null){
			ehcache = new EhcacheUtil();
		}
		return ehcache;
	}
	
	public void put(Object key, Object value){
		Element element = new Element(key, value);  
		cache.put(element);
	}
	
	public Object get(Object key){
		try{
			return cache.get(key).getObjectValue();
		}catch(NullPointerException e){
			return null;
		}
		
	}
	
	public void remove(String key){
		cache.remove(key);
	}
}
