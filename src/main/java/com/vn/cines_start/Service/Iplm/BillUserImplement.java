package com.vn.cines_start.Service.Iplm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.cines_start.Model.BillUser;
import com.vn.cines_start.Model.Movie;
import com.vn.cines_start.Repository.BillUserRepository;
import com.vn.cines_start.Repository.MovieRepository;
import com.vn.cines_start.Service.IBillUserService;

@Service
public class BillUserImplement implements IBillUserService{
	 @Autowired
	    BillUserRepository billUserRepository ;
	    @Override
	    public List<BillUser> findAll() {
	        return billUserRepository.findAll();
	    }

	    @Override
	    public Optional<BillUser> findBillUserById(Long id) {
	        return billUserRepository.findById(id);
	    }


	    @Override
	    public BillUser create(BillUser billUser) {
	        return billUserRepository.save(billUser);
	    }

	    @Override
	    public BillUser edit(BillUser billUser) {
	        return billUserRepository.save(billUser);
	    }

	    @Override
	    public void delete(Long id) {
	    	billUserRepository.deleteById(id);;
	    }

		@Override
		public List<BillUser> findBillByUserId(Long userId) {
			return billUserRepository.findBillByUserId(userId);
		}
}
