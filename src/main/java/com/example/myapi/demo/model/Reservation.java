package com.example.myapi.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class Reservation {
    @JsonFormat
    private long reservationId;
    @JsonFormat
    private long clientId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDateTime;
    @JsonFormat
    private int numberOfPeople;
    @JsonFormat
    private String status;

    public Reservation() {
    }

    public Reservation(long reservationId, long clientId, LocalDateTime reservationDateTime, int numberOfPeople, String status) {
        this.reservationId = reservationId;
        this.clientId = clientId;
        this.reservationDateTime = reservationDateTime;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
    }

    public long getReservationId() { return reservationId; }
    public long getClientId() { return clientId; }
    public LocalDateTime getReservationDateTime() { return reservationDateTime; }
    public int getNumberOfPeople() { return numberOfPeople; }
    public String getStatus() {  return status; }
    public void setReservationId(long reservationId) { this.reservationId = reservationId; }



    //    id: unikalus identifikatorius (ilgasis sveikasis skaičius)
//    client_id: kliento ID (ilgasis sveikasis skaičius, svetimosios raktas į clients lentelę)
//    reservation_date: rezervacijos data ir laikas (laiko žymė)
//    number_of_people: žmonių skaičius (sveikasis skaičius)
//    status: rezervacijos būklė (tekstinis laukas, pvz., "patvirtinta", "atšaukta", arba tusčia (sukuriama be statuso))

}
