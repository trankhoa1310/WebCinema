package com.vn.cines_start.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vn.cines_start.Model.BillUser;
import com.vn.cines_start.Model.Role;
import com.vn.cines_start.Model.Schedule;

public interface BillUserRepository  extends JpaRepository<BillUser,Long>{
	@Query(value = "select *  from billusers as b  where b.user_id=?1", nativeQuery = true)
	List<BillUser> findBillByUserId(Long userId);
}
