<template>
  <div class="card card-side bg-base-100 items-center py-2 px-4" @click="goProfile()">
    <div class="avatar basis-1/6">
      <div class="rounded-full">
        <img :src="userInfo.userImg" />
      </div>
    </div>
    <div class="card-body grow p-2">
      <div class="flex flex-col">
        <h2 class="card-title text-lg font-jalnan">{{ userInfo.userNickName }}</h2>
        <span class="text-sm">{{ userInfo.userIntro }}</span>
      </div>
    </div>
    <div class="flex flex-col basis-1/3 text-xs text-right">
      <span>{{ dateString }}</span>
      <span>{{ timeString }}</span>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()
const props = defineProps({
  userInfo: Object,
  isOrigin: Boolean
})

const datetime = new Date(props.userInfo.cardRegistDate)
const locale = 'ko-KR'
const options = { timeZone: 'Asia/Seoul' }
const dateString = datetime != 'Invalid Date' ? ref(datetime.toLocaleDateString(locale, options)) : ''
const timeString = datetime != 'Invalid Date' ? ref(datetime.toLocaleTimeString(locale, options)) : ''

const goProfile = function () {
  if (props.isOrigin) {
    return
  } else {
    if (props.userInfo.userId === userStore.kakaoUserInfo.id) {
      router.push({name: 'MypageView'})
    } else {
      router.push({name: 'OtherUserProfileView', params: {id: props.userInfo.userId}})
    }
  }
  }
</script>
