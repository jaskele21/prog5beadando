import { ref } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'
import { authMeApi, loginApi, logoutApi, signInApi } from '@/services/authorization-api.js'

export const useAuthStore = defineStore('auth', () => {
  const router = useRouter()

  const isLoggedIn = ref(false)
  const user = ref(null)

  const sign = async (password, phoneNumber, name) => {
    try {
      const { key } = await signInApi(password, phoneNumber, name)
      setKey(key)
      await authMe()
      router.push('/appointment')
    } catch (error) {
      console.error(error)
    }
  }
  const login = async (password, phoneNumber) => {
    try {
      const { key } = await loginApi(password, phoneNumber)
      setKey(key)
      await authMe()
      router.push('/appointment')
    } catch (error) {
      console.error(error)
    }
  }

  const logout = async () => {
    await logoutApi(getKey())
    removeKey()
    isLoggedIn.value = false
    user.value = null
    router.push({ name: 'Login' })
  }

  const authMe = async () => {
    user.value = await authMeApi(getKey())
    isLoggedIn.value = true
  }
  const getKey = () => {
    return localStorage.getItem('key')
  }

  const setKey = (key) => {
    localStorage.setItem('key', key)
  }

  const removeKey = () => {
    return localStorage.removeItem('key')
  }

  return {
    isLoggedIn,
    user,
    sign,
    login,
    logout,
    authMe,
    getKey,
    removeKey,
  }
})
