package com.example.parkingmanagement.repository;

import com.example.parkingmanagement.model.Bookedslot;
import com.example.parkingmanagement.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISlotRepository extends JpaRepository<Slot,Long> {
//    List<Slot> findByStatus(String status);

    List<Slot> findByType(String type);

    List<Slot> findByPrice(Integer price);

	List<Slot> findByStatus(String status);

	List<Slot> findByUserId(long id);
}
