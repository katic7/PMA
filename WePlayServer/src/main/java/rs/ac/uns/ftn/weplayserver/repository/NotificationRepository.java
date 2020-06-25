package rs.ac.uns.ftn.weplayserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.weplayserver.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
