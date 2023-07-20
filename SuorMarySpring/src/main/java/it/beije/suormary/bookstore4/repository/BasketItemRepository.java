package it.beije.suormary.bookstore4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore4.model.BasketItem;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Integer>{

//	public List<BasketItem> findByUserId(Integer userId);
}
