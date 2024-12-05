import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('../view/homeViews.vue'),
            alias: '/inicio',


        },

        {
            path: '/user',
            name: 'user',
            component: () => import('../view/userSolicitation.vue'),
            


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
            


        }





    ]
});

export default router;