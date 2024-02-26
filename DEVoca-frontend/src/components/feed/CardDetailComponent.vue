<template>
  <div class="flex justify-center m-2 relative">
    <button class="btn btn-ghost absolute -top-2 -right-2 z-10" @click="deleteCards(card.cardId)" v-if="userStore.kakaoUserInfo.id === card.userId">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" /></svg>
    </button>
    <div class="card bg-base-100 shadow-xl w-full mt-2 pt-4">
      <!-- 유저 -->
      <AvartarComponent :userInfo="userInfo" />
      <!-- 본문 -->
      <div class="card-body p-4">
        <WordComponent :word="word" v-if="card.originCardId === 0" @click="goWordDetail(word.wordId)"/>
        <OriginCardComponent :card="originCard" v-else @click="goOriginCard"/>
          
        <div class="m-2" v-html="card.cardContent"></div>
        
        <div v-if="card.cardRelatedKeywordList" class="flex justify-start gap-1 mt-2 text-xs">
          <span v-for="keyword in card.cardRelatedKeywordList" :key="keyword.id">#{{ keyword }}</span>
        </div>
        <hr>
        <div class="mt-2">
          <p>참고 링크</p>
          <a :href="card.cardLink" class="text-xs text-wrap break-words text-blue-600">{{ card.cardLink }} <LinkIcon class="inline"/></a>
        </div>

        <CardIconComponent :card="card"/>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import AvartarComponent from '@/components/common/AvatarComponent.vue'
import WordComponent from "@/components/word/WordComponent.vue"
import CardIconComponent from "@/components/feed/CardIconComponent.vue"
import { useUserStore } from '@/stores/user'
import { useRouter } from 'vue-router'
import LinkIcon from "@/components/icon/LinkIcon.vue"
import { deleteCard } from '@/api/card'
import OriginCardComponent from '@/components/feed/OriginCardComponent.vue'

const router = useRouter()
const userStore = useUserStore()
const props = defineProps({
  card: Object
})

const userInfo = computed(() => {
  return {
    userId: props.card.userId,
    userImg: props.card.userImg,
    userIntro: props.card.userIntro,
    userNickName: props.card.userNickName,
    cardRegistDate: props.card.cardRegistDate
  }
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
  wordSumm: props.card.wordSumm
})

const goWordDetail = function (wordId) {
  router.push({name: 'WordDetailView', params: {id: wordId}})
}

const goOriginCard = () => {
  router.push({name: 'CardDetailView', params: {id: props.card.originCardId}})
}

const deleteCards = (cardId) => {
  const confirmResult = confirm("글을 삭제하시겠어요?")
  if (confirmResult) {
    deleteCard(cardId, (res) => {
      console.log(res.data)
      router.push({name: 'MainView'})
    }, (err) => {
      console.log(err)
    })
  } else {
    return
  }
}
</script>