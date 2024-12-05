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
            component: () => import('../view/contacto.vue'),
            


        }
    ]
});

export default router;