package com.example.parkingmanageent.reppo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.parkingmanagement.model.Slot;
import com.example.parkingmanagement.repository.ISlotRepository;
import com.example.parkingmanagement.service.impl.SlotService;

@SpringBootTest
public class Slotservicetest {
	@MockBean
	private ISlotRepository slotrepo;
	
	@Autowired
	private SlotService sservice;
	
	@Test
	public void saveSlotTest() {
		Slot s=new Slot();
		s.setId(1L);
		s.setName("Johnnn");
		s.setStatus("available");
		s.setType("Bike");
		s.setPrice(200);
		when(slotrepo.save(s)).thenReturn(s);
		assertEquals(s,sservice.saveSlot(s));

}

}
