<template>
  <div class="card card-side bg-devoca_skyblue bg-opacity-60">
    <div class="avatar">
      <div class="w-24 h-24 rounded-full my-auto ml-5">
        <img :src="userInfo.userImg" />
      </div>
    </div>
    <div class="card-body">
      <div class="flex flex-row justify-between">
        <h2 class="card-title mt-6">{{ userInfo.userNickName }}</h2>
        <template v-if="props.otherUserId === undefined && userInfo.userId">
          <router-link :to="{ name: 'ProfileChangeView', params: { id: userInfo.userId } }">
            <PenIcon />
          </router-link>
        </template>
      </div>
      <p class="text-sm">{{ userInfo.userIntro }}</p>
      <div class="flex-row mt-2">
        <div class="mr-5 text-sm inline" @click="goFolloing">
          팔로잉 {{ userInfo.userFollowingCnt }}
        </div>
        <div class="text-sm inline" @click="goFollower">
          팔로워 {{ userInfo.userFollowerCnt }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onBeforeMount } from 'vue';
import { useRouter } from "vue-router";
import { getUserInfo, getOtherUserInfo } from '@/api/user.js'
import PenIcon from '@/components/icon/PenIcon.vue'

const router = useRouter();
const userInfo = ref({})

const props = defineProps({
  otherUserId: String
})

onBeforeMount(() => {
  if (props.otherUserId === undefined) {
    // 내 프로필 조회인 경우
    getUserInfo((res) => {
      userInfo.value = res.data
    }, null)
  } else {
    // 다른 사람 프로필 조회인 경우
    getOtherUserInfo(props.otherUserId, (res) => {
      userInfo.value = res.data
    }, null)
  }
})

const goFolloing = () => {
  router.push({
    name: 'FollowView',
    params: { option: 'following' },
    state: { targetUserId: userInfo.value.userId }
  });
}

const goFollower = () => {
  router.push({
    name: 'FollowView',
    params: { option: 'follower' },
    state: { targetUserId: userInfo.value.userId }
  });
}

</script>
