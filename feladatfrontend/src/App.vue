<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HeaderComponent from '@/components/HeaderComponent.vue'
import { useAuthStore } from '@/stores/auth-store.js'
import { storeToRefs } from 'pinia'
const { isLoggedIn } = storeToRefs(useAuthStore())
console.log(isLoggedIn)
</script>

<template>
  <HeaderComponent v-if="isLoggedIn" />
  <RouterView />
</template>
