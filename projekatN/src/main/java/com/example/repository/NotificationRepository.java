package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>  {
	
	@Query(value = "SELECT n FROM Notification n WHERE n.building.id = ?1",
		  countQuery = "SELECT count(*) FROM Notification n WHERE n.building.id = ?1",
		    nativeQuery = false)
	public Page<Notification> findByBuilding(Long buildingID, Pageable pageable);
	
	@Query(value = "SELECT n.id, n.date, n.status, n.text FROM Notification n WHERE n.writer.id = ?1",
			  countQuery = "SELECT count(*) FROM Notification n WHERE n.writer.id = ?1",
			    nativeQuery = false)
	public Page<Notification> findByWriter(Long userId, Pageable pageable);

	@Query(value = "SELECT n FROM Notification n WHERE n.item.id IS NULL AND n.building.id=?1")
	public List<Notification> findWithoutMeeting(Long id);
	
	@Query("SELECT count(*) FROM Notification n WHERE n.building.id = ?1")
	public Long getCountOfNotifications(Long idBuilding);
	
	
		
}
