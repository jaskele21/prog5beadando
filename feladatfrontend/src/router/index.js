import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/LoginView.vue'
import SignView from '@/views/SignView.vue'
import AppointmentView from '@/views/AppointmentView.vue'
import { useAuthStore } from '@/stores/auth-store.js'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: LoginView,
      meta: { requiresAuth: false },
    },
    {
      path: '/sign',
      name: 'Sign',
      component: SignView,
      meta: { requiresAuth: false },
    },
    {
      path: '/appointment',
      name: 'Appointment',
      component: AppointmentView,
      meta: { requiresAuth: true },
    },
  ],
})
router.beforeEach(async (to) => {
  const { isLoggedIn, getKey, authMe, removeKey } = useAuthStore()
  const key = getKey()
  if (to.meta.requiresAuth && !isLoggedIn) {
    if (key) {
      try {
        await authMe()
      } catch (e) {
        console.error(e)
        removeKey()
        return { name: 'Login' }
      }
    } else {
      return { name: 'Login' }
    }
  }
})
export default router
