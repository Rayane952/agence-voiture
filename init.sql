-- Création des tables
CREATE TABLE categorie (
                           id_categorie BIGSERIAL PRIMARY KEY,
                           nom VARCHAR(50) NOT NULL,
                           description TEXT
);

CREATE TABLE vehicule (
                          id_vehicule BIGSERIAL PRIMARY KEY,
                          marque VARCHAR(50) NOT NULL,
                          modele VARCHAR(50) NOT NULL,
                          annee INT NOT NULL,
                          prix DECIMAL(10,2) NOT NULL,
                          kilometrage INT,
                          couleur VARCHAR(30),
                          carburant VARCHAR(20),
                          transmission VARCHAR(20),
                          puissance INT,
                          nombre_places INT,
                          date_arrivee DATE NOT NULL,
                          disponibilite BOOLEAN DEFAULT TRUE,
                          id_categorie BIGINT REFERENCES categorie(id_categorie),
                          image_path VARCHAR(255)
);

CREATE TABLE image_vehicule (
                                id_image BIGSERIAL PRIMARY KEY,
                                id_vehicule BIGINT REFERENCES vehicule(id_vehicule),
                                url_image VARCHAR(255) NOT NULL,
                                est_principale BOOLEAN DEFAULT FALSE
);

CREATE TABLE option_vehicule (
                                 id_option BIGSERIAL PRIMARY KEY,
                                 nom VARCHAR(100) NOT NULL,
                                 description TEXT
);

CREATE TABLE vehicule_option (
                                 id_vehicule BIGINT REFERENCES vehicule(id_vehicule),
                                 id_option BIGINT REFERENCES option_vehicule(id_option),
                                 PRIMARY KEY (id_vehicule, id_option)
);

-- Insertion des catégories
INSERT INTO categorie (nom, description) VALUES
                                             ('Berline', 'Voiture à coffre séparé de l''habitacle'),
                                             ('SUV', 'Sport Utility Vehicle, véhicule utilitaire sport'),
                                             ('Coupé', 'Voiture à deux portes et au toit bas'),
                                             ('Cabriolet', 'Voiture décapotable'),
                                             ('Citadine', 'Petite voiture pour usage urbain');

-- Insertion des véhicules
INSERT INTO vehicule (marque, modele, annee, prix, kilometrage, couleur, carburant, transmission, puissance, nombre_places, date_arrivee, id_categorie, image_path) VALUES
                                                                                                                                                                        ('BMW', 'Série 3', 2021, 42000.00, 15000, 'Noir', 'Diesel', 'Automatique', 190, 5, '2023-01-15', 1, '/img/bmw.jpg'),
                                                                                                                                                                        ('Renault', 'Clio', 2020, 15000.00, 25000, 'Rouge', 'Essence', 'Manuelle', 90, 5, '2023-02-10', 5, '/img/default-car.jpg'),
                                                                                                                                                                        ('Peugeot', '3008', 2022, 32000.00, 8000, 'Gris', 'Diesel', 'Automatique', 130, 5, '2023-03-05', 2, '/img/default-car.jpg'),
                                                                                                                                                                        ('Bugatti', 'Chiron', 2021, 2500000.00, 1200, 'Bleu', 'Essence', 'Automatique', 1500, 2, '2023-01-20', 3, '/img/bugatti.jpg'),
                                                                                                                                                                        ('Audi', 'A4', 2019, 28000.00, 45000, 'Blanc', 'Diesel', 'Automatique', 150, 5, '2023-02-25', 1, '/img/default-car.jpg');

-- Insertion des options
INSERT INTO option_vehicule (nom, description) VALUES
                                                   ('GPS', 'Système de navigation par satellite'),
                                                   ('Toit ouvrant', 'Toit panoramique ouvrant électriquement'),
                                                   ('Sièges chauffants', 'Sièges avant chauffants'),
                                                   ('Caméra de recul', 'Caméra d''aide au stationnement'),
                                                   ('Jantes alliage', 'Jantes en alliage léger');

-- Association des options aux véhicules
INSERT INTO vehicule_option (id_vehicule, id_option) VALUES
                                                         (1, 1), (1, 3), (1, 4),
                                                         (2, 5),
                                                         (3, 1), (3, 4), (3, 5),
                                                         (4, 1), (4, 2), (4, 3), (4, 4), (4, 5),
                                                         (5, 1), (5, 3), (5, 4);

-- Insertion des images
INSERT INTO image_vehicule (id_vehicule, url_image, est_principale) VALUES
                                                                        (1, '/img/bmw.jpg', true),
                                                                        (4, '/img/bugatti.jpg', true);
