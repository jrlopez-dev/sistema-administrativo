-- Insertar 1000 productos
INSERT INTO producto (nombre, precio, stock)
SELECT
    CONCAT('Producto ', i) AS nombre,
    ROUND(RAND() * 100 + 1, 2) AS precio, -- Precio aleatorio entre 1 y 100
    FLOOR(RAND() * 50 + 1) AS stock       -- Stock aleatorio entre 1 y 50
FROM (
         SELECT @i := @i + 1 AS i FROM (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) a,
             (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) b,
             (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) c,
             (SELECT @i := 0) init
     ) numbers WHERE i <= 1000;

-- Insertar 500 clientes
INSERT INTO cliente (nombre, correo)
SELECT
    CONCAT('Cliente ', i) AS nombre,
    CONCAT('cliente', i, '@example.com') AS correo
FROM (
         SELECT @i := @i + 1 AS i FROM (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) a,
             (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) b,
             (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) c,
             (SELECT @i := 0) init
     ) numbers WHERE i <= 500;

-- Insertar 3000 ventas con detalles
INSERT INTO venta (fecha, cliente_id, total)
SELECT
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY) AS fecha, -- Fecha aleatoria en el último año
    FLOOR(RAND() * 500 + 1) AS cliente_id,                     -- Cliente aleatorio
    ROUND(RAND() * 500 + 50, 2) AS total                       -- Total aleatorio entre 50 y 500
FROM (
         SELECT @i := @i + 1 AS i FROM (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) a,
             (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) b,
             (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) c,
             (SELECT @i := 0) init
     ) numbers WHERE i <= 3000;

-- Insertar detalles de ventas
INSERT INTO detalle_venta (venta_id, producto_id, cantidad, subtotal)
SELECT
    FLOOR(RAND() * 3000 + 1) AS venta_id,                     -- Venta aleatoria
    FLOOR(RAND() * 1000 + 1) AS producto_id,                  -- Producto aleatorio
    FLOOR(RAND() * 5 + 1) AS cantidad,                        -- Cantidad aleatoria entre 1 y 5
    ROUND((FLOOR(RAND() * 5 + 1) * (RAND() * 100 + 1)), 2) AS subtotal -- Subtotal aleatorio
FROM (
         SELECT @i := @i + 1 AS i FROM (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) a,
             (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) b,
             (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) c,
             (SELECT @i := 0) init
     ) numbers WHERE i <= 9000; -- Aproximadamente 3 productos por venta