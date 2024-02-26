<template>
  <div class="flex flex-col justify-center items-center h-dvh">
    <h1 class="font-jalnan text-3xl inline-block">로그인 중입니다</h1>
    <h1 class="font-jalnan text-3xl inline-block">잠시만 기다려주세요</h1>
    <span class="loading loading-dots loading-lg"></span>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getKaKaoToken } from '@/api/user'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

onMounted (() => {
  const queryString = window.location.search
  const urlParams = new URLSearchParams(queryString)
  const codeParam = urlParams.get('code')

  getKaKaoToken(codeParam, (res) => {
    userStore.kakaoUserInfo = res.data
    userStore.kakaoUserInfo.id = userStore.kakaoUserInfo.id.toString()

    if (res.data.userYn === true) {
      router.push({name : 'MainView'})
    } else if (res.data.userYn === false) {
      router.push({name : 'SignupView'})
    }
  }, (err) => {
    console.log(err)
  })
})

</script>