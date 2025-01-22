# Bievenido/a a Alvolante 😺
Este proyecto fue creado para la asignatura de Fundamentos de Ingeniería de Sowftware el 2-2024. En esta aplicación web podrás simular un arriendo de vehículo, podrás elegir entre los roles de trabajador, cliente y administrador.

---

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
- Nuestro profesor, **Felipe Fuentes**  
- Nuestro ayudante, **Enzo Gulppi**

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
Ve a la carpeta `resources` y luego al archivo `application.properties`.Cambia la contraseña de la base de datos por la tuya y verifica que la propiedad para la creación de tablas esté configurada como create, de la siguiente forma:
```bash
spring.jpa.hibernate.ddl-auto=create
```

Este programa requiere Java 17 (JDK), por lo que es necesario tenerlo instalado previamente.

Para ejecutar el backend:
Dirígete al archivo `BackendApplication` ubicado en la siguiente ruta:
```plaintext
**\src\main\java\com\Alvolante\Backend
```
Presiona el botón play o la combinación de teclas Shift + F10.

Una vez ejecutado el backend por primera vez, regresa al archivo `application.properties` y cambia la propiedad `spring.jpa.hibernate.ddl-auto` de `create` a `update`, para evitar la recreación de las tablas en futuras ejecuciones:
```bash
spring.jpa.hibernate.ddl-auto=update
```

## 5.- Poblar la base de datos
Buscar el archivo `base de datos` y ejecutarlo en la base de datos "alvolante".
## 6.- Abrir con Visual Studio Code 
Se debe de abir en la carpeta `frontendUsuario`.
## 7.- En la terminal del frontend ingresar 
```bash
npm install
```
## 8.- Ejecutar e ingresar a la URL 
Se ejecuta lo siguiente: 
```bash
npm run dev
```
Luego, se presiona `control + clic` en la url.
## 9.- Listo! Ya estas preparado para simular un arriendo o reserva de vehículos 😼.
