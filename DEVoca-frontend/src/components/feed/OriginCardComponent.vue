<template>
  <div class="flex justify-center">
    <div class="card h-fit bg-base-100 shadow-xl w-full">
      <!-- 유저 -->
      <AvartarComponent :userInfo="userInfo" :isOrigin="true"/>
      <!-- 본문 -->
      <div class="card-body p-4">
        <WordComponent :word="word" :isOrigin="true"/>
        <div class="m-2" v-html="card.originCardContent"></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import AvartarComponent from '@/components/common/AvatarComponent.vue'
import WordComponent from "@/components/word/WordComponent.vue"

const props = defineProps({
  card: Object
})
console.log(props.card)

const userInfo = ref({
  userImg: props.card.originUserImg,
  userIntro: '',
  userNickName: props.card.originUserNickName,
})

const word = ref({
  wordId: props.card.wordId,
  wordNameEn: props.card.wordNameEn,
  wordNameKr: props.card.wordNameKr,
  wordSumm: props.card.wordSumm,
})
</script>
