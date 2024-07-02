package com.example.myapi.demo.model;

public class Client {

    private long clientId;
    private String name;
    private String email;
    private String phone;

    public Client() {
    }
    public Client(long clientId, String name, String email, String phone) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public long getClientId() { return clientId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public void setClientId(long clientId) { this.clientId = clientId; }


//    id: unikalus identifikatorius (ilgasis sveikasis skaičius)
//    name: kliento vardas (tekstinis laukas)
//    email: kliento el. paštas (tekstinis laukas)
//    phone: kliento telefono numeris (tekstinis laukas)

}
