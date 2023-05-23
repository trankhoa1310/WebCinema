package com.vn.cines_start.Service;

import java.util.List;
import java.util.Optional;

import com.vn.cines_start.Model.BillUser;
import com.vn.cines_start.Model.Movie;

public interface IBillUserService {
	List<BillUser> findAll();

    Optional<BillUser> findBillUserById(Long id);
    BillUser create(BillUser billUser);

    BillUser edit(BillUser billUser);

    void delete(Long id);
    
    List<BillUser> findBillByUserId(Long userId);
}
