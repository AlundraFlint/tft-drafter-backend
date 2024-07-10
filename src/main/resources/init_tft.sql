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
    TIER        integer,
    TRAIT_ID_1  integer,
    TRAIT_ID_2  integer,
    TRAIT_ID_3  integer,
    URL_IMAGE   varchar(255),
    URL_ICON    varchar(255),
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
    CONSTRAINT  traitBonus_fk FOREIGN KEY (TRAIT_BONUS) REFERENCES TRAITS(ID)
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
    FIRSTNAME   varchar(80),
    LASTNAME    varchar(80),
    NICKNAME    varchar(80),
    PASSWORD    varchar(255),
    ADMIN       integer not null default 0
);

CREATE TABLE TFT.BOARD(
    ID          integer primary key not null AUTO_INCREMENT,
    USER_ID     integer,
    NAME        varchar(80),
    CONSTRAINT  boardUserId_fk FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE TFT.BOARD_SLOT(
    ID          integer primary key not null AUTO_INCREMENT,
    BOARD_ID    integer not null,
    POSITION    integer,
    CHAMPION_ID integer,
    ITEM1_ID    integer,
    ITEM2_ID    integer,
    ITEM3_ID    integer,
    IS_MAX      integer,
    CONSTRAINT  boardId_fk FOREIGN KEY (BOARD_ID) REFERENCES BOARD(ID),
    CONSTRAINT  boardChampionId_fk FOREIGN KEY (CHAMPION_ID) REFERENCES CHAMPIONS(ID),
    CONSTRAINT  boardItem1Id_fk FOREIGN KEY (ITEM1_ID) REFERENCES ITEMS(ID),
    CONSTRAINT  boardItem2Id_fk FOREIGN KEY (ITEM2_ID) REFERENCES ITEMS(ID),
    CONSTRAINT  boardItem3Id_fk FOREIGN KEY (ITEM3_ID) REFERENCES ITEMS(ID)
);