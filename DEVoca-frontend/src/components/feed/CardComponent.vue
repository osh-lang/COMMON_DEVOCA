<template>
  <div class="flex justify-center m-2">
    <div class="card bg-base-100 shadow-xl w-full">
      <!-- 유저 -->
      <AvartarComponent :userInfo="userInfo" />
      <!-- 본문 -->
      <div class="card-body p-4">
        <div @click="goCardDetail(card.cardId)">
          <WordComponent :word="word" v-if="card.originCardId === null || card.originCardId === 0" />
          <OriginCardComponent :card="originCard" v-else />

          <div class="m-2" v-html="card.cardContent"></div>
        </div>

        <CardIconComponent :card="card" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import AvartarComponent from '@/components/common/AvatarComponent.vue'
import WordComponent from "@/components/word/WordComponent.vue"
import CardIconComponent from "@/components/feed/CardIconComponent.vue"
import { useRouter } from 'vue-router'
import OriginCardComponent from '@/components/feed/OriginCardComponent.vue'

const router = useRouter()
const props = defineProps({
  card: Object
})

const userInfo = ref({
  userId: props.card.userId,
  userImg: props.card.userImg,
  userIntro: props.card.userIntro,
  userNickName: props.card.userNickName,
  cardRegistDate: props.card.cardRegistDate
})

const word = ref({
  wordId: props.card.wordId,
  wordNameEn: props.card.wordNameEn,
  wordNameKr: props.card.wordNameKr,
  wordSumm: props.card.wordSumm,
})

const originCard = ref({
  originUserImg: props.card.originUserImg,
  originUserNickName: props.card.originUserNickName,
  originCardId: props.card.originCardId,
  originCardContent: props.card.originCardContent,
  wordId: props.card.wordId,
  wordNameEn: props.card.wordNameEn,
  wordNameKr: props.card.wordNameKr,
})

const goCardDetail = function (cardId) {
  router.push({ name: 'CardDetailView', params: { id: cardId } })
}

</script>
