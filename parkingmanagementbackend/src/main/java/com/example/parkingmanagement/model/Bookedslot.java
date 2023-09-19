package com.example.parkingmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class Bookedslot {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long bookingId;
//        private String name;
//        private Boolean isApproved;
//        private String status;
//        private String type;
//        private String price;
        
        private long slotId;
        private long userId;
        
		public Long getBookingId() {
			return bookingId;
		}

		public void setBookingId(Long bookingId) {
			this.bookingId = bookingId;
		}

		public long getSlotId() {
			return slotId;
		}

		public void setSlotId(long slotId) {
			this.slotId = slotId;
		}

		public long getUserId() {
			return userId;
		}

		public void setUserId(long userId) {
			this.userId = userId;
		}

		@Override
		public String toString() {
			return "Bookedslot [bookingId=" + bookingId + ", slotId=" + slotId + ", userId=" + userId + "]";
		}
        
        
      
        
    }


