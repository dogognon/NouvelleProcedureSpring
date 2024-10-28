INSERT INTO ROLES (id, role_name) VALUES (1, 'Utilisateur');
INSERT INTO ROLES (id, role_name) VALUES (2, 'Administrateur');
INSERT INTO DEPARTEMENT (id, libelle) VALUES ('8', 'Achat'), ('9', 'Ressource Humaines'), ('10', 'Informatique'),('11', 'Controle de gestion');
INSERT INTO SERVICES (id, libelle, departement) VALUES ('8', 'Marché','8'),('13', 'Emploi et retraite','9'), ('9', 'Etude et systeme','10');
INSERT INTO POSTE (id, libelle, services) VALUES ('8', 'Responsable des ressource humanies','13'), ('9', 'Responsable reseau système','9');
INSERT INTO USERS (ID, EMAIL, NOMPRENOMS, PASSWORDS, PHONE,POSTES) VALUES ('84c9c9b2-9d0b-4c0b-9e0b-9c0b9c0b9c0b', 'email@example.com', 'John Doe', '$2y$10$WL9w1AuZtEawN.mG.yX.jeTWAY.8ZCBoHsJOhN9bjqPNhN0FF6oDq', '1234567890','8');
INSERT INTO USER_ROLES (USER_ID, ROLE_ID) VALUES ('84c9c9b2-9d0b-4c0b-9e0b-9c0b9c0b9c0b',2);