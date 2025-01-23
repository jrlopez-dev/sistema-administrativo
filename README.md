# Configuración de Docker Compose para la Aplicación de Sistema Administrativo

Este documento proporciona una visión general de la configuración de `docker-compose.yml` para la aplicación de prueba tecnica, que incluye un backend, frontend, base de datos PostgreSQL y pgAdmin.
El Backend esta en Spring-Boot y el FrontEnd esta en React. Al final encontraran el comando para ejecutar la solucion
## Servicios

### 1. **client-db** (Base de Datos PostgreSQL)
- **Nombre del contenedor**: `${POSTGRES_HOST}`
- **Contexto de construcción**: `./clients-db/docker`
- **Dockerfile**: `Dockerfile`
- **Variables de entorno**:
  - `POSTGRES_DB=${POSTGRES_DB}`
  - `POSTGRES_USER=${POSTGRES_USER}`
  - `POSTGRES_PASSWORD=${POSTGRES_PASSWORD}`
- **Redes**: `clients-net`

Este servicio configura una base de datos PostgreSQL para almacenar los datos de los clientes.

### 2. **client-backend** (API del Backend)
- **Nombre del contenedor**: `${API_HOST}`
- **Contexto de construcción**: `./clients-app/backend`
- **Dockerfile**: `Dockerfile`
- **Variables de entorno**:
  - `POSTGRES_HOST=${POSTGRES_HOST}`
  - `POSTGRES_DB=${POSTGRES_DB}`
  - `POSTGRES_USER=${POSTGRES_USER}`
  - `POSTGRES_PASSWORD=${POSTGRES_PASSWORD}`
- **Puertos**:
  - `"8086:8080"` (Expone el puerto 8080 del contenedor como 8086 en el host)
- **Dependencias**:
  - `client-db`
- **Redes**: `clients-net`
Este servicio construye y ejecuta la API backend de la aplicación de clientes.

### 3. **client-frontend** (Aplicación Frontend)
- **Nombre del contenedor**: `${FRONTEND_HOST}`
- **Contexto de construcción**: `./clients-app/frontend`
- **Dockerfile**: `Dockerfile2`
- **Puertos**:
  - `"80:80"` (Expone el puerto 80 del contenedor como 80 en el host)
- **Dependencias**:
  - `client-backend`
- **Redes**: `clients-net`
Este servicio construye y ejecuta la aplicación frontend del sistema de clientes.

### 4. **pgadmin** (pgAdmin 4)
- **Nombre del contenedor**: `pgadmin`
- **Imagen**: `dpage/pgadmin4`
- **Variables de entorno**:
  - `PGADMIN_DEFAULT_EMAIL=${PGADMIN_DEFAULT_EMAIL}`
  - `PGADMIN_DEFAULT_PASSWORD=${PGADMIN_DEFAULT_PASSWORD}`
- **Puertos**:
  - `"8080:80"` (Expone el puerto 80 del contenedor como 8080 en el host)
- **Redes**: `clients-net`
Este servicio ejecuta pgAdmin para gestionar la base de datos PostgreSQL a través de una interfaz web.
## Redes
- **clients-net**: Esta es una red personalizada de tipo puente que permite que todos los servicios se comuniquen entre sí.

## Cómo Ejecutar la Aplicación

1. **Configurar las Variables de Entorno**:
   Antes de ejecutar el `docker-compose`, asegúrate de que las siguientes variables de entorno estén configuradas en tu archivo `.env`:
   - `POSTGRES_HOST`
   - `POSTGRES_DB`
   - `POSTGRES_USER`
   - `POSTGRES_PASSWORD`
   - `API_HOST`
   - `FRONTEND_HOST`
   - `PGADMIN_DEFAULT_EMAIL`
   - `PGADMIN_DEFAULT_PASSWORD`

2. **Construir y Ejecutar los Contenedores**:
   Ejecuta el siguiente comando desde el directorio donde se encuentra el archivo `docker-compose.yml`:
   docker-compose up --build
