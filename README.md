Restorano rezervacijų valdymo sistema

Jums reikia sukurti API, kuri valdys restorano rezervacijų sistemą. Projektas turi būti realizuotas naudojant Java, Spring Boot karkasą ir duomenų bazę. Jūsų API turėtų leisti atlikti šias operacijas:

Pridėti naują klientą
Pridėti naują rezervaciją
Gauti visų rezervacijų sąrašą
Gauti visų rezervacijų sąrašą pagal nurodytą datą
Gauti visas patvirtintas rezervacijas
Gauti visas atšauktas rezervacijas
Ieškoti rezervacijų pagal kliento ID
Atšaukti rezervaciją pagal rezervacijos ID
Patvirtinti rezervaciją

Reikalavimai:

Duomenų bazė:

Lentelės: clients ir reservations.

Klientų lentelė (clients):
id: unikalus identifikatorius (ilgasis sveikasis skaičius)
name: kliento vardas (tekstinis laukas)
email: kliento el. paštas (tekstinis laukas)
phone: kliento telefono numeris (tekstinis laukas)

Rezervacijų lentelė (reservations):
id: unikalus identifikatorius (ilgasis sveikasis skaičius)
client_id: kliento ID (ilgasis sveikasis skaičius, svetimosios raktas į clients lentelę)
reservation_date: rezervacijos data ir laikas (laiko žymė)
number_of_people: žmonių skaičius (sveikasis skaičius)
status: rezervacijos būklė (tekstinis laukas, pvz., "patvirtinta", "atšaukta", arba tusčia (sukuriama be statuso))

Servisai ir kontroleriai:
ClientService: Servisas, atsakingas už klientų valdymą.
ReservationService: Servisas, atsakingas už rezervacijų valdymą.

ClientController: Kontroleris, apdorojantis užklausas, susijusias su klientais.
ReservationController: Kontroleris, apdorojantis užklausas, susijusias su rezervacijomis.

Repository:
ClientRepository:  Klientų duomenų tvarkymui.
ReservationRepository: Rezervacijų duomenų tvarkymui.

Modeliai:
Client: Kliento duomenų modelis.
Reservation: Rezervacijos duomenų modelis.

API Endpoint'ai:
POST /clients - Pridėti naują klientą.
GET /clients - Gauti visų klientų sąrašą.
POST /reservations - Pridėti naują rezervaciją.
UPDATE /reservations/confirm/{reservationId} - Patvirtini rezervaciją.
GET /reservations - Gauti visų rezervacijų sąrašą.
GET /reservations/client/{clientId} - Gauti visas rezervacijas pagal kliento ID.
GET /reservations?date={date} - Gauti visas rezervacijas pagal datą. Tas pats endpoint, tik nurodžius datą
GET /reservations/confirmed / GET /reservations/canceled - Gauti visas patvirtintas arba atšauktas rezervacijas.
DELETE /reservations/{reservationId} - Atšaukti rezervaciją pagal rezervacijos ID.

Papildomai:
PUT /clients/{id} - Atnaujinti esamo kliento informaciją pagal kliento ID.
PUT /reservations/{id} - Atnaujinti esamos rezervacijos informaciją pagal rezervacijos ID.
GET /clients/{id} - Gauti kliento informaciją pagal kliento ID.
GET /reservations/{id} - Gauti rezervacijos informaciją pagal rezervacijos ID.
GET /clients/search - Gauti klientų sąrašą pagal vardą.
Parametras: name
Klientų ir rezervacijų suskaičiavimas:
GET /clients/count - Gauti bendrą klientų skaičių.
GET /reservations/count - Gauti bendrą rezervacijų skaičių.









