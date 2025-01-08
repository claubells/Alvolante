Manual de uso:
1.- Clonar repositorio (se puede hacer desde la cmd escribiendo "git clone https://github.com/Diegomuu/Alvolante.git")
2.- Abrir pgadmin y crear una base de datos llamada "alvolante" 
3.- Abrir con Intellj la carpeta "Backend"
4.- Ir a la carpeta recursos y luego al archivo "application.properties". Dentro de esta cambiar la contraseña de la base de datos a la suya y verificar que la linea siguiente diga: "spring.jpa.hibernate.ddl-auto=create". Luego de esto ejecutar el backend y cambiar la linea anterior a "update".
5.- Abrir con Visual Studio code la carpeta frontendUsuario
6.- En la terminal del frontend ingresar "npm install"
7.- Luego escribir "npm run dev" e ingresar a la url local que apareció con "control + clic"
