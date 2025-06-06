package com.springcourse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springcourse.domain.RequestStage;
import java.util.List;


@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long>{
	
	public List<RequestStage> findAllByRequestId(Long id);
	
	public Page<RequestStage> findAllByRequestId(Long id, Pageable pageable);

}
