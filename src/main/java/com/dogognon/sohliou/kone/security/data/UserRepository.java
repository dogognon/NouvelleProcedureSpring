package com.dogognon.sohliou.kone.security.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

	Boolean existsByPhoneOrEmail(String phone, String email);
	
    Optional<User> findByPhoneOrEmail(String phone, String email);
    
    
    Optional<User> findByPhone(String phone);
    
    Boolean existsByPhone(String phone);
    
    
    
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
    
    
    List<User> findByIdIn(List<String> userIds);
    
}
