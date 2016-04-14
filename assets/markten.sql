
drop table if exists markten;

CREATE TABLE if not exists markten (
    Dag VARCHAR(9) CHARACTER SET utf8,
    Plaats VARCHAR(36) CHARACTER SET utf8,
    Begintijd VARCHAR(5) CHARACTER SET utf8,
    Eindtijd VARCHAR(8) CHARACTER SET utf8,
    Straatnaam VARCHAR(23) CHARACTER SET utf8,
    Longitude double,
    Latitude double
);
INSERT INTO markten VALUES ('Maandag',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO markten VALUES ('Dinsdag','Binnenrotte Centrummarkt',' 8.00','17:30:00','Binnenrotte',4.484583,51.922597);
INSERT INTO markten VALUES ('Dinsdag','Boekenmarkt Wijdekerkstraat         ',' 9.30','16:00:00','Wijdekerkstraat',4.484598,51.920803);
INSERT INTO markten VALUES ('Dinsdag','Biologische markt Eendrachtsplein',' 8.00','13:00:00','Eendrachtsplein',4.473419,51.916709);
INSERT INTO markten VALUES ('Woensdag','Afrikaanderplein Rotterdam-Zuid',' 8.00','17:30:00','Afrikaanderplein',4.503937,51.899699);
INSERT INTO markten VALUES ('Woensdag','Ommoord',' 8.00','17:30:00','Hesseplaats',4.552463,51.963147);
INSERT INTO markten VALUES ('Donderdag','IJsselmonde',' 8.00','17:30:00','Groeninx van Zoelenlaan',4.557918,51.884850);
INSERT INTO markten VALUES ('Donderdag','Visserijplein Rotterdam-West',' 8.00','17:30:00','Visserijplein',4.441507,51.911926);
INSERT INTO markten VALUES ('Vrijdag','Alexanderpolder',' 8.00','17:30:00','Samuel Esmeijerplein',4.548509,51.940522);
INSERT INTO markten VALUES ('Vrijdag','Schiebroek',' 8.00','17:30:00','Rododendronplein',4.473388,51.960033);
INSERT INTO markten VALUES ('Vrijdag','Overschie',' 8.00','17:30:00','Van Noortwijckstraat',4.427014,51.933016);
INSERT INTO markten VALUES ('Vrijdag','Asterlo Zuidwijk',' 8.00','17:30:00','Asterlo',4.483261,51.873836);
INSERT INTO markten VALUES ('Zaterdag','Binnenrotte Centrummarkt',' 8.00','17:00:00','Binnenrotte',4.484583,51.922597);
INSERT INTO markten VALUES ('Zaterdag','Afrikaanderplein Rotterdam-Zuid',' 8.00','17:00:00','Afrikaanderplein',4.503937,51.899699);
INSERT INTO markten VALUES ('Zaterdag','Boekenmarkt Wijdekerkstraat  ',' 9.00','16:00:00','Wijdekerkstraat',4.484598,51.920803);
INSERT INTO markten VALUES ('Zaterdag','Visserijplein Rotterdam-West',' 8.00','17:00:00','Visserijplein',4.441507,51.911926);
INSERT INTO markten VALUES ('Zondag','Zondagsmarkt','12.00','17:00:00','Binnenrotte',4.484583,51.922597);

select * from markten