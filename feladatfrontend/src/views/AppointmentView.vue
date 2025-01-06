<script setup>
import { ref } from 'vue'
import VueDatePicker from '@vuepic/vue-datepicker'
import { appointmentPost } from '@/services/appointment-api.js'
import { useAuthStore } from '@/stores/auth-store.js'
const authStore = useAuthStore()
const date = ref()
const startTime = ref({ hours: 8, minutes: 0 })
const newTire = ref({})
const service = ref('')
const submit = async () => {
  await appointmentPost(
    {
      tyre: newTire.value,
      appointment: date.value,
      serviceId: service.value,
    },
    authStore.getKey(),
  )
}
</script>

<template>
  <div class="flex flex-col justify-start items-center h-screen w-screen bg-slate-600">
    <div class="flex flex-row items-center justify-start w-full">
      <label class="text-white text-lg">Szolgáltatás:</label>
      <select v-model="service">
        <option v-for="i in 5" :value="i">{{ i }}</option>
      </select>
    </div>
    <div class="flex flex-row items-center justify-start w-full">
      <label class="text-white text-lg">Dátum:</label>
      <VueDatePicker
        locale="hu"
        cancelText="Törlés"
        selectText="Kiválasztás"
        v-model="date"
        text-input
        :disabled-week-days="[0]"
        minutes-increment="30"
        :min-time="{ hours: 8, minutes: 0 }"
        :max-time="{ hours: 16, minutes: 30 }"
        :start-time="startTime"
      />
    </div>
    <div class="flex justify-start items-center w-full">
      <input type="text" placeholder="Méret" v-model="newTire['war']" />
    </div>
    <div class="flex justify-start items-center w-full">
      <input type="text" placeholder="Márka" v-model="newTire['brand']" />
    </div>
    <div class="flex justify-start items-center w-full">
      <input type="text" placeholder="Mennyiség" v-model="newTire['quantity']" />
    </div>
    <div class="flex justify-start items-center w-full">
      <button @click="submit" class="text-black text-lg bg-gray-200 rounded-md w-1/6 min-w-32 py-2">
        Foglalás
      </button>
    </div>
  </div>
</template>
