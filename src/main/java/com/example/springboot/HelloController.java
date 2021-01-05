package com.example.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;



@RestController
public class HelloController {
	private final static String VERSION = "v1";
	@RequestMapping("/")
	public String index() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			String fqdn = address.getCanonicalHostName();
			return String.format("Greetings from GUHADA service (%s): %s-%s",
					VERSION, fqdn, address.getHostAddress());
		} catch (Throwable t) {
t.printStackTrace();
		}
		return String.format("Greetings from GUHADA service (%s) ", VERSION);
	}




}
