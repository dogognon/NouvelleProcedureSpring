package com.dogognon.sohliou.kone.security.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import lombok.NonNull;



public interface RoleRepository extends JpaRepository<Role, Long> {
	
    Optional<Role> findByRoleName(@NonNull RoleName roleName);
}
