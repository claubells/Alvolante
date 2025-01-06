import axios from 'axios';

const API_URL = "http://localhost:8080";

class UserService {
    getCurrentUserId() {
        return localStorage.getItem('userId');
    }

    getAuthToken() {
        return localStorage.getItem('jwtToken');
    }
    // buscar usaurio por email
    async fetchUserByEmail(email) {
        try {
            const token = localStorage.getItem('jwtToken');
            const response = await axios.get(`${API_URL}/api/usuarios/userByEmail`, {
                params: { email },
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            return response.data;
        } catch (error) {
            console.error("Error al obtener usuario por email:", error);
            throw error;
        }
    }
    // obtener usuario por email
    async getUserById(id) {
        try {
            const response = await axios.get(`${API_URL}/api/usuarios/id`, {
                params: { id }, // Pass id directly as parameter
                headers: {
                    'Authorization': `Bearer ${this.getAuthToken()}`
                }
            });
            console.log("Usuario encontrado:", response.data);
            if (!response.data) {
                throw new Error('Usuario no encontrado');
            }
            
            return response.data; // Returns UsuarioEntity object from backend
        } catch (error) {
            console.error("Error al obtener usuario por ID:", error);
            throw error;
        }
    }
    // obtener id usuario por email
    async getIdByEmail(email) {
        try {
            const token = localStorage.getItem('jwtToken');
            const response = await axios.get(`${API_URL}/api/usuarios/idByEmail`, {
                params: { email },
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            return response.data;
        } catch (error) {
            console.error("Error al obtener ID por email:", error);
            throw error;
        }
    }
    // obtener la info de un usuario
    async getCurrentUserData() {
        try {
            const userId = this.getCurrentUserId();
            if (!userId) {
                throw new Error('No hay usuario autenticado');
            }
            return await this.getUserById(userId);
        } catch (error) {
            console.error("Error al obtener datos del usuario actual:", error);
            throw error;
        }
    }
}

export const userService = new UserService();