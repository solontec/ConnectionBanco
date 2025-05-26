USE login_db;

DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios(
	
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR (16) NOT NULL UNIQUE


);

INSERT INTO usuarios (username, password) VALUES ('Gui', '1234');
INSERT INTO usuarios (username, password) VALUES ('Usuario', 'guizin2');
INSERT INTO usuarios (username, password) VALUES ('dudu', '2');
INSERT INTO usuarios (username, password) VALUES ('japa', 'vitoria');
INSERT INTO usuarios (username, password) VALUES ('admin', '123');

