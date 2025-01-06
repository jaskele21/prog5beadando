<script setup>
import { useAuthStore } from '@/stores/auth-store.js'
import { ref } from 'vue'
const authStore = useAuthStore()
const password = ref('')
const phoneNumber = ref('')
const login = () => {
  authStore.login(password.value, phoneNumber.value)
}
</script>

<template>
  <div class="flex justify-center items-center h-screen w-screen bg-slate-600">
    <div
      class="flex flex-col justify-center items-center rounded-md w-2/6 min-w-96 h-2/6 min-h-80 bg-indigo-900"
    >
      <label class="text-white text-lg">Telefonszám:</label>
      <input v-model="phoneNumber" type="text" class="my-4 min-w-44" />
      <label class="text-white text-lg">Jelszó:</label>
      <input v-model="password" type="password" class="my-4 min-w-44" />
      <button @click="login" class="text-black text-lg bg-gray-200 rounded-md w-1/6 min-w-32 py-2">
        Belépés
      </button>
    </div>
  </div>
</template>
