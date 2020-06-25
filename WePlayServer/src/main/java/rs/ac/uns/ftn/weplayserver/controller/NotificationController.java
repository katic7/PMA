package rs.ac.uns.ftn.weplayserver.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.ac.uns.ftn.weplayserver.dto.NotificationDTO;
import rs.ac.uns.ftn.weplayserver.model.Event;
import rs.ac.uns.ftn.weplayserver.model.Notification;
import rs.ac.uns.ftn.weplayserver.model.User;
import rs.ac.uns.ftn.weplayserver.repository.EventRepository;
import rs.ac.uns.ftn.weplayserver.repository.NotificationRepository;
import rs.ac.uns.ftn.weplayserver.service.NotificationService;
import rs.ac.uns.ftn.weplayserver.utils.ObjectMapperUtils;

@Controller
@RequestMapping(value = "/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@PostMapping("create/{id}")
	public ResponseEntity<?> createNotification(@RequestBody NotificationDTO notification2send, @PathVariable Long id) {
		
		Event event = eventRepo.getOne(id);
		for(User subscriber : event.getSubscribers()) {
			Notification n = ObjectMapperUtils.map(notification2send,Notification.class);
			n.setEvent(event);
			Notification saved = notificationRepository.save(n);
			JSONObject body = new JSONObject();
		    body.put("to", subscriber.getFcmid());
		    body.put("priority", "high");
		    JSONObject notification = new JSONObject();
		    notification.put("title", notification2send.getText());
		    notification.put("body", notification2send.getScore());
		    notification.put("click_action", "NOTIFICATION_INFO");	    
		    JSONObject data = new JSONObject();
		    data.put("NotificationId", saved.getId());
		    //data.put("EventId", saved.getEvent().getId());
		    body.put("notification", notification);
		    body.put("data", data);
		    HttpEntity<String> request = new HttpEntity<>(body.toString());
		    CompletableFuture<String> pushNotification = notificationService.send(request);
		    CompletableFuture.allOf(pushNotification).join();
		    try {
		      String firebaseResponse = pushNotification.get();
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    } catch (ExecutionException e) {
		      e.printStackTrace();
		    }
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
