package com.example.parkingmanagement.repository;

import com.example.parkingmanagement.model.Bookedslot;
import com.example.parkingmanagement.service.impl.BookedSlot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookedslotRepository extends JpaRepository<Bookedslot,Long> {

}
