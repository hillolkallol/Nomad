CREATE TABLE IF NOT EXISTS User(
user_id INT NOT NULL AUTO_INCREMENT, 
email_id VARCHAR(20) NOT NULL,
password VARCHAR(50) NOT NULL,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
address VARCHAR(20) NOT NULL,
gender VARCHAR(10) NOT NULL,
UNIQUE (email_id),
PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS Driver(
user_id INT NOT NULL, 
license_no INT NOT NULL,
insurance_no INT NOT NULL,
insurance_com VARCHAR(20) NOT NULL,
PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS Rider(
user_id INT NOT NULL, 
PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS Schedule(
schedule_id INT NOT NULL AUTO_INCREMENT, 
time DATETIME,
from_location VARCHAR(20) NOT NULL,
to_destination VARCHAR(20) NOT NULL,
seats_left INT NOT NULL,
seats_total INT NOT NULL,
PRIMARY KEY (schedule_id)
);

CREATE TABLE IF NOT EXISTS Trip(
trip_id INT NOT NULL AUTO_INCREMENT, 
is_confirmed BOOLEAN,
is_finished BOOLEAN,
schedule_id INT NOT NULL,
rider_id INT NOT NULL,
driver_id INT NOT NULL,
PRIMARY KEY (trip_id)
);

CREATE TABLE IF NOT EXISTS Payment(
trip_id INT NOT NULL, 
rider_id INT NOT NULL,
driver_id INT NOT NULL,
payment_date DATETIME,
amount INT NOT NULL,
PRIMARY KEY (trip_id, rider_id, driver_id)
);

CREATE TABLE IF NOT EXISTS RateRider( 
rider_id INT NOT NULL,
driver_id INT NOT NULL,
rating INT NOT NULL,
PRIMARY KEY (rider_id, driver_id)
);

CREATE TABLE IF NOT EXISTS Request( 
rider_id INT NOT NULL,
trip_id INT NOT NULL,
schedule_id INT NOT NULL,
PRIMARY KEY (rider_id, trip_id, schedule_id)
);

CREATE TABLE IF NOT EXISTS Temp_Registration( 
temp_reg_id INT NOT NULL AUTO_INCREMENT,
email_id INT NOT NULL,
temp_hash INT NOT NULL,
user_id INT NOT NULL,
PRIMARY KEY (temp_reg_id)
);




