TRUNCATE TABLE alumn;
CREATE TABLE IF NOT EXISTS alumn
(
    id
    INT
    NOT
    NULL
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    last_name VARCHAR
(
    255
),
    state VARCHAR
(
    10
) NOT NULL,
    age INT NOT NULL,
    created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified timestamp NULL,
    deleted timestamp NULL
    );
--  Insertar Alumnos de Ejemplo
-- INSERT INTO alumn(name, last_name, state, age)
-- VALUES ('Jorge', 'Melendez', 'activo', 28);
-- INSERT INTO alumn(name, last_name, state, age)
-- VALUES ('Luis', 'Torres', 'activo', 29);