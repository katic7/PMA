package rs.ac.uns.ftn.weplayserver.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.ftn.weplayserver.utils.HeadersRequestInterceptor;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	private static final String FIREBASE_SERVER_KEY = "AAAASfHxNrs:APA91bEK7BOsZgLM0G58qXtrGFiHP7sQPcNcE5FwRvfQLtBSHtJc-t1L9T02CAzoDqnjBpPClHmAo0LZnbNVmIKkzosPyjk6jUBKRM_DMGjoNhRArbYk7Yh3dy3pPldIOA6GkOcAc6Re";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

	
	@Async
	@Override
	public CompletableFuture<String> send(HttpEntity<String> entity) {
	 
	    RestTemplate restTemplate = new RestTemplate();
	 
	    /**
	    https://fcm.googleapis.com/fcm/send
	    Content-Type:application/json
	    Authorization:key=FIREBASE_SERVER_KEY*/
	 
	    ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new HeadersRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
	    interceptors.add(new HeadersRequestInterceptor("Content-Type", "application/json"));
	    restTemplate.setInterceptors(interceptors);
	 
	    String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
	 
	    return CompletableFuture.completedFuture(firebaseResponse);
	}

}
