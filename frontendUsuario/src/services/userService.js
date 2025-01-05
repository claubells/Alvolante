import axios from 'axios';

const API_URL = "http://localhost:8080";

class UserService {
    /* async fetchIdByEmail(email) {
        try {
            const token = localStorage.getItem('token');
            const response = await axios.get(`${API_URL}/api/usuarios/idByEmail`, {
                params: { email },
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            console.error("Error al cargar el id del usuario:", error);
            throw error;
        }
    } */
        async fetchUserByEmail(email) {
            try {
                const token = localStorage.getItem('token'); // Now matches authService
                console.log('Token in service:', token);
        
                if (!token) {
                    throw new Error('Token not found - please login again');
                }
        
                const cleanEmail = email.replace(/"/g, '');
                console.log('Making request with email:', cleanEmail); // Debug email
        
                const response = await axios.get(`${API_URL}/api/usuarios/userByEmail`, {
                    params: { email: cleanEmail },
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
                
                console.log('Response:', response.data); // Debug response
                return response.data;
            } catch (error) {
                console.error("Error fetching user:", error);
                throw error;
            }
        }
}

export const userService = new UserService();