package com.example.myapi.demo.service;

import com.example.myapi.demo.model.Reservation;
import com.example.myapi.demo.repository.DatabaseRepository;
import java.sql.SQLException;
import java.util.List;

public class ReservationService {


    DatabaseRepository dbRepository = new DatabaseRepository();
    public ReservationService() throws SQLException {
    }

    public List<Reservation> getReservationsList(String date) throws SQLException {
        return (date.equals("")) ? dbRepository.getReservationsList():
                dbRepository.getReservationsListByDate(date);
    }

    public Reservation addReservation(Reservation reservation) throws SQLException {
        return dbRepository.addReservation(reservation);
    }

    public Boolean confirmReservation(long reservationId) throws SQLException {
        return dbRepository.confirmReservation(reservationId);
    }

    public Boolean cancelReservation(long reservationId) throws SQLException {
        return dbRepository.cancelReservation(reservationId);
    }

    public List<Reservation> getReservationsByClient (long clientId) throws SQLException {
        return dbRepository.getReservationsByClient(clientId);
    }

    public List<Reservation> getReservationsByStatus (String status) throws SQLException {
        return dbRepository.getReservationsByStatus(status);
    }

    public boolean deleteReservations (long reservationId) throws SQLException {
        return dbRepository.deleteReservations(reservationId);
    }

    public Reservation updateReservation(long id, Reservation reservation) throws SQLException {
        return (dbRepository.updateReservation(id, reservation) == null) ? dbRepository.addReservation(reservation) : reservation;
    }

}
