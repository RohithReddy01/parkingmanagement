package com.example.parkingmanagement.service.impl;

import com.example.parkingmanagement.model.Slot;
import com.example.parkingmanagement.repository.ISlotRepository;
import com.example.parkingmanagement.service.ISlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SlotService implements ISlotService {

    @Autowired
    private ISlotRepository slotRepository;

    @Override
    public List<Slot> findByStatus(String  status) {
        return slotRepository.findByStatus(status);
    }

    @Override
    public List<Slot> findByType(String type) {
        return slotRepository.findByType(type);
    }

    @Override
    public List<Slot> findByPrice(Integer price) {
        return slotRepository.findByPrice(price);
    }

    @Override
    public List<Slot> allSlots() {
       return slotRepository.findAll();
    }

    @Override
    public Slot saveSlot(Slot slot) {


        return slotRepository.save(slot);
    }

    @Override
    public void deleteSlot(Long id) {
        slotRepository.deleteById(id);
    }

    @Override
    public Slot updateSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    @Override
    public Optional<Slot> getById(Long id) {
        return slotRepository.findById(id);
    }
    
    

	@Override
	public List<Slot> getBookedSlot() {
		return slotRepository.findByStatus("Booked");
	}

	@Override
	public List<Slot> getAvailableSlot() {
		// TODO Auto-generated method stub
		return slotRepository.findByStatus("Available");
	}

	@Override
	public List<Slot> getUserSlot(long id) {
		return slotRepository.findByUserId(id);
	}
}
