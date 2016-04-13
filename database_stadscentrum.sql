SET SQL_MODE=ANSI_QUOTES;

CREATE TABLE buurtprobleem_fietsendiefstal (
    wijk VARCHAR(30),
    "2006" NUMERIC(3, 1),
    "2007" NUMERIC(3, 1),
    "2008" NUMERIC(3, 1),
    "2009" NUMERIC(3, 1),
    "2011" NUMERIC(3, 1)
);

CREATE TABLE buurtprobleem_diefstal_uit_de_auto (
    wijk VARCHAR(30),
    "2006" NUMERIC(3, 1),
    "2007" NUMERIC(3, 1),
    "2008" NUMERIC(3, 1),
    "2009" NUMERIC(3, 1),
    "2011" NUMERIC(3, 1)
);

CREATE TABLE buurtprobleem_beschadiging_aan_of_diefstal_vanaf_de_auto (
    wijk VARCHAR(30),
    "2006" NUMERIC(3, 1),
    "2007" NUMERIC(3, 1),
    "2008" NUMERIC(3, 1),
    "2009" NUMERIC(3, 1),
    "2011" NUMERIC(3, 1)
);

CREATE TABLE slachtofferschap_autodiefstal (
    wijk VARCHAR(30),
    "2006" NUMERIC(2, 1),
    "2007" NUMERIC(2, 1),
    "2008" NUMERIC(2, 1),
    "2009" NUMERIC(2, 1),
    "2011" NUMERIC(2, 1)
);

CREATE TABLE slachtofferschap_diefstal_uit_de_auto (
    wijk VARCHAR(30),
    "2006" NUMERIC(3, 1),
    "2007" NUMERIC(3, 1),
    "2008" NUMERIC(3, 1),
    "2009" NUMERIC(3, 1),
    "2011" NUMERIC(3, 1)
);

CREATE TABLE slachtofferschap_diefstal_vanaf_de_auto (
    wijk VARCHAR(30),
    "2006" NUMERIC(3, 1),
    "2007" NUMERIC(3, 1),
    "2008" NUMERIC(3, 1),
    "2009" NUMERIC(3, 1),
    "2011" NUMERIC(3, 1)
);

CREATE TABLE slachtofferschap_fietsendiefstal (
    wijk VARCHAR(30),
    "2006" NUMERIC(3, 1),
    "2007" NUMERIC(2, 1),
    "2008" NUMERIC(3, 1),
    "2009" NUMERIC(2, 1),
    "2011" NUMERIC(2, 1)
);

CREATE TABLE slachtofferschap_overige_diefstal (
    wijk VARCHAR(30),
    "2006" NUMERIC(2, 1),
    "2007" NUMERIC(2, 1),
    "2008" NUMERIC(2, 1),
    "2009" NUMERIC(2, 1),
    "2011" NUMERIC(2, 1)
);

CREATE TABLE buurtprobleem_bedreiging (
    wijk VARCHAR(30),
    "2006" NUMERIC(2, 1),
    "2007" NUMERIC(2, 1),
    "2008" NUMERIC(2, 1),
    "2009" NUMERIC(3, 1),
    "2011" NUMERIC(2, 1)
);

CREATE TABLE buurtprobleem_geweldsdelicten (
    wijk VARCHAR(30),
    "2006" NUMERIC(2, 1),
    "2007" NUMERIC(3, 1),
    "2008" NUMERIC(3, 1),
    "2009" NUMERIC(3, 1),
    "2011" NUMERIC(2, 1)
);

CREATE TABLE buurtprobleem_tasjesroof (
    wijk VARCHAR(30),
    "2006" NUMERIC(2, 1),
    "2007" NUMERIC(2, 1),
    "2008" NUMERIC(3, 1),
    "2009" NUMERIC(2, 1),
    "2011" NUMERIC(2, 1)
);

CREATE TABLE slachtofferschap_tasjesroof_met_geweld (
    wijk VARCHAR(30),
    "2006" NUMERIC(2, 1),
    "2007" NUMERIC(2, 1),
    "2008" NUMERIC(2, 1),
    "2009" NUMERIC(2, 1),
    "2011" NUMERIC(2, 1)
);

CREATE TABLE slachtofferschap_tasjesroof_zonder_geweld (
    wijk VARCHAR(30),
    "2006" NUMERIC(2, 1),
    "2007" NUMERIC(2, 1),
    "2008" NUMERIC(2, 1),
    "2009" NUMERIC(2, 1),
    "2011" NUMERIC(2, 1)
);

CREATE TABLE slachtofferschap_bedreiging_met_geweld (
    wijk VARCHAR(30),
    "2006" NUMERIC(2, 1),
    "2007" NUMERIC(2, 1),
    "2008" NUMERIC(2, 1),
    "2009" NUMERIC(2, 1),
    "2011" NUMERIC(2, 1)
);

CREATE TABLE slachtofferschap_mishandeling (
     wijk VARCHAR(30),
    "2006" NUMERIC(2, 1),
    "2007" NUMERIC(2, 1),
    "2008" NUMERIC(2, 1),
    "2009" NUMERIC(2, 1),
    "2011" NUMERIC(2, 1)
);


CREATE TABLE tevredenheid_met_het_wonen_in_de_buurt (
     wijk VARCHAR(30),
    "2006" NUMERIC(3, 1),
    "2007" NUMERIC(3, 1),
    "2008" NUMERIC(3, 1),
    "2009" NUMERIC(3, 1),
    "2011" NUMERIC(3, 1)
);




INSERT INTO buurtprobleem_fietsendiefstal VALUES ('Stadsdriehoek/C.S. Kwartier',25.1,22.7,25,22.5,24.2);
INSERT INTO buurtprobleem_fietsendiefstal VALUES ('Oude Westen',23.1,25.3,29.1,26.6,22.2);
INSERT INTO buurtprobleem_fietsendiefstal VALUES ('Cool/Nieuwe Werk/Dijkzigt',30.7,20.6,29.2,28.8,21.6);
INSERT INTO buurtprobleem_fietsendiefstal VALUES ('Stadscentrum',25.2,23.1,27.3,25.1,23.1);
INSERT INTO buurtprobleem_fietsendiefstal VALUES ('Rotterdam',18.7,18.2,20,18.5,18.2);

INSERT INTO buurtprobleem_diefstal_uit_de_auto VALUES ('Stadsdriehoek/C.S. Kwartier',32.1,29.9,25.8,25.3,18.3);
INSERT INTO buurtprobleem_diefstal_uit_de_auto VALUES ('Oude Westen',20.6,23.8,21.2,19.8,12.6);
INSERT INTO buurtprobleem_diefstal_uit_de_auto VALUES ('Cool/Nieuwe Werk/Dijkzigt',34.9,26.9,22.7,31.6,20.2);
INSERT INTO buurtprobleem_diefstal_uit_de_auto VALUES ('Stadscentrum',29.3,27.2,23.7,24.8,16.9);
INSERT INTO buurtprobleem_diefstal_uit_de_auto VALUES ('Rotterdam',16.6,15.8,15.4,15.7,14.1);

INSERT INTO buurtprobleem_beschadiging_aan_of_diefstal_vanaf_de_auto VALUES ('Stadsdriehoek/C.S. Kwartier',23.4,23.9,20.6,19.1,17.1);
INSERT INTO buurtprobleem_beschadiging_aan_of_diefstal_vanaf_de_auto VALUES ('Oude Westen',22.3,19.5,22.1,21.2,15.1);
INSERT INTO buurtprobleem_beschadiging_aan_of_diefstal_vanaf_de_auto VALUES ('Cool/Nieuwe Werk/Dijkzigt',29.9,20.6,23.2,25.1,19.6);
INSERT INTO buurtprobleem_beschadiging_aan_of_diefstal_vanaf_de_auto VALUES ('Stadscentrum',24.6,21.7,21.5,21,17);
INSERT INTO buurtprobleem_beschadiging_aan_of_diefstal_vanaf_de_auto VALUES ('Rotterdam',16.9,16.8,18.2,18.2,17.2);

INSERT INTO slachtofferschap_autodiefstal VALUES ('Stadsdriehoek/C.S. Kwartier',0,1,2.4,1,0.3);
INSERT INTO slachtofferschap_autodiefstal VALUES ('Oude Westen',0,1.6,1.7,0,0.3);
INSERT INTO slachtofferschap_autodiefstal VALUES ('Cool/Nieuwe Werk/Dijkzigt',1,0.7,2.5,2.6,0.4);
INSERT INTO slachtofferschap_autodiefstal VALUES ('Stadscentrum',0.3,1.1,2.2,1,0.3);
INSERT INTO slachtofferschap_autodiefstal VALUES ('Rotterdam',0.7,0.5,1.2,1,0.6);

INSERT INTO slachtofferschap_diefstal_uit_de_auto VALUES ('Stadsdriehoek/C.S. Kwartier',7.7,8.7,8.5,6.3,4.8);
INSERT INTO slachtofferschap_diefstal_uit_de_auto VALUES ('Oude Westen',6.2,7.2,9.5,6.2,3.8);
INSERT INTO slachtofferschap_diefstal_uit_de_auto VALUES ('Cool/Nieuwe Werk/Dijkzigt',10.5,11.4,13.9,10.4,8.2);
INSERT INTO slachtofferschap_diefstal_uit_de_auto VALUES ('Stadscentrum',8.5,8.8,9.9,7.1,5.1);
INSERT INTO slachtofferschap_diefstal_uit_de_auto VALUES ('Rotterdam',7.1,6.8,5.8,4.9,4);

INSERT INTO slachtofferschap_diefstal_vanaf_de_auto VALUES ('Stadsdriehoek/C.S. Kwartier',38,42.2,22.3,20.4,15.4);
INSERT INTO slachtofferschap_diefstal_vanaf_de_auto VALUES ('Oude Westen',33.8,31,33,21.3,15);
INSERT INTO slachtofferschap_diefstal_vanaf_de_auto VALUES ('Cool/Nieuwe Werk/Dijkzigt',36,29.5,35.4,23,20.8);
INSERT INTO slachtofferschap_diefstal_vanaf_de_auto VALUES ('Stadscentrum',36,36.3,28,21.2,16.3);
INSERT INTO slachtofferschap_diefstal_vanaf_de_auto VALUES ('Rotterdam',29.6,30.5,25.2,15.7,14.7);

INSERT INTO slachtofferschap_fietsendiefstal VALUES ('Stadsdriehoek/C.S. Kwartier',5.9,6.7,7.6,5.5,4.8);
INSERT INTO slachtofferschap_fietsendiefstal VALUES ('Oude Westen',10.1,6.5,9.6,6.2,8);
INSERT INTO slachtofferschap_fietsendiefstal VALUES ('Cool/Nieuwe Werk/Dijkzigt',7.6,8.5,10.8,6.5,3.5);
INSERT INTO slachtofferschap_fietsendiefstal VALUES ('Stadscentrum',7.2,7,8.9,5.9,5.4);
INSERT INTO slachtofferschap_fietsendiefstal VALUES ('Rotterdam',5.3,5.2,9.1,3.9,5);

INSERT INTO slachtofferschap_overige_diefstal VALUES ('Stadsdriehoek/C.S. Kwartier',3,5.2,3.6,2.4,3.8);
INSERT INTO slachtofferschap_overige_diefstal VALUES ('Oude Westen',2.1,1.5,3.1,2.9,1.5);
INSERT INTO slachtofferschap_overige_diefstal VALUES ('Cool/Nieuwe Werk/Dijkzigt',2.9,2.8,2.7,2.3,2.2);
INSERT INTO slachtofferschap_overige_diefstal VALUES ('Stadscentrum',2.6,3.4,3.3,2.5,2.7);
INSERT INTO slachtofferschap_overige_diefstal VALUES ('Rotterdam',2.6,3.7,3.3,3,3.2);


INSERT INTO buurtprobleem_bedreiging VALUES ('Stadsdriehoek/C.S. Kwartier',5.8,3.2,5.4,3.4,6.2);
INSERT INTO buurtprobleem_bedreiging VALUES ('Oude Westen',3.7,5.3,9.6,10.1,7.3);
INSERT INTO buurtprobleem_bedreiging VALUES ('Cool/Nieuwe Werk/Dijkzigt',7,6.1,4.9,8.1,4.3);
INSERT INTO buurtprobleem_bedreiging VALUES ('Stadscentrum',5.1,4.5,6.7,6.5,6.1);
INSERT INTO buurtprobleem_bedreiging VALUES ('Rotterdam',3.3,3.1,4.5,4.8,5.8);

INSERT INTO buurtprobleem_geweldsdelicten VALUES ('Stadsdriehoek/C.S. Kwartier',5.2,4,6.2,3.9,4.1);
INSERT INTO buurtprobleem_geweldsdelicten VALUES ('Oude Westen',7.7,10.7,16.7,11.7,5.6);
INSERT INTO buurtprobleem_geweldsdelicten VALUES ('Cool/Nieuwe Werk/Dijkzigt',5.9,5,4.8,11.4,4.4);
INSERT INTO buurtprobleem_geweldsdelicten VALUES ('Stadscentrum',5.8,6.4,9.3,8,4.6);
INSERT INTO buurtprobleem_geweldsdelicten VALUES ('Rotterdam',3.9,3.7,4.6,4.7,5.9);

INSERT INTO buurtprobleem_tasjesroof VALUES ('Stadsdriehoek/C.S. Kwartier',5.9,4.8,5.8,2.2,4.4);
INSERT INTO buurtprobleem_tasjesroof VALUES ('Oude Westen',5.5,5.3,10.2,5.3,4.8);
INSERT INTO buurtprobleem_tasjesroof VALUES ('Cool/Nieuwe Werk/Dijkzigt',6.7,3.9,5.2,4.7,5.9);
INSERT INTO buurtprobleem_tasjesroof VALUES ('Stadscentrum',5.9,4.8,7.2,3.7,4.8);
INSERT INTO buurtprobleem_tasjesroof VALUES ('Rotterdam',3.2,2.8,3.4,3,3.4);

INSERT INTO slachtofferschap_tasjesroof_met_geweld VALUES ('Stadsdriehoek/C.S. Kwartier',1.3,0,0,0.4,0.3);
INSERT INTO slachtofferschap_tasjesroof_met_geweld VALUES ('Oude Westen',0.7,1,0,0,0);
INSERT INTO slachtofferschap_tasjesroof_met_geweld VALUES ('Cool/Nieuwe Werk/Dijkzigt',0.5,1.2,0,0.6,0.5);
INSERT INTO slachtofferschap_tasjesroof_met_geweld VALUES ('Stadscentrum',0.9,0.6,0.3,0.3,0.2);
INSERT INTO slachtofferschap_tasjesroof_met_geweld VALUES ('Rotterdam',0.5,0.5,0.3,0.2,0.2);

INSERT INTO slachtofferschap_tasjesroof_zonder_geweld VALUES ('Stadsdriehoek/C.S. Kwartier',1.6,4,1.7,1.6,1.4);
INSERT INTO slachtofferschap_tasjesroof_zonder_geweld VALUES ('Oude Westen',1.9,1,1.9,2.1,1.6);
INSERT INTO slachtofferschap_tasjesroof_zonder_geweld VALUES ('Cool/Nieuwe Werk/Dijkzigt',3.5,2.7,2,2.6,1.2);
INSERT INTO slachtofferschap_tasjesroof_zonder_geweld VALUES ('Stadscentrum',2.2,2.7,1.8,2,1.4);
INSERT INTO slachtofferschap_tasjesroof_zonder_geweld VALUES ('Rotterdam',1.3,1.3,1.3,1.2,1.2);

INSERT INTO slachtofferschap_bedreiging_met_geweld VALUES ('Stadsdriehoek/C.S. Kwartier',4.3,2.8,3,3.8,3.6);
INSERT INTO slachtofferschap_bedreiging_met_geweld VALUES ('Oude Westen',3.4,4.9,2.2,3.3,2.2);
INSERT INTO slachtofferschap_bedreiging_met_geweld VALUES ('Cool/Nieuwe Werk/Dijkzigt',4.3,4.3,4.7,2.7,1.5);
INSERT INTO slachtofferschap_bedreiging_met_geweld VALUES ('Stadscentrum',4,3.8,3.1,3.4,2.7);
INSERT INTO slachtofferschap_bedreiging_met_geweld VALUES ('Rotterdam',2.8,2.6,2.8,2.9,3.1);

INSERT INTO slachtofferschap_mishandeling VALUES ('Stadsdriehoek/C.S. Kwartier',1,1.7,0.6,0.6,0.5);
INSERT INTO slachtofferschap_mishandeling VALUES ('Oude Westen',0.8,0,1.3,0.2,0.6);
INSERT INTO slachtofferschap_mishandeling VALUES ('Cool/Nieuwe Werk/Dijkzigt',0.5,1.1,1.1,1.1,0.9);
INSERT INTO slachtofferschap_mishandeling VALUES ('Stadscentrum',0.9,1,0.9,0.6,0.6);
INSERT INTO slachtofferschap_mishandeling VALUES ('Rotterdam',0.4,0.5,0.8,0.4,0.8);


INSERT INTO tevredenheid_met_het_wonen_in_de_buurt VALUES ('Stadsdriehoek/C.S. Kwartier',89.6,90.8,81.8,77.6,87.2);
INSERT INTO tevredenheid_met_het_wonen_in_de_buurt VALUES ('Oude Westen',74.1,69.8,63.6,60.9,76);
INSERT INTO tevredenheid_met_het_wonen_in_de_buurt VALUES ('Cool/Nieuwe Werk/Dijkzigt',86.7,87.9,81.5,79.2,83.6);
INSERT INTO tevredenheid_met_het_wonen_in_de_buurt VALUES ('Stadscentrum',84.4,83.1,75.7,72.5,82.9);
INSERT INTO tevredenheid_met_het_wonen_in_de_buurt VALUES ('Rotterdam',79.4,79.9,75,73.4,74.4);




SELECT * FROM buurtprobleem_fietsendiefstal;
SELECT * FROM buurtprobleem_diefstal_uit_de_auto;
SELECT * FROM buurtprobleem_beschadiging_aan_of_diefstal_vanaf_de_auto;
SELECT * FROM slachtofferschap_autodiefstal;
SELECT * FROM slachtofferschap_diefstal_uit_de_auto;
SELECT * FROM slachtofferschap_diefstal_vanaf_de_auto;
SELECT * FROM slachtofferschap_fietsendiefstal;
SELECT * FROM slachtofferschap_overige_diefstal;

SELECT * FROM buurtprobleem_bedreiging;
SELECT * FROM buurtprobleem_geweldsdelicten;
SELECT * FROM buurtprobleem_tasjesroof;
SELECT * FROM slachtofferschap_tasjesroof_met_geweld;
SELECT * FROM slachtofferschap_tasjesroof_zonder_geweld;
SELECT * FROM slachtofferschap_bedreiging_met_geweld;
SELECT * FROM slachtofferschap_mishandeling;

SELECT * FROM tevredenheid_met_het_wonen_in_de_buurt;