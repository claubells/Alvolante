# Manual de Uso

## 1. Clonar repositorio
Puedes clonar el repositorio desde la terminal ejecutando el siguiente comando:
```bash
git clone https://github.com/Diegomuu/Alvolante.git
```
## 2.- Crear la base de datos
Abrir pgadmin y crear una base de datos llamada "alvolante" 
## 3.- Abrir en el Intellj la carpeta "Backend"
## 4.- Cambiar contraseña de la base de datos
Ir a la carpeta recursos y luego al archivo "application.properties". Dentro de esta cambiar la contraseña de la base de datos a la suya y verificar que las tablas esten en "create", de la siguiente forma: 
```bash
spring.jpa.hibernate.ddl-auto=create
```
Luego de esto ejecutar el backend y cambiar la linea anterior a "update".
## 5.- Abrir con Visual Studio Code la carpeta "frontendUsuario"
## 6.- En la terminal del frontend ingresar 
```bash
npm install
```
## 7.- Ejecutar lo siguiente e ingresar a la url local que apareció con "control + clic"
```bash
npm run dev
```
## 8.- Listo! Ya estas preparado para simular un arriendo o reserva de vehículos 😼
