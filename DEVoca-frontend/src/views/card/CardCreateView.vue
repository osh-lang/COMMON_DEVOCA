<template>
  <div class="p-3">
    <h2 class="font-jalnan text-lg px-2">단어 선택</h2>
    <AutoCompleteSearchComponent @selected-word="(id)=>{inputData.wordId = id}"/>
    <form @submit.prevent="submitCard">
      <textarea class="textarea textarea-bordered w-full h-[30vh] resize-none mb-2" placeholder="나누고 싶은 글을 적어주세요"
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
import AutoCompleteSearchComponent from '@/components/common/AutoCompleteSearchComponent.vue'
import { registCard } from '@/api/card'
import { useUserStore } from '@/stores/user'
import { useRouter } from 'vue-router'

const router = useRouter()
const userStore = useUserStore()

const inputData = ref({
  userId: userStore.kakaoUserInfo['id'],
  wordId: null,
  cardContent: null,
  cardLink: null,
  cardRelatedKeywordList: null
})

const submitCard = function () {
  if (inputData.value) {
    inputData.value.cardContent = inputData.value.cardContent.replaceAll(/(\n|\r\n)/g,'<br>')
  }
  if (inputData.value.cardRelatedKeywordList) {
    inputData.value.cardRelatedKeywordList = inputData.value.cardRelatedKeywordList.
      split(',').map(item => item.trim()).filter(item => item !== '')
  }
  registCard(inputData.value, (res) => {
    router.push({name: 'MainView'})
  }, (err) => {
    console.log(`err : ${err}`)
  })
}

</script>