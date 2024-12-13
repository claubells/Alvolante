import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'intranet',
            component: () => import('../view/intranet.vue'),
            alias: '/inicio',


        },

        {
            path: '/homeviews',
            name: 'homeviews',
            component: () => import('../view/homeViews.vue'),
            


        },
        {
            path: '/user',
            name: 'user',
            component: () => import('../view/userSolicitation.vue'),
            alias: '/inicio',


        },

        {
            path: '/agregarVehiculo',
            name: 'agregarVehiculo',
            component: () => import('../view/agregarVehiculoAdmin.vue')
        },


        {
            path: '/contacto',
            name: 'contacto',
            component: () => import('../view/contacto.vue')
        },


        {
            path: '/anonimo',
            name: 'anonimo',
            component: () => import('../view/anonimo.vue'),
            


        },

        {
            path: '/vehiculosAnonimo',
            name: 'vehiculosAnonimo',
            component: () => import('../view/vehiculosAnonimo.vue'),
            
        },
        {
            path: '/vistaAutos',
            name: 'vistaAutos',
            component: () => import('../view/autosClienteVista.vue'),
            
        },
        {
            path: '/perfilCliente',
            name: 'perfilCliente',
            component: () => import('../view/perfilCliente.vue'),
            
        },
        {
            path: '/verVehiculos',
            name: 'verVehiculos',
            component: () => import('../view/VerVehiculos.vue'),
        },
        {
            path: '/loginTrabajador',
            name: 'loginTrabajador',
            component: () => import('../view/loginTrabajador.vue'),
            
        },
        {
            path: '/contactoAnonimo',
            name: 'contactoAnonimo',
            component: () => import('../view/contactoAnonimo.vue'),
            
        },
        {
            path: '/seleccionVehiculo/:idVehiculo',
            name: 'seleccionVehiculo',
            component: () => import('../view/seleccionVehiculo.vue'),
            props: true, // Pasar el parámetro como prop al componente
        },
        {
            path: '/pago',
            name: 'pago',
            component: () => import('../view/pago.vue'),
            
        },   

        {
            path: '/calendarioCliente',
            name: 'calendarioCliente',
            component: () => import('../view/calendarioCliente.vue'),
            
        },
        {
            path: '/verAutosSegunCalendario',
            name: 'verAutosSegunCalendario',
            component: () => import('../view/verAutosSegunCalendario.vue'),
            
        },
             
        {
            path: '/pruebas',
            name: 'pruebas',
            component: () => import('../view/pruebas.vue'),
            
        },

        
        

    ]
});

export default router;