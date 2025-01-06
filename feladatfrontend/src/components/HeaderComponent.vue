<script setup>
import { useAuthStore } from '@/stores/auth-store.js'
const { logout } = useAuthStore()
</script>

<template>
  <section>
    <div class="flex w-screen flex-row-reverse bg-red-600 p-2 h-16">
      <button @click="logout">Kijelentkezés</button>
    </div>
  </section>
</template>
