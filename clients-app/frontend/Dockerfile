# Etapa de construcción
FROM node:18-alpine AS builder

# Crear un usuario llamado "app" con UID 10001
RUN addgroup -S appgroup && adduser -D -u 10001 app -G appgroup

WORKDIR /app

# Copiar solo los archivos necesarios para instalar las dependencias
COPY package*.json ./

# Instalar todas las dependencias y construir la aplicación en un solo paso
RUN npm install 

# Copiar el código fuente
COPY . .

RUN npm run build

# Etapa de producción
FROM nginx:alpine

# Crear un usuario llamado "app" con UID 10001
RUN addgroup -S appgroup && adduser -D -u 10001 app -G appgroup

# Crear directorios necesarios para Nginx y darles permisos
RUN mkdir -p /var/run/nginx && \
    mkdir -p /var/cache/nginx/client_temp && \
    mkdir -p /var/log/nginx && \
    mkdir -p /tmp/nginx && \
    chown -R app:appgroup /var/cache/nginx /var/log/nginx /var/run/nginx /tmp/nginx /usr/share/nginx/html /etc/nginx/conf.d /etc/nginx 

# Copiar archivos de configuración de Nginx
COPY nginx.conf /etc/nginx/nginx.conf

# Copiar los archivos construidos a Nginx
COPY --from=builder /app/.next /usr/share/nginx/html

# Cambiar propietario a app:app
RUN chown -R app:appgroup /usr/share/nginx/html

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Cambiar al usuario con UID 10001
USER app

# Configurar HEALTHCHECK
HEALTHCHECK --interval=30s --timeout=5s --retries=3 CMD curl -f http://localhost/ || exit 1

# Iniciar Nginx en primer plano
CMD ["nginx", "-g", "daemon off;"]
