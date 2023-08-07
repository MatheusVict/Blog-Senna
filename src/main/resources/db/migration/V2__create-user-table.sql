CREATE TABLE user_table (
  id VARCHAR(255) NOT NULL,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   role INTEGER NOT NULL,
   CONSTRAINT pk_user_table PRIMARY KEY (id)
);

ALTER TABLE user_table ADD CONSTRAINT uc_user_table_email UNIQUE (email);