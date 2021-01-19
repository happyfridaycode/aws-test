package com.example.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;


@Slf4j
@RestController
public class HelloController {
	private final static String VERSION = "v3";
	@RequestMapping("/")
	public String index() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			String fqdn = address.getCanonicalHostName();
			return String.format("Greetings from GUHADA service (%s): %s-%s",
					VERSION, fqdn, address.getHostAddress());
		} catch (Throwable t) {
			log.error("failed to get inet address ", t);
		}
		return String.format("Greetings from GUHADA service (%s) ", VERSION);
	}




}
