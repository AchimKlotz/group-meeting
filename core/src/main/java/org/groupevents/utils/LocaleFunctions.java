package org.groupevents.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public  class LocaleFunctions {
   public  Map<String,String> getAllCountries(Locale locale) {
	   String[] countries = Locale.getISOCountries();
	     return 
	    		 Arrays.stream(countries).sorted()
	    		 .collect(Collectors
	    				 .toMap(key->key,
	    						 key->{Locale l = new Locale("",key);return l.getDisplayCountry(locale);}
	    						 )).entrySet().stream()
	    		 .sorted(Comparator.comparing(e->e.getValue().toString()))
	    		 .collect(Collectors.toMap(entry->entry.getKey(), entry->entry.getValue(),(v1,v2)->v1,()->new LinkedHashMap<String,String>()));
   }
   
   public Map<String,String> getAllCountries() {
	   return getAllCountries(Locale.getDefault());
   }
}
