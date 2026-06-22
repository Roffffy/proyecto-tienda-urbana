CREATE DATABASE IF NOT EXISTS db_usuarios_tienda_urbana
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS db_catalogo_tienda_urbana
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS db_carrito_tienda_urbana
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS db_ordenes_tienda_urbana
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_devolucion
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_envio
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_notificaciones
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_pagos
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS bd_resena
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;

GRANT ALL PRIVILEGES ON db_usuarios_tienda_urbana.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON db_catalogo_tienda_urbana.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON db_carrito_tienda_urbana.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON db_ordenes_tienda_urbana.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON bd_devolucion.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON bd_envio.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON bd_notificaciones.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON bd_pagos.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON bd_resena.* TO 'root'@'%';

FLUSH PRIVILEGES;