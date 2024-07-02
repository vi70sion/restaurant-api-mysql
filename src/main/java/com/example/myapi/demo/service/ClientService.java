package com.example.myapi.demo.service;

import com.example.myapi.demo.model.Client;
import com.example.myapi.demo.repository.DatabaseRepository;
import java.sql.SQLException;
import java.util.List;

public class ClientService {

    DatabaseRepository dbRepository = new DatabaseRepository();

    public ClientService() throws SQLException {
    }

    public List<Client> getAllClientsList() throws SQLException {
        return dbRepository.getAllClientsList();
    }

    public Client getCLientById(long id) throws SQLException {
        return dbRepository.getCLientById(id);
    }

    public Client addClient(Client client) throws SQLException {
        return dbRepository.addClient(client);
    }

    public Client updateClient(long id, Client client) throws SQLException {
        return (dbRepository.updateClient(id, client) == null) ? dbRepository.addClient(client): client;
    }

    public int getClientsCount () throws SQLException {
        return dbRepository.getClientsCount();
    }

}
