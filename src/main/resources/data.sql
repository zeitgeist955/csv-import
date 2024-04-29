DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  mail VARCHAR(250) NOT NULL
);

INSERT INTO employees (first_name, last_name, mail) VALUES
  ('Laurent', 'GINA', 'laurentgina@mail.com'),
  ('Sophie', 'FONCEK', 'sophiefoncek@mail.com'),
  ('Agathe', 'FEELING', 'agathefeeling@mail.com');