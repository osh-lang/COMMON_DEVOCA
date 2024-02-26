<template>
  <div class="navbar bg-base-100 fixed top-0 right-0 left-0 z-20 ">
    <div class="navbar-start ml-1">
        <img src="@/assets/images/DEVoca_logo_en_only.png" class="size-1/2 max-h-16" alt="devoca_logo" @click="goMainOrTop">
    </div>

    <!-- 알림페이지 연결 후 swap 기능 구현 예정 -->
    <div class="navbar-end">
      <button @click="goDM(userStore.kakaoUserInfo['id'])" class="btn btn-ghost btn-circle">
        <div class="indicator text-neutral-900">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" transform="rotate(-30)" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" class="w-7s h-7 mb-2"><path stroke-linecap="round" stroke-linejoin="round" d="M6 12 3.269 3.125A59.769 59.769 0 0 1 21.485 12 59.768 59.768 0 0 1 3.27 20.875L5.999 12Zm0 0h7.5" /></svg>
          <span class="relative flex h-3 w-3" v-if="userStore.isDMNotify">
            <span class="animate-ping absolute inline-flex h-full w-full rounded-full bg-red-500 opacity-75"></span>
            <span class="relative inline-flex rounded-full h-3 w-3 bg-red-500"></span>
          </span>
        </div>
      </button>
      <button @click="goNotification" class="btn btn-ghost btn-circle">
        <div class="indicator text-neutral-900">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" /></svg>
          <span class="relative flex h-3 w-3" v-if="userStore.isNotify">
            <span class="animate-ping absolute inline-flex h-full w-full rounded-full bg-red-500 opacity-75"></span>
            <span class="relative inline-flex rounded-full h-3 w-3 bg-red-500"></span>
          </span>
        </div>
      </button>
      </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const route = useRoute()
const router = useRouter()
const userStore = useUserStore()

const goDM = (userId) => {
  router.push({name : 'DMListView', params : {id : userId}});
};

const goNotification = () => {
  router.push({name : 'NotificationPageView'});
};

const goMainOrTop = () => {
  if (route.name != 'FeedListView') {
    router.push({name: 'MainView'})
  } else {
    document.getElementById("appView").scrollTo({top: 0, behavior: 'smooth'})
  }
}
</script>

<style scoped>

</style>