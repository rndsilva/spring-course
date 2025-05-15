package com.springcourserepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.springcourse.domain.Request;
import com.springcourse.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{

	public List<Request> findAllByOwnerId(Long id);
	
	@Query("UPDATE Request SET stat = ?2 where id = ?1")
	public Request updateStatus(Long id, RequestState state);
}
