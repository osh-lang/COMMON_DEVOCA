<template>
  <div role="tablist" class="tabs tabs-bordered m-6">
    <router-link :to="{ name: 'FollowView', params: { option: 'following' } }" role="tab" class="tab text-lg"
      :class="{ 'tab-active': option === 'following' }">
      팔로잉 {{ followingList.length }}명
    </router-link>
    <router-link :to="{ name: 'FollowView', params: { option: 'follower' } }" role="tab" class="tab text-lg"
      :class="{ 'tab-active': option === 'follower' }">
      팔로워 {{ followerList.length }}명
    </router-link>
  </div>

  <div v-show="option === 'following'" class="mx-2">
    <FollowCardComponent v-for="(item, index) in followingList" :key="index" :userInfo="item" />
  </div>
  <div v-show="option === 'follower'" class="mx-2">
    <FollowCardComponent v-for="(item, index) in followerList" :key="index" :userInfo="item" />
  </div>
</template>

<script setup>
import FollowCardComponent from '@/components/common/FollowCardComponent.vue'

import { onBeforeRouteUpdate, useRoute } from "vue-router";
import { ref, onMounted } from "vue";
import { getFollower, getFollowing } from "@/api/mypage";
import { getOtherFollowList, getOtherFollowingList } from "@/api/user";
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
const loginUserId = userStore.kakaoUserInfo.id;
const route = useRoute()
const targetUserId = history.state.targetUserId;

const option = ref()

const followerList = ref([])
const followingList = ref([])

onBeforeRouteUpdate((to,) => {
  option.value = to.params.option
  updateList()
})

onMounted(() => {
  option.value = route.params.option
  updateList()
})

const updateList = () => {
  if (targetUserId == loginUserId) {
    getFollower((res) => {
      followerList.value = res.data
    }, null)
    getFollowing((res) => {
      followingList.value = res.data
    }, null)
  } else {
    getOtherFollowList(targetUserId, (res) => {
      followerList.value = res.data
    }, null)
    getOtherFollowingList(targetUserId, (res) => {
      followingList.value = res.data
    }, null)
  }
}

</script>
