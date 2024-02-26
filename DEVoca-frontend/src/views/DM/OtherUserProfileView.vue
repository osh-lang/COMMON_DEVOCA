<template>
  <ProfIleCardComponents :otherUserId="userId" />
  <div class="flex justify-between mt-3">
    <div>
      <button :key="userInfo.followingYn" v-if="userInfo.followingYn === 'N'"
        class="btn btn-sm w-24 ml-5 bg-devoca text-white"
        @click="followHandler(userInfo.followingYn, userInfo.userId)">팔로우</button>
      <button v-else class="btn btn-sm w-24 ml-5 border-devoca text-devoca"
        @click="followHandler(userInfo.followingYn, userInfo.userId)">팔로잉</button>
    </div>
    <div>
      <button class="btn btn-sm w-24 bg-devoca text-white" @click="dmHandler(userInfo.userId)">DM</button>
    </div>
    <div>
      <button class="btn btn-sm w-24 mr-5 bg-devoca text-white" @click="selectModal()">VS</button>
    </div>
  </div>
  <h2 class="mx-6 mt-8 font-bold text-lg">{{ userInfo.userNickName }}님의 글</h2>
  <div v-if="hasCardResults" class="flex flex-col">
    <CardComponent v-for="(card, index) in cardList" :key="index" :card="card" />
  </div>
  <div v-else>
    <p class="ml-2">작성한 글이 없습니다.</p>
  </div>
  <dialog id="FightPopupModal" class="modal sm:modal-middle" :class="{'modal-open': isShowModal}">
    <FightPopupComponent v-if="isShowModal" @close-modal="isShowModal=false" :oppoUserId="userId"/>
  </dialog>
</template>

<script setup>
import { ref, onBeforeMount } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getOtherUserInfo } from '@/api/user.js'
import { getRoomUuid } from '@/api/dm'
import { followUser, unfollowUser } from "@/api/mypage"
import ProfIleCardComponents from '@/components/mypage/ProfIleCardComponents.vue'
import FightPopupComponent from '@/components/quiz/FightPopupComponent.vue'
import { getCardListByUserId } from "@/api/card"
import CardComponent from "@/components/feed/CardComponent.vue";

const route = useRoute()
const router = useRouter()

const userId = ref(route.params.id)
const userInfo = ref({})

const hasCardResults = ref(false)
const cardList = ref([])

onBeforeMount(() => {
  getOtherUserInfo(userId.value, (res) => {
    userInfo.value = res.data
  }, null)

  getCardListByUserId(`${userId.value}/0`, (res) => {
    hasCardResults.value = res.data.length > 0
    if (!hasCardResults.value) return
    cardList.value = res.data
  }, null)
})

const followHandler = (option, id) => {
  if (option === 'N') {
    followUser(id)
    userInfo.value.followingYn = 'Y'
  } else {
    unfollowUser(id)
    userInfo.value.followingYn = 'N'
  }
}

const dmHandler = (id) => {
  getRoomUuid(id, (res) => {
    router.push({
      name: 'DMMessageView',
      params: { roomUuid: res.data }
    })
  }, null)
}

const isShowModal = ref(false);

const selectModal = () => {
  isShowModal.value = true;
}
</script>