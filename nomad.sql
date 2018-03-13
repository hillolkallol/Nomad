CREATE TABLE User(
user_id INT NOT NULL AUTO_INCREMENT, 
email_id VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
address VARCHAR(20) NOT NULL,
gender VARCHAR(10) NOT NULL,
schedule_id INT,
email VARCHAR(20) NOT NULL,
UNIQUE (email_id),
PRIMARY KEY (user_id)
);

