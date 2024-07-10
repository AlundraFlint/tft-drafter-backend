CREATE DATABASE TFT;

CREATE TABLE TFT.TRAITS(
    ID          integer primary key not null AUTO_INCREMENT,
    RIOT_ID     varchar(80),
    NOM         varchar(80),
    NOM_ANGLAIS varchar(80),
    URL_IMAGE   varchar(255)
);

CREATE TABLE TFT.CHAMPIONS(
    ID          integer primary key not null AUTO_INCREMENT,
    RIOT_ID     varchar(80),
    NOM         varchar(80),
    NOM_ANGLAIS varchar(80),
    COUT        integer,
    TRAIT_ID_1  integer,
    TRAIT_ID_2  integer,
    TRAIT_ID_3  integer,
    URL_IMAGE   varchar(255),
    URL_ICONE   varchar(255),
    CONSTRAINT  trait1_fk FOREIGN KEY (TRAIT_ID_1) REFERENCES TRAITS(ID),
    CONSTRAINT  trait2_fk FOREIGN KEY (TRAIT_ID_2) REFERENCES TRAITS(ID),
    CONSTRAINT  trait3_fk FOREIGN KEY (TRAIT_ID_3) REFERENCES TRAITS(ID)
);

CREATE TABLE TFT.ITEMS(
    ID          integer primary key not null AUTO_INCREMENT,
    RIOT_ID     varchar(80),
    NOM         varchar(80),
    NOM_ANGLAIS varchar(80),
    TRAIT_BONUS integer,
    URL_IMAGE   varchar(255),
    CONSTRAINT  traitbonus_fk FOREIGN KEY (TRAIT_BONUS) REFERENCES TRAITS(ID)
);

CREATE TABLE TFT.TRAITS_LEVEL(
    ID          integer primary key not null AUTO_INCREMENT,
    TRAIT_ID    integer,
    VALEUR      integer,
    COLOR       varchar(80),
    CONSTRAINT  trait_fk FOREIGN KEY (TRAIT_ID) REFERENCES TRAITS(ID)
);

CREATE TABLE TFT.USERS(
    ID          integer primary key not null AUTO_INCREMENT,
    EMAIL       varchar(255),
    NOM         varchar(80),
    PRENOM      varchar(80),
    PSEUDO      varchar(80),
    ADMIN       integer not null default 0
);