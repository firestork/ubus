package com.firestork.config;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class ConfigurationXpath {

	public ConfigurationXpath() {

	}

	public static HashMap<String, String> insertMap(String filename) {

		HashMap<String, String> map = new HashMap<String, String>();
		XMLConfiguration config = null;
		try {
			config = new XMLConfiguration(filename);
			Iterator<String> keyIter = config.getKeys();
			String key;
			while (keyIter.hasNext()) {
				key = keyIter.next();
				String s = (String) config.getProperty(key);
				map.put(key, s);
				//System.out.println(key + " = " + s);
			}
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String keymap : map.keySet()){
			System.out.println(keymap + map.get(keymap));
		}
		return map;
	}

}
