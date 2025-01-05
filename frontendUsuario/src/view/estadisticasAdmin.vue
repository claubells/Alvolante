<template>
  <main class="main-container">

    <!-- Barra lateral -->
    <div class="sidebar">
      <ul>
        <img class="image" src="./media/logoalvolante.png">
        <li><a href="#" @click="toInicio"><i class="bi bi-house-door nav-icon"></i> Inicio</a></li>
        <li><a href="#" @click="cierreSesion"><i class="bi bi-box-arrow-right nav-icon"></i> Cerrar sesión</a></li>
      </ul>
    </div>


  <div>
    <!-- Sección de gráficos -->
    <section class="charts-section">
      <div class="chart-container">
        <h2>Ventas Anuales</h2>
        <Bar :data="chartData1" :options="chartOptions" />
        <div class="chart-controls">
          <button class="chart-button">Expandir</button>
          <div class="controls-right">
            <select class="control-select">
              <option value="">Ordenar</option>
              <option value="">Marca</option>
              <option value="">Precio</option>
              <option value="">Año del vehiculo</option>
              <option value="">Estado</option>
            </select>
            <select class="control-select">
              <option value="">Filtrar</option>
              <option value="">Marca</option>
              <option value="">Precio</option>
              <option value="">Año del vehiculo</option>
              <option value="">Estado</option>
            </select>
          </div>
        </div>
        
      </div>
      <!-- Gráfico de ventas mensuales -->
      <div class="chart-container">
        <h2>Ventas Mensuales</h2>
        <Bar :data="chartData2" :options="chartOptions" />
        <div class="chart-controls">
          <button class="chart-button">Expandir</button>
          <div class="controls-right">
            <select class="control-select">
              <option value="">Ordenar</option>
              <option value="">Marca</option>
              <option value="">Precio</option>
              <option value="">Año del vehiculo</option>
              <option value="">Estado</option>
            </select>
            <select class="control-select">
              <option value="">Filtrar</option>
              <option value="">Marca</option>
              <option value="">Precio</option>
              <option value="">Año del vehiculo</option>
              <option value="">Estado</option>
            </select>
          </div>
        </div>
      </div>

      <!-- Gráfico de ventas semanales -->
      <div class="chart-container">
        <h2>Ventas Semanales</h2>
        <Bar :data="chartData3" :options="chartOptions" />
        <div class="chart-controls">
          <button class="chart-button">Expandir</button>
          <div class="controls-right">
            <select class="control-select">
              <option value="">Ordenar</option>
              <option value="">Marca</option>
              <option value="">Precio</option>
              <option value="">Año del vehiculo</option>
              <option value="">Estado</option>
            </select>
            <select class="control-select">
              <option value="">Filtrar</option>
              <option value="">Marca</option>
              <option value="">Precio</option>
              <option value="">Año del vehiculo</option>
              <option value="">Estado</option>
            </select>
          </div>
        </div>
      </div>
    </section>

</div>

<section class="text-box-section">
  <div class="text-box-container">
    <img src="./media/usuarios_nuevos.png" alt="Imagen 1" class="text-box-image" />
    <p class="text-box-text">+500 Nuevos registros el último mes.</p>
  </div>
  <div class="text-box-container">
    <img src="./media/vistas_a_la_pagina.png" alt="Imagen 2" class="text-box-image" />
    <p class="text-box-text">+9.0000 Visitas a la pagina el último mes.</p>
  </div>
  <div class="text-box-container">
    <img src="./media/ventas.png" alt="Imagen 3" class="text-box-image" />
    <p class="text-box-text">+100 Vehiculos arrendados el último mes .</p>
  </div>
</section>

  </main>
</template>


<script setup>
// Imports
import { useRouter } from 'vue-router'; // Importar el router
import { Bar } from 'vue-chartjs';
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from 'chart.js';

// Variables reactivas
const router = useRouter(); // Usar el router

// Métodos
const logout = () => {
  localStorage.removeItem("login"); // Limpia el almacenamiento local
  window.location.href = "/"; // Redirige al login
};

const toInicio = () => {
  window.location.href = "/admin";
};

// Registrar componentes globales de Chart.js
ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

// Datos y opciones de ejemplo para los gráficos
const chartData1 = {
  labels: ['Enero', 'Febrero', 'Marzo', 'Abril','Mayo', 'Junio', 'Julio','Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
  datasets: [
    {
      label: 'Arriendos anuales',
      data: [50, 100, 150, 200,50, 100, 150, 200,50, 100, 150, 200],
      backgroundColor: 'rgba(255, 99, 132, 0.5)',
    },
  ],
};

const chartData2 = {
  labels: ['1', '2', '3', '4','5', '6', '7', '8','9', '10', '11', '12','13', '14', '15', '16','17', '18', '19', '20','21', '22', '23', '24','25', '26', '27', '28','29', '30', '31'],
  datasets: [
    {
      label: 'Arriendos mensuales',
      data: [300, 400, 200, 500,300, 400, 200, 500,300, 400, 200, 500,300, 400, 200, 500, 300, 400, 200, 500,300, 400, 200, 500,300, 400, 200, 500,300, 400, 200],
      backgroundColor: 'rgba(54, 162, 235, 0.5)',
    },
  ],
};

const chartData3 = {
  labels: ['Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo'],
  datasets: [
    {
      label: 'Arriendos semanales',
      data: [120, 200, 170, 90, 120, 200, 170],
      backgroundColor: 'rgba(75, 192, 192, 0.5)',
    },
  ],
};

const chartOptions = {
  responsive: true,
  plugins: {
    legend: { display: false },
    title: { display: false },
  },
};
</script>


<style>
/* Estilos generales */
body {
  background: linear-gradient(135deg, #ffe6cc, #ffd1dc);
  color: #000;
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

.container {
    display: flex;
    min-height: 100vh;
  }

/* Barra lateral */
.sidebar {
  width: 250px;
  background-color: #ff80ab;
  padding: 15px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  position: fixed;
  height: 100%;
  margin-left: -15.4%;
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar ul li {
  margin-bottom: 12px;
}

.sidebar ul li a {
  text-decoration: none;
  color: white;
  font-size: 15px;
  display: block;
  padding: 7px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.sidebar ul li a:hover {
  background-color: #ff4081;
}

.image {
  max-width: 100%;
  margin-bottom: 10px; /* Menos margen debajo de la imagen */
  border-radius: 8px;
}


/* Contenedor principal */
.main-container {
  margin-left: 250px; /* Compensa el ancho del sidebar */
  padding: 1rem; /* Asegura un espacio interno */
  margin-top: -16px;
}


.charts-section {
  display: flex;
  justify-content: space-evenly; /* Espacio entre gráficos */
  flex-wrap: wrap; /* Permitir que los gráficos se ajusten si el espacio es pequeño */
  margin-top: 2rem;
  gap: 1rem; /* Espaciado entre los gráficos */
  
}

.chart-container {
  background: #fff;
  padding: 1rem;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 30%; /* Ajustar el ancho del contenedor */
}

.chart-container canvas {
  max-height: 200px; /* Limitar la altura del gráfico */
  max-width: 100%; /* Asegurar que no se desborde */
}

.chart-container h2 {
  margin-bottom: 1rem;
  font-size: 1.2rem; /* Reducir tamaño del título */
  color: #333;
}

/* Ajustes responsivos para pantallas pequeñas */
@media (max-width: 768px) {
  .charts-section {
    flex-direction: column; /* Apilar gráficos en pantallas pequeñas */
    align-items: center;
  }

  .chart-container {
    width: 90%; /* Tomar más espacio en pantallas pequeñas */
  }
}


/* Sección de cajas de texto */
.text-box-section {
  display: flex;
  justify-content: space-evenly; /* Espacio entre las cajas */
  flex-wrap: wrap; /* Permite que las cajas se ajusten en pantallas más pequeñas */
  margin-top: 2rem;
  gap: 1rem; /* Espaciado entre las cajas */
}

.text-box-container {
  background: #fff;
  padding: 1rem;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: left; /* Alineación del texto a la izquierda */
  width: 30%; /* Ajustar el ancho del contenedor */
  display: flex; /* Usamos flexbox para alinear la imagen y el texto */
  align-items: center; /* Centra verticalmente el contenido */
}

.text-box-container img {
  width: 50%; /* La imagen ocupará el 50% del ancho del contenedor */
  height: auto;
  margin-right: 1rem; /* Espacio entre la imagen y el texto */
}

.text-box-text {
  flex: 1; /* El texto ocupará el 50% restante del contenedor */
  font-size: 1rem;
  color: #333;
  margin-top: 0;
  font-weight: normal;
}

/* Ajustes responsivos */
@media (max-width: 768px) {
  .text-box-section {
    flex-direction: column; /* Apilar cajas en pantallas pequeñas */
    align-items: center;
  }

  .text-box-container {
    width: 90%; /* Tomar más espacio en pantallas pequeñas */
  }

  .text-box-container img {
    width: 100%; /* La imagen ocupará el 100% en pantallas pequeñas */
    margin-right: 0; /* Eliminar margen a la derecha */
  }

  .text-box-text {
  flex: 1; /* El texto ocupará el 50% restante del contenedor */
  font-size: 3rem; /* Aumentar el tamaño de la fuente */
  font-weight: bold; /* Hacer el texto más fuerte */
  color: #333;
  margin-top: 0;
  line-height: 1.4; /* Mejorar el espaciado entre líneas si el texto es largo */
}
}
/* Iconos */
.nav-icon {
  margin-right: 5px;
  font-size: 1.1rem;
  vertical-align: middle;
  }
  .nav-icon.large {
    font-size: 1.3rem;
  }

</style>