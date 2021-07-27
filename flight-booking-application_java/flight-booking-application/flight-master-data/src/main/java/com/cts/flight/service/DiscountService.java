package com.cts.flight.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.flight.modal.DiscountModal;
import com.cts.flight.repository.DiscountRepository;
import com.cts.flight.util.InvalidRequestException;

@Service
public class DiscountService {
	
	public final Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	DiscountRepository discountRepository;

	public DiscountModal createDiscount(DiscountModal discountModal) throws Exception {
		DiscountModal existingDiscount = discountRepository.findByDiscountNumber((discountModal.getDiscountNumber()));
		if(existingDiscount!=null) {
			throw new InvalidRequestException("Discount Number"+discountModal.getDiscountNumber()+"is already exist!!");
		}
		logger.info("discount request {} ", discountModal);
		return discountRepository.save(discountModal);
	}

	public DiscountModal updateDiscountOffer(Long id, DiscountModal discountModal) {
		logger.info("discount update request for id {}",id);
		 Optional<DiscountModal> findById = discountRepository.findById(id);
		 if(findById.isPresent()) {
			 DiscountModal discountModal2 = findById.get();
			 BeanUtils.copyProperties(discountModal, discountModal2, "id");
			 return discountRepository.save(discountModal2);
		 }else {
			 throw new InvalidRequestException("Discount id "+id+" not found for update flight details ") ;
		 }
	}

	public List<DiscountModal> getAllDiscount() {
		return discountRepository.findAll();
	}

	public boolean deleteDiscountOffer(Long id) {
		boolean isDelete=false;
		logger.info("flight update request for id {}",id);
		 Optional<DiscountModal> findById = discountRepository.findById(id);
		 if(findById.isPresent()) {
			 DiscountModal discountModal2 = findById.get();
			 discountRepository.delete(discountModal2);
			 isDelete=true;
		 }else {
			 throw new InvalidRequestException("Discount id "+id+" not found for delete flight details ") ;
		 }
		 return isDelete;
	}

	public DiscountModal getDiscountByDiscountCode(String discountCode) throws Exception {
		DiscountModal discountModal=discountRepository.findByDiscountNumber(discountCode);
		if(discountModal==null) {
			throw new InvalidRequestException("Discount Details are not present for Discount Number "+discountCode);
		}
		return discountModal;
	}

}
