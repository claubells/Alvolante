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
            path: '/Autos',
            name: 'Autos',
            component: () => import('../view/Autos.vue'),
            
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
            path: '/loginTrabajador',
            name: 'loginTrabajador',
            component: () => import('../view/loginTrabajador.vue'),
            
        },

    ]
});

export default router;