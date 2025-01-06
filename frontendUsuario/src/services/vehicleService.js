import axios from "axios"

// URL de la API
const API_URL = "http://localhost:8080/api/vehiculos";

class VehicleServices{
  async createVehiculo(vehiculo) {
    try {
      const token = localStorage.getItem("jwtToken");
      console.log("Token enviado:", token); // Debug para confirmar el token
      console.log("Vehiculo enviado al back:", vehiculo);

      const response = await axios.post(`${API_URL}/crear-vehiculo`, vehiculo, {
        headers: {
          Authorization: `Bearer ${token}`, // Incluye el token con el prefijo Bearer
          "Content-Type": "application/json", // Este encabezado es necesario
        },
      });
      return response.data;
    } catch (error) {
      console.error("Error al agregar vehÃculo:", error.response || error);
      throw error;
    }
  }

}
// Exportar una instancia de la clase correctamente
const vehicleServicesInstance = new VehicleServices();
export default vehicleServicesInstance;