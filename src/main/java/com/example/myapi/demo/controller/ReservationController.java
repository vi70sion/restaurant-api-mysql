package com.example.myapi.demo.controller;

import com.example.myapi.demo.model.Reservation;
import com.example.myapi.demo.service.ReservationService;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {


    ReservationService reservationService = new ReservationService();
    public ReservationController() throws SQLException {
    }

    //reservations?date={date}
    @GetMapping
    public List<Reservation> getReservationsList (@RequestParam String date) throws SQLException {
        return reservationService.getReservationsList(date);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) throws SQLException {
        return reservationService.addReservation(reservation);
    }

    @PatchMapping("/confirm/{reservationId}")
    public Boolean confirmReservation(@PathVariable long reservationId) throws SQLException {
        return reservationService.confirmReservation(reservationId);
    }

    @PatchMapping("/cancel/{reservationId}")
    public Boolean cancelReservation(@PathVariable long reservationId) throws SQLException {
        return reservationService.cancelReservation(reservationId);
    }

    @GetMapping("/client/{clientId}")
    public List<Reservation> getReservationsByClient (@PathVariable long clientId) throws SQLException {
        return reservationService.getReservationsByClient(clientId);
    }

    @GetMapping("/confirmed")
    public List<Reservation> getConfirmedReservations () throws SQLException {
        return reservationService.getReservationsByStatus("confirmed");
    }

    @GetMapping("/canceled")
    public List<Reservation> getCanceledReservations () throws SQLException {
        return reservationService.getReservationsByStatus("canceled");
    }

    @DeleteMapping("/{reservationId}")
    public boolean deleteReservations (@PathVariable long reservationId) throws SQLException {
        return reservationService.deleteReservations(reservationId);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable long id, @RequestBody Reservation reservation) throws SQLException {
        return reservationService.updateReservation(id, reservation);
    }

    @GetMapping("/count")
    public int getReservationCount () throws SQLException {
        return reservationService.getReservationCount();
    }



}
