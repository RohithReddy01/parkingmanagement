package com.example.parkingmanagement.service.impl;

import com.example.parkingmanagement.model.Bookedslot;
import com.example.parkingmanagement.repository.IBookedslotRepository;
import com.example.parkingmanagement.repository.ISlotRepository;
import com.example.parkingmanagement.service.IBookedslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedSlot implements IBookedslotService {

    @Autowired
    private IBookedslotRepository bookedslotRepository;
    
    @Autowired
    private ISlotRepository iSlotRepository;

    @Override
    public Bookedslot bookedSlot(Bookedslot bookedslot) {
        return bookedslotRepository.save(bookedslot);
    }

}
