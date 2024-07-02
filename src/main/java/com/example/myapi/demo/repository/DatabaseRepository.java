package com.example.myapi.demo.repository;

import com.example.myapi.demo.model.Client;
import com.example.myapi.demo.model.Reservation;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static com.example.myapi.demo.Constants.*;

public class DatabaseRepository {

    private Connection _connection;
    private List<Client> clientList;
    private List<Reservation> reservationList;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DatabaseRepository() throws SQLException {
        _connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    //------------CLIENTS----------------------

    public List<Client> getAllClientsList() throws SQLException {
        clientList = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        PreparedStatement statement = _connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getInt("client_id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            clientList.add(new Client(id, name, email, phone));
        }
        return clientList;
    }

    public Client getCLientById(long id) throws SQLException {
        String sql = "SELECT * FROM clients WHERE client_id = ?";
        PreparedStatement statement = _connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        boolean hasResults = resultSet.next();
        if(!hasResults) return null;
        int client_id = resultSet.getInt("client_id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        return (new Client(client_id, name, email, phone));
    }

    public Client addClient(Client client) throws SQLException {
        String sql = "INSERT INTO clients ( name, email, phone) VALUES (?,?,?)";
        PreparedStatement statement = _connection.prepareStatement(sql);
        statement.setString(1, client.getName());
        statement.setString(2, client.getEmail());
        statement.setString(3, client.getPhone());
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) return client;
        else return null;
    }

    public Client updateClient(long id, Client client) throws SQLException {
        client.setClientId(id);
        String updateSql = "UPDATE clients SET name = ?, email = ?, phone = ? WHERE client_id = ?";
        PreparedStatement statement = _connection.prepareStatement(updateSql);
        statement.setString(1, client.getName());
        statement.setString(2, client.getEmail());
        statement.setString(3, client.getPhone());
        statement.setLong(4, id);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) return client;
        else return null;
    }

    public int getClientsCount() throws SQLException {
        int rowsCount = 0;
        String sql = "SELECT COUNT(*) AS rows_count FROM clients";
        PreparedStatement statement = _connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) rowsCount = resultSet.getInt("rows_count");
        return rowsCount;
    }

    //------------RESERVATIONS----------------------

    public List<Reservation> getReservationsList() throws SQLException {
        reservationList = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        PreparedStatement statement = _connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("reservation_id");
            long clientId = resultSet.getLong("client_id");
            LocalDateTime reservDate = LocalDateTime.parse(resultSet.getString("reservation_date"), formatter);
            int people = resultSet.getInt("number_of_people");
            String status = resultSet.getString("status");
            reservationList.add(new Reservation(id, clientId, reservDate, people, status));
        }
        return reservationList;
    }

    public List<Reservation> getReservationsListByDate(String lookingDate) throws SQLException {
        reservationList = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE DATE(reservation_date) = ?";
        PreparedStatement statement = _connection.prepareStatement(sql);
        statement.setString(1, lookingDate);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("reservation_id");
            long clientId = resultSet.getLong("client_id");
            LocalDateTime reservDate = LocalDateTime.parse(resultSet.getString("reservation_date"), formatter);
            int people = resultSet.getInt("number_of_people");
            String status = resultSet.getString("status");
            reservationList.add(new Reservation(id, clientId, reservDate, people, status));
        }
        return reservationList;
    }

    public Reservation addReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservations ( client_id, reservation_date, number_of_people, status) VALUES (?,?,?,?)";
        PreparedStatement statement = _connection.prepareStatement(sql);
        statement.setLong(1, reservation.getClientId());
        statement.setString(2, reservation.getReservationDateTime().format(formatter));
        statement.setInt(3, reservation.getNumberOfPeople());
        statement.setString(4, reservation.getStatus());
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) return reservation;
        else return null;
    }

    public Boolean confirmReservation(long id) throws SQLException {
        String updateSql = "UPDATE reservations SET status = ? WHERE reservation_id = ?";
        PreparedStatement statement = _connection.prepareStatement(updateSql);
        statement.setString(1, "confirmed");
        statement.setLong(2, id);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) return true;
        return false;
    }

    public Boolean cancelReservation(long id) throws SQLException {
        String updateSql = "UPDATE reservations SET status = ? WHERE reservation_id = ?";
        PreparedStatement statement = _connection.prepareStatement(updateSql);
        statement.setString(1, "canceled");
        statement.setLong(2, id);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) return true;
        return false;
    }

    public List<Reservation> getReservationsByClient(long id) throws SQLException {
        reservationList = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE client_id = ?";
        PreparedStatement statement = _connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int reservationId = resultSet.getInt("reservation_id");
            long clientId = resultSet.getLong("client_id");
            LocalDateTime reservDate = LocalDateTime.parse(resultSet.getString("reservation_date"), formatter);
            int people = resultSet.getInt("number_of_people");
            String status = resultSet.getString("status");
            reservationList.add(new Reservation(reservationId, clientId, reservDate, people, status));
        }
        return reservationList;
    }

    public List<Reservation> getReservationsByStatus(String status) throws SQLException {
        reservationList = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE status = ?";
        PreparedStatement statement = _connection.prepareStatement(sql);
        statement.setString(1, status);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int reservationId = resultSet.getInt("reservation_id");
            long clientId = resultSet.getLong("client_id");
            LocalDateTime reservDate = LocalDateTime.parse(resultSet.getString("reservation_date"), formatter);
            int people = resultSet.getInt("number_of_people");
            String resStatus = resultSet.getString("status");
            reservationList.add(new Reservation(reservationId, clientId, reservDate, people, resStatus));
        }
        return reservationList;
    }

    public boolean deleteReservations(long id) throws SQLException {
        String deleteSql = "DELETE FROM reservations WHERE reservation_id = ?";
        PreparedStatement statement = _connection.prepareStatement(deleteSql);
        statement.setLong(1, id);
        int rowsDeleted = statement.executeUpdate();
        return (rowsDeleted > 0) ? true : false;
    }

    public Reservation updateReservation(long id, Reservation reservation) throws SQLException {
        String updateSql = "UPDATE reservations SET client_id = ?, reservation_date = ?, number_of_people = ?, status = ? " +
                "WHERE reservation_id = ?";
        PreparedStatement statement = _connection.prepareStatement(updateSql);
        statement.setLong(1, reservation.getClientId());
        statement.setString(2, reservation.getReservationDateTime().format(formatter));
        statement.setInt(3, reservation.getNumberOfPeople());
        statement.setString(4, reservation.getStatus());
        statement.setLong(5, id);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) return reservation;
        return null;
    }

}
