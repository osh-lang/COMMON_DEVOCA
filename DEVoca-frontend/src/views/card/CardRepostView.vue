<template>
  <div class="p-3">
    <OriginCardComponent :card="originCard" />

    <form @submit.prevent="submitRepost">
      <textarea class="textarea textarea-bordered w-full h-[25vh] resize-none my-3" placeholder="나누고 싶은 글을 적어주세요"
      v-model="inputData.cardContent"></textarea>
      
      <label class="form-control w-full mb-4">
        <div class="label">
          <span class="label-text font-jalnan text-lg px-2">참고 링크</span>
        </div>
        <input type="text" placeholder="www.example.com" class="input input-bordered w-full" 
        v-model="inputData.cardLink"/>
      </label>

      <label class="form-control w-full mb-4">
        <div class="label">
          <span class="label-text font-jalnan text-lg px-2">연관 개념</span>
        </div>
        <input type="text" placeholder="연관 개념을 , (쉼표) 로 구분해 입력해주세요" class="input input-bordered w-full" 
        v-model="inputData.cardRelatedKeywordList"/>
      </label>
      
      <div class="flex justify-end">
        <button class="btn btn-md bg-devoca text-white text-lg">게시</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { repostCard } from '@/api/card'
import OriginCardComponent from '@/components/feed/OriginCardComponent.vue'

const router = useRouter()
const userStore = useUserStore()
const repostInfo = ref(history.state)
console.log(repostInfo.value)

const originCard = ref({
  originUserImg: repostInfo.value.userImg,
  originUserNickName: repostInfo.value.userNickName,
  originCardId: repostInfo.value.cardId,
  originCardContent: repostInfo.value.cardContent,
  wordId: repostInfo.value.wordId,
  wordNameEn: repostInfo.value.wordNameEn,
  wordNameKr: repostInfo.value.wordNameKr,
  wordSumm: repostInfo.value.wordSumm,
})

const inputData = ref({
  userId: userStore.kakaoUserInfo.id, // 현재 로그인 유저
  cardId: null, // BE에서 처리
  cardContent: null, // 새로 추가하는 내용
  cardLink: null, // 새로 작성하는 참고링크
  cardRelatedKeywordList: null, // 새로 작성하는 연관개념
  originCardId: repostInfo.value.cardId,
  wordId: repostInfo.value.wordId // 조상 단어 id
})

const submitRepost = function () {
  if (inputData.value) {
    inputData.value.cardContent = inputData.value.cardContent.replaceAll(/(\n|\r\n)/g,'<br>')
  }
  if (inputData.value.cardRelatedKeywordList) {
    inputData.value.cardRelatedKeywordList = inputData.value.cardRelatedKeywordList.
      split(',').map(item => item.trim()).filter(item => item !== '')
  }

  repostCard(inputData.value, (res) => {
    console.log(res)
    router.push({name: 'MainView'})
  }, (err) => {
    console.log(err)
  })
}

</script>