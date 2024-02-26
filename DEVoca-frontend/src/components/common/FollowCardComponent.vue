<template>
  <router-link :to="{ name: 'OtherUserProfileView', params: { id: userInfo.userId } }" custom v-slot="{ href, navigate }">
    <div class="card card-side bg-base-100 items-center py-2 px-4">
      <a :href="href" @click="navigate" class="avatar basis-1/6">
        <div class="rounded-full">
          <img :src="userInfo.userImg" />
        </div>
      </a>
      <a :href="href" @click="navigate" class="card-body grow p-2">
        <div class="flex flex-col">
          <h2 class="card-title text-lg font-jalnan">{{ userInfo.userNickName }}</h2>
          <span class="text-sm">{{ userInfo.userIntro }}</span>
        </div>
      </a>
      <div class="flex flex-col basis-1/4 text-right" v-if="!isMyself">
        <button v-if="followingState === 'N'" class="btn bg-devoca text-white"
          @click="followHandler(followingState, userInfo.userId)">팔로우</button>
        <button v-else class="btn border-devoca text-devoca"
          @click="followHandler(followingState, userInfo.userId)">팔로잉</button>
      </div>
    </div>
  </router-link>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { followUser, unfollowUser } from "@/api/mypage";
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
const loginUserId = userStore.kakaoUserInfo.id;
const isMyself = ref(false);

const props = defineProps({
  userInfo: Object,
  option: String
})

onMounted(() => {
  if (props.userInfo.userId == loginUserId) {
    isMyself.value = true;
  }
})

const followingState = ref(props.userInfo.followingYn)

const followHandler = (option, id) => {
  if (option === 'N') {
    followUser(id)
    followingState.value = 'Y'
  } else {
    unfollowUser(id)
    followingState.value = 'N'
  }
}
</script>
