SET MODE MYSQL;

DROP TABLE IF EXISTS payment_methods;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS withdrawals;

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE payment_methods (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT NOT NULL,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE withdrawals (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT NOT NULL,
  payment_method_id INT NOT NULL,
  status VARCHAR(50) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  executed_at TIMESTAMP,
  scheduled_at DATE,
  amount DOUBLE NOT NULL
);

INSERT INTO users (name) VALUES
	('Herschel'),
  	('Hipolit'),
  	('Bob'),
  	('Trigger');
  	
INSERT INTO payment_methods (user_id, name) VALUES
	(1, 'My bank account'),
	(1, 'My mom account'),
	(2, 'Work account'),
	(3, 'My bank account'),
	(3, 'Secret account');