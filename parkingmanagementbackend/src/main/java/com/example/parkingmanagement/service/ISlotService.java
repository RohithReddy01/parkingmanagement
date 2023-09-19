package com.example.parkingmanagement.service;

import com.example.parkingmanagement.model.Slot;

import java.util.List;
import java.util.Optional;

public interface ISlotService {

    List<Slot> findByStatus(String status);

    List<Slot> findByType(String type);

    List<Slot> findByPrice(Integer price);

     List<Slot> allSlots();

    Slot saveSlot(Slot slot);

    void deleteSlot(Long id);

    Slot updateSlot(Slot slot);

    Optional<Slot> getById(Long id);

	List<Slot> getBookedSlot();

	List<Slot> getAvailableSlot();

	List<Slot> getUserSlot(long id);

}
