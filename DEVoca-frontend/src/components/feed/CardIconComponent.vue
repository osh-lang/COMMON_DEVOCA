<template>
  <div class="flex justify-between p-2">
    <div class="flex gap-3">
      <!-- 링크 공유 -->
      <ShareIcon @click="doShare(card.cardId)"/>
      <!-- 단어장 저장하기 -->
      <BookmarkIcon @click="selectModal"/>
      <VocalistSelectModal :class="{'modal-open' : isShowModal}" @close-modal="isShowModal = false" 
        :vocalistInfo="vocalistInfo" @load-vocalist="loadVocalist" v-if="vocalistInfo"/>
    </div>
    <div class="flex gap-4">
      <!-- 리포스트 -->
      <div class="flex gap-2" v-if="card.originCardId === 0 || card.originCardId === null">
        <RepostIcon @click="goRepost()"/>
        <p class="mt-1">{{ card.cardRepostCnt }}</p>
      </div>
      <!-- 좋아요 -->
      <div class="flex gap-2">
        <label class="swap swap-flip">
          <input type="checkbox" @click="likeCards()" v-model="card.cardLikeYN"/>
          <div class="swap-on"><LikeIcon class="stroke-devoca" /></div>
          <div class="swap-off"><LikeIcon /></div>
        </label>
        <p class="mt-1">{{ card.cardLikeCnt }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import ShareIcon from "@/components/icon/ShareIcon.vue"
import BookmarkIcon from "@/components/icon/BookmarkIcon.vue"
import LikeIcon from "@/components/icon/LikeIcon.vue"
import RepostIcon from "@/components/icon/RepostIcon.vue"
import { useRouter } from 'vue-router'
import { likeCard } from '@/api/card'
import { checkVocaList } from '@/api/vocalist'
import VocalistSelectModal from '@/components/vocalist/VocalistSelectModal.vue'

const router = useRouter()
const props = defineProps({
  card: Object
})
const vocalistInfo = ref(null)

const doShare = async function (id) {
  const link = `https://i10d112.p.ssafy.io/card/detail/${id}`
  await navigator.clipboard.writeText(link)
  window.alert('클립보드에 링크가 복사되었습니다.')
}

const isShowModal = ref(false) // 모달 표시 플래그
const selectModal = () => {
  isShowModal.value = true
}

const loadVocalist = () => {
  checkVocaList(props.card.cardId, (res) => {
    vocalistInfo.value = res.data
    vocalistInfo.value.forEach((obj) => {obj.cardId = props.card.cardId})
  }, (err) => {
    console.log(err)
  })
}

const likeCards = () => {
  likeCard(props.card.cardId, {cardLikeYN : props.card.cardLikeYN}, (res) => {
    props.card.cardLikeCnt += props.card.cardLikeYN ? 1 : -1
  }, (err) => {
    console.log(err)
  })
}

const goRepost = () => {
  router.push({name: 'CardRepostView', state: props.card})
}

onMounted(() => {
  loadVocalist()
})
</script>
