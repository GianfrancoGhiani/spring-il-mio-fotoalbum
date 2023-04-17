import { createRouter, createWebHistory } from "vue-router";

import HomePage from './pages/HomePage.vue';
import PhotoShow from './pages/PhotoShow.vue';


const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomePage
        },
        {
            path: '/photos/:id',
            name: 'show',
            component: PhotoShow
        },

    ]
});

export {router};
