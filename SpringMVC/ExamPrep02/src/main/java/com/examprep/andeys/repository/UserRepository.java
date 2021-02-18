package com.examprep.andeys.repository;

import com.examprep.andeys.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
}
