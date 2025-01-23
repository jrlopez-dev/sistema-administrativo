# Usa la imagen oficial de PostgreSQL como base
FROM postgres:latest

# Copia el script SQL al directorio /docker-entrypoint-initdb.d/
COPY init.sql /docker-entrypoint-initdb.d/

# Define las variables de entorno para la base de datos, usuario y contraseña
# ENV POSTGRES_DB=admin
# ENV POSTGRES_USER=admin
# ENV POSTGRES_PASSWORD=admin