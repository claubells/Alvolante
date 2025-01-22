# Bievenido/a a Alvolante 😺
Este proyecto fue creado para la asignatura de Fundamentos de Ingeniería de Sowftware el 2-2024. En esta aplicación web podrás simular un arriendo de vehículo, podrás elegir entre los roles de trabajador, cliente y administrador.

--------------------------------------------------------------------------------------------------------------

## Créditos
Este proyecto fue desarrollado con mucho esfuerzo y dedicación por el siguiente equipo:
- **👨‍💻 [Jordán Arias](https://github.com/Jordanariash)**  
- **👨‍💻 [Thomas Gustafsson](https://github.com/Gunndur)**  
- **👨‍💻 [Diego Hernández](https://github.com/Diegomuu)**  
- **👩‍💻 [Isidora Reveco](https://github.com/IsidoraSRM)**
- **👨‍💻 [Matías Vejar](https://github.com/matiasjava)**
- **👨‍💻 [Álvaro Muñoz](https://github.com/Dante.stormblessed)**
- **👩‍💻 [Claudia Villa](https://github.com/claubells)**  

---

### 📚 Agradecimientos
Un especial agradecimiento a:  
- Nuestro profesor, **[Felipe Fuentes]**  
- Nuestro ayudante, **[Enzo Gulppi]**

---

# Manual de Uso

## 1. Clonar repositorio
Puedes clonar el repositorio desde la terminal (necesita tener instalado el git) ejecutando el siguiente comando:
```bash
git clone https://github.com/Diegomuu/Alvolante.git
```
## 2.- Crear la base de datos
Abrir pgadmin y crear una base de datos llamada "alvolante".
## 3.- Abrir en el Intellj la carpeta "Backend"
## 4.- Cambiar contraseña de la base de datos y ejecutar el backend
Ir a la carpeta recursos y luego al archivo `application.properties`. Dentro de esta cambiar la contraseña de la base de datos a la suya y verificar que las tablas esten en "create", de la siguiente forma: 
```bash
spring.jpa.hibernate.ddl-auto=create
```
Luego de esto ejecutar el backend, para esto hay que ir al archivo `BackendApplication` dentro de la carpeta main. 
```plaintext
**\src\main\java\com\Alvolante\Backend
```

Una vez ejecutado, cambiar la igualdad linea de `application.properties` a "update". 
## 5.- Poblar la base de datos
Buscar el archivo "base de datos" y ejecutarlo en la base de datos "alvolante".
## 6.- Abrir con Visual Studio Code la carpeta "frontendUsuario"
## 7.- En la terminal del frontend ingresar 
```bash
npm install
```
## 8.- Ejecutar lo siguiente e ingresar a la url local que apareció con "control + clic"
```bash
npm run dev
```
## 9.- Listo! Ya estas preparado para simular un arriendo o reserva de vehículos 😼
