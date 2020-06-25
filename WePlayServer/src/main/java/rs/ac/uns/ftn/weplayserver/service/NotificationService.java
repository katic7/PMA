package rs.ac.uns.ftn.weplayserver.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;

import rs.ac.uns.ftn.weplayserver.model.Notification;

public interface NotificationService {
	CompletableFuture<String> send(HttpEntity<String> entity);
}
