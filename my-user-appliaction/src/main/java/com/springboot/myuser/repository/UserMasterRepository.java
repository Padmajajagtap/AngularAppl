package com.springboot.myuser.repository;

import com.springboot.myuser.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {
}
