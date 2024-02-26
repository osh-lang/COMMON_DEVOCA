<template>
  <div class="h-screen flex items-center">
    <div class="card shrink-0 w-full max-w-sm bg-base-100 mx-auto">
      <div class="flex justify-center">
        <img src="@/assets/images/DEVoca_logo_en_only.png" class="w-3/4">
      </div>
      <form class="card-body">
        <div class="flex flex-col items-center my-10">
          <a :href=kakaoURI>
            <img alt="kakao logo" src="@/assets/images/kakao_login.png" class="logo" />
          </a>
        </div>
      </form>
    </div>
  </div>
</template>
  
<script setup>
import { onMounted } from 'vue'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()
const clientId = 'f9cb962075484b28551d411e7d63c0eb'
const redirectURI = 'https://i10d112.p.ssafy.io/kakao/callback'
const kakaoURI = `https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectURI}&response_type=code`

onMounted(() => {
  localStorage.clear()
  userStore.kakaoUserInfo = {
    id: null,
    token: null,
    userYn: false
  }
})

</script>

<style scoped>
.logo {
  width: 300px;
  height: 50px;
}
</style>