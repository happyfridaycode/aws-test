package com.example.springboot;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;


//@Slf4j
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
//			log.error("failed to get local addresses",t);
		}
		return String.format("Greetings from GUHADA service (%s) ", VERSION);
	}




}
