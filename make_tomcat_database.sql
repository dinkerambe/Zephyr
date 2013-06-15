DROP DATABASE IF EXISTS tomcat_realm;
CREATE DATABASE tomcat_realm;
USE tomcat_realm;
/*CREATE TABLE tomcat_users (
	user_id int(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	email varchar(255) NOT NULL UNIQUE,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	password varchar(255) NOT NULL
		);

CREATE TABLE tomcat_roles (
	role_name varchar(20) NOT NULL UNIQUE 
	);
CREATE TABLE tomcat_users_roles (
	email varchar(255) NOT NULL,
		role_name varchar(20) NOT NULL,
			UNIQUE(email, role_name),
				CONSTRAINT tomcat_users_roles_foreign_key_1 FOREIGN KEY (email) REFERENCES tomcat_users (email),
					CONSTRAINT tomcat_users_roles_foreign_key_2 FOREIGN KEY (role_name) REFERENCES tomcat_roles (role_name)
					);
INSERT INTO tomcat_users (first_name, last_name, email, password) VALUES ('tomcat', 'tomcat', 'tomcat@localhost.com', 'tomcat');
INSERT INTO tomcat_users (first_name, last_name, email, password) VALUES ('user','user', 'user@gmail.com', '123');
INSERT INTO tomcat_roles (role_name) VALUES ('admin');
INSERT INTO tomcat_roles (role_name) VALUES ('user');
INSERT INTO tomcat_users_roles (email, role_name) VALUES ('tomcat@localhost.com', 'user');
INSERT INTO tomcat_users_roles (email, role_name) VALUES ('tomcat@localhost.com', 'admin');
INSERT INTO tomcat_users_roles (email, role_name) VALUES ('user@gmail.com', 'user');
DROP TABLE IF EXISTS tomcat_users;
*/
COMMIT;

