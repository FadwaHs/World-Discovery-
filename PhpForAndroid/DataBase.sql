create database DiscoverWorld ;
use DiscoverWorld;

CREATE TABLE IF NOT EXISTS Utilisateur (
   Email_User       varchar(50) not null,
   Name_User      varchar(25),
   Location_User       varchar(50),
   Photo_Profil     Blob,
   Password_User  varchar(25),
   Date_Creation   date,
   Vkeys           varchar(45) not null,
   primary key (Email_User)
);

CREATE TABLE IF NOT EXISTS ForgetPass (
   id               int not null  AUTO_INCREMENT,
   Email_User       varchar(50) ,
   code           varchar(45),
   primary key (id)
);


CREATE TABLE IF NOT EXISTS Post(
   id_Post       int not null AUTO_INCREMENT,
   Email_User       varchar(50) not null,
   Nom_Categorie varchar(50) not null,
   Titre_Post      varchar(50),
   Description_Post         varchar(10000),
   Location_User       varchar(50),
   Photo_Post     varchar(50),
   City_Post   varchar(50),
   primary key (id_Post)
);



CREATE TABLE IF NOT EXISTS Categorie (
  Nom_Categorie  varchar(50) not null,

   PRIMARY KEY(Nom_Categorie)
);

CREATE TABLE IF NOT EXISTS Save(
   
    id_Post       int not null,
    Email_User    varchar(50) not null,
    
  
   
   primary key (Email_User,id_Post)
);

CREATE TABLE IF NOT EXISTS LikePost(
   
    id_Post       int not null,
    Email_User    varchar(50) not null,
    
    Nbr_Like  int ,
   
   primary key (Email_User,id_Post)
);



CREATE TABLE IF NOT EXISTS Recherche(
   
    id_recherche     int not null AUTO_INCREMENT,
    
   primary key (id_recherche )
);


CREATE TABLE IF NOT EXISTS LignRecherche(
    
      id_recherche      int not null ,
      Email_User       varchar(50) not null,
      Mots     varchar(50) ,
    
    
   primary key (id_recherche,Email_User )
);
  
ALTER TABLE LignRecherche
    ADD CONSTRAINT fk_Email_User_Recherche
    FOREIGN KEY (Email_User)
    REFERENCES Utilisateur(Email_User);


ALTER TABLE LignRecherche
    ADD CONSTRAINT fk_Id_recherche_Recherche
    FOREIGN KEY (id_recherche)
    REFERENCES Recherche(id_recherche);
    
    ALTER TABLE Save
    ADD CONSTRAINT fk_id_Post_Save
    FOREIGN KEY (id_Post)
    REFERENCES Post(id_Post);
    
    ALTER TABLE Save
    ADD CONSTRAINT fk_Email_User_Save
    FOREIGN KEY ( Email_User)
    REFERENCES Utilisateur( Email_User);


      ALTER TABLE LikePost
    ADD CONSTRAINT fk_id_Post_LikePost
    FOREIGN KEY (id_Post)
    REFERENCES Post(id_Post);
    
    ALTER TABLE LikePost
    ADD CONSTRAINT fk_Email_User_LikePost
    FOREIGN KEY ( Email_User)
    REFERENCES Utilisateur( Email_User);
   
  
ALTER TABLE Post
    ADD CONSTRAINT fk_Email_User_Post
    FOREIGN KEY (Email_User)
    REFERENCES  Utilisateur(Email_User);

ALTER TABLE Post
    ADD CONSTRAINT fk_Nom_Categorie_Post
    FOREIGN KEY (Nom_Categorie)
    REFERENCES Categorie(Nom_Categorie);

ALTER TABLE ForgetPass
    ADD CONSTRAINT fk_Email_User_forgetpass
    FOREIGN KEY (Email_User)
    REFERENCES Utilisateur(Email_User);



ALTER TABLE Post ADD Column date_ varchar(50);