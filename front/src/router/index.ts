import { createRouter, createWebHistory } from 'vue-router'
// @ts-ignore
import HelloWorld from "@/components/HelloWorld.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HelloWorld
    }
  ]
})

export default router
