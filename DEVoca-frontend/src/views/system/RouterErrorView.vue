<template>
  <div class="flex flex-col justify-center items-center h-screen text-2xl font-jalnan">
    <p>잘못된 경로입니다</p>
    <p>메인 페이지로 이동합니다</p>
    <span class="loading loading-dots loading-lg"></span>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

onMounted(() => {
  // 1.573초 후에 다른 페이지로 리디렉션
  setTimeout(() => {
    if (userStore.kakaoUserInfo.id) {
      router.push({name : 'MainView'})
    } else {
      router.push({name : 'LoginView'})
    }
    }, 1573)
})
</script>
