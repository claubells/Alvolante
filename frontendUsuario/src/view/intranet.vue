<template>
  <main>
    <div class="general">
      <div class="content">
        <img class="image" src="./media/logoalvolante.png" alt="Logo">
        <div class="header">BIENVENIDO A LA INTRANET</div>
        <div class="headerDescription">Selecciona tu rol para continuar</div>

        <div class="roleButtons">
          <button class="roleButton" @click="chooseRoleC('cliente')">Soy Cliente</button>
          <button class="roleButton" @click="chooseRoleT('trabajador')">Soy Trabajador</button>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  methods: {
    chooseRoleC() {
      this.$router.push('/homeviews'); // Redirecciona a la vista de contacto
    },
    chooseRoleT(){
      this.$router.push('/loginTrabajador');
    }
  },
};
</script>

<style>
.general {
  position: fixed; /* Hace que la barra se quede fija en la parte superior */
  top: 0; /* La coloca exactamente en la parte superior */
  left: 0; /* Alinea al inicio del contenido horizontal */
  width: 100%; /* Se asegura de que ocupe todo el ancho */
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #f8bbd0, #fce4ec);
  font-family: 'Arial', sans-serif;
  margin: 0;
  padding: 0;
}

.content {
  background: #fff3e0;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  max-width: 400px;
  width: 100%;
  margin: 0;
  box-sizing: border-box; /* Asegura que el padding y el border se incluyan en el tamaño total */
}


.image {
  display: block;
  max-width: 100%;
  height: auto;
  margin: 0 auto 1rem;
  width: 150px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.header {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
  color: #ff4081;
}

.headerDescription {
  font-size: 1rem;
  color: #ff80ab;
  margin-bottom: 1.5rem;
}

.roleButtons {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.roleButton {
  background: #ff80ab;
  color: white;
  border: none;
  padding: 0.75rem;
  border-radius: 8px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.roleButton:hover {
  background: #ff4081;
  transform: scale(1.05);
}

.roleButton:active {
  transform: scale(0.98);
}
</style>