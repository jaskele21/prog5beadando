<script setup>
import { useAuthStore } from '@/stores/auth-store.js'
import { ref } from 'vue'
const authStore = useAuthStore()
const password = ref('')
const name = ref('')
const phoneNumber = ref('')
const sign = () => {
  authStore.sign(password.value, phoneNumber.value, name.value)
}
</script>

<template>
  <div class="flex justify-center items-center h-screen w-screen bg-lime-600">
    <div
      class="flex flex-col justify-center items-center rounded-md w-2/6 min-w-96 h-2/6 min-h-80 bg-lime-900"
    >
      <label class="text-white text-lg">Név:</label>
      <input v-model="name" type="text" class="my-4 min-w-44" />
      <label class="text-white text-lg">Telefonszám:</label>
      <input v-model="phoneNumber" type="text" class="my-4 min-w-44" />
      <label class="text-white text-lg">Jelszó:</label>
      <input v-model="password" type="password" class="my-4 min-w-44" />
      <button @click="sign" class="text-black text-lg bg-slate-200 rounded-md w-1/6 min-w-32 py-2">
        Regisztráció
      </button>
    </div>
  </div>
</template>
