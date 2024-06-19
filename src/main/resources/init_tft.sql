CREATE DATABASE TFT;

CREATE TABLE TRAITS(
    ID          integer primary key not null AUTO_INCREMENT,
    NOM         varchar(80),
    NOM_ANGLAIS varchar(80)
);

CREATE TABLE CHAMPIONS(
    ID          integer primary key not null AUTO_INCREMENT,
    NOM         varchar(80),
    COUT        integer,
    TRAIT_ID_1  integer,
    TRAIT_ID_2  integer,
    TRAIT_ID_3  integer,
    CONSTRAINT  trait1_fk FOREIGN KEY (TRAIT_ID_1) REFERENCES TRAITS(ID),
    CONSTRAINT  trait2_fk FOREIGN KEY (TRAIT_ID_2) REFERENCES TRAITS(ID),
    CONSTRAINT  trait3_fk FOREIGN KEY (TRAIT_ID_3) REFERENCES TRAITS(ID)
);

CREATE TABLE ITEMS(
    ID          integer primary key not null AUTO_INCREMENT,
    NOM         varchar(80),
    NOM_ANGLAIS varchar(80),
    TRAIT_BONUS integer,
    CONSTRAINT  traitbonus_fk FOREIGN KEY (TRAIT_BONUS) REFERENCES TRAITS(ID)
);

CREATE TABLE TRAITS_LEVEL(
    ID          integer primary key not null AUTO_INCREMENT,
    TRAIT_ID    integer,
    VALEUR      integer,
    COLOR       varchar(80),
    CONSTRAINT  trait_fk FOREIGN KEY (TRAIT_ID) REFERENCES TRAITS(ID)
);