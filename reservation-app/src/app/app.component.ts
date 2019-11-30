import {Component} from '@angular/core';
import {ReservationService} from "./reservation.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'reservation-app';

  constructor(private reservationService: ReservationService) {
  }

  rooms: Room[];
  roomSearchForm: FormGroup;
  currentCheckInVal: string;
  currentCheckOutVal: string;
  currentPrice: number;
  currentRoomNumber: number;

  ngOnInit() {
    this.roomSearchForm = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl(''),
      roomNumber: new FormControl('')
    });

    this.roomSearchForm.valueChanges.subscribe(form => {

      this.currentCheckInVal = form.checkin;
      this.currentCheckOutVal = form.checkout;

      if (form.roomNumber) {
        let roomValues: string[] = form.roomNumber.split('|');
        this.currentRoomNumber = Number(roomValues[0]);
        this.currentPrice = Number(roomValues[1]);
      }

      console.log(this.currentCheckInVal);
      console.log(this.currentCheckOutVal);
      console.log(this.currentRoomNumber);
      console.log(this.currentPrice);

    });

    this.rooms = [
      new Room('127', '127', '150'),
      new Room('140', '140', '200'),
      new Room('200', '200', '250')
    ];
  }
}

export class Room {

  id: string;
  roomNumber: string;
  price: string;

  constructor(id: string, roomNumber: string, price: string) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.price = price;
  }
}
