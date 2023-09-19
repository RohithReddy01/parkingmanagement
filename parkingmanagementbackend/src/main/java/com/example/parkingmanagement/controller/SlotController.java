package com.example.parkingmanagement.controller;

import com.example.parkingmanagement.model.Bookedslot;
import com.example.parkingmanagement.model.Slot;
import com.example.parkingmanagement.service.IBookedslotService;
import com.example.parkingmanagement.service.ISlotService;
import com.example.parkingmanagement.service.impl.SlotService;

import com.example.parkingmanagement.service.impl.BookedSlot;
import com.sun.source.tree.TryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class SlotController {

    @Autowired
    private ISlotService slotService;

    @Autowired
    private IBookedslotService bookedslotService;

    //ENDPOINT FOR ADDING THE SLOT
    @PostMapping("/slot")
      private ResponseEntity<?> saveSlot(@RequestBody Slot slot)
      {
    	System.out.println("Post mapping /slot");
    	System.out.println("price : " + slot.getPrice());
          try
          {
              return new ResponseEntity<>(slotService.saveSlot(slot), HttpStatus.OK);
          }
          catch(Exception e)
          {
             return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }

    //ENDPOINT FOR GETTING ALL THE SLOT
    @GetMapping("/slots")
    private ResponseEntity<?> getSlots()
    {
        try
        {
        	System.out.println(slotService.allSlots());
            return new ResponseEntity<>(slotService.allSlots(),HttpStatus.OK);
        }
        catch(Exception e)
        {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ENDPOINT FOR GETTING THE SLOT BY ID
    @GetMapping("/slot/{id}")
    private ResponseEntity<?> getSlotByID(@PathVariable Long id)
    {
      try
      {
          return new ResponseEntity<>(slotService.getById(id),HttpStatus.OK);
      }
      catch(Exception e)
      {
          return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    //ENDPOINT FOR UPDATE THE SLOT
    @PutMapping("/slot")
    private ResponseEntity<?> updateSlotById(@RequestBody Slot slot)
    {
        HashMap<String,String> res = new HashMap<String , String>();
        System.out.println("PUT mapping /slot");
        System.out.println("slot : " + slot);
//        System.out.println("slot : " + slot.getIsApproved());
//        if(slot.getIsApproved())
        try
        {
            return new ResponseEntity<>(slotService.updateSlot(slot),HttpStatus.OK);
        }
        catch(Exception e)
        {
            res.put("error" , e.getMessage());
            return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ENDPOINT FOR DELETING THE SLOT BY ID
    @DeleteMapping("/slot/{id}")
    private ResponseEntity<?> deleteSlot(@PathVariable Long id)
    {
         HashMap<String,String> res=new HashMap<>();
         System.out.println("Delete mapping /slot");
        try
        {
            slotService.deleteSlot(id);
            res.put("message","deleted successfully");
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
        catch(Exception e)
        {
             return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ENDPOINT FOR FINDING THE SLOT BY STATUS
//    @PostMapping("/status")
//    private ResponseEntity<?> findByStatus(@RequestBody HashMap<String,String> body)
//    {
//        String status=body.get("status");
//        try
//        {
//            return new ResponseEntity<>(slotService.findByStatus(status),HttpStatus.OK);
//        }
//        catch(Exception e)
//        {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //ENDPOINT FOR GETTING THE SLOT BY TYPE
    @PostMapping("/type")
    private ResponseEntity<?> findByType(@RequestBody HashMap<String,String> body)
    {
        String type=body.get("type");
        try
        {
           return new ResponseEntity<>(slotService.findByType(type),HttpStatus.OK);
        }
        catch(Exception e)
        {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ENDPOINT FOR GETTING THE SLOT BY PRICE
    @PostMapping("/price")
    private ResponseEntity<?> findByPrice(@RequestBody HashMap<String,Integer> body)
    {
        Integer price=body.get("price");
        try
        {
            return new ResponseEntity<>(slotService.findByPrice(price),HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //ENDPOINT FOR ADDING THE BOOKED USER
    @PostMapping("/bookedslot")
    private ResponseEntity<?> bookedUser(@RequestBody Bookedslot bookedslot)
    {
    	
    	System.out.println(bookedslot);
    	
        try
        {
        	Optional<Slot> slot=slotService.getById(bookedslot.getSlotId());
//        	System.out.println(slot.isEmpty());
        	if(!slot.isEmpty() && slot.isPresent()) {
        		Slot s=slot.get();
        		System.out.println(s.getStatus());
        		if(s.getStatus().equals("Booked"))
        			return new ResponseEntity<>("Slot already booked",HttpStatus.INTERNAL_SERVER_ERROR);
        		s.setStatus("Booked");
        		s.setUserId(bookedslot.getUserId());
        	}else {
        		return new ResponseEntity<>("Id not available",HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        		
            return new ResponseEntity<>(bookedslotService.bookedSlot(bookedslot),HttpStatus.OK);
        }
        catch(Exception e)
        {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/availableslot")
    private ResponseEntity<?> getAvailableSlot()
    {
        try
        {
            return new ResponseEntity<>(slotService.getAvailableSlot(),HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


    //ENDPOINT FOR GETTING ALL THE BOOKED SLOTS
    @GetMapping("/bookedslot")
    private ResponseEntity<?> getBookedSlot()
    {
        try
        {
            return new ResponseEntity<>(slotService.getBookedSlot(),HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getSlots/{id}")
    public ResponseEntity<?> getUserSlots(@PathVariable long id){
    	 try
         {
             return new ResponseEntity<>(slotService.getUserSlot(id),HttpStatus.OK);
         }
         catch(Exception e)
         {
             return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
    
//    @PutMapp

}