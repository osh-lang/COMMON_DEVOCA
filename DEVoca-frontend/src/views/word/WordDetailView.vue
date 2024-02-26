<template>
  <div>
    <div class="bg-neutral-100 pt-2">
      <WordDetailComponent v-if="word" :word="word" />
      <div v-else class="flex justify-center">
        <span class="loading loading-dots loading-lg"></span>
      </div>
    </div>

    <!-- 기사 -->
    <div class="collapse collapse-arrow bg-base-100 shadow-xl w-full mb-4">
      <input type="checkbox" />
      <div class="collapse-title text-xl font-medium font-jalnan">관련 기사</div>
      <div v-if="newsList && newsList.length === 0" class="collapse-content p-0">
        <div class="card card-compact bg-base-100 dark:bg-base-100 shadow-xl mb-4 p-2">
          <div class="card-body">관련 기사가 없습니다!</div>
        </div>
      </div>
      <div v-if="newsList && newsList.length > 0" class="collapse-content p-0">
        <NewsComponent v-for="news in newsList" :key="news.id" :news="news" />
      </div>
    </div>

    <div class="collapse collapse-arrow bg-base-100 shadow-xl w-full">
      <input type="checkbox" />
      <div class="collapse-title text-xl font-medium font-jalnan">관련 카드</div>
      <div v-if="cardList && cardList.length === 0" class="collapse-content p-0">
        <div class="card card-compact bg-base-100 dark:bg-base-100 shadow-xl mb-4 p-2">
          <div class="card-body">관련 카드가 없습니다!</div>
        </div>
      </div>
      <div v-if="cardList && cardList.length > 0" class="collapse-content p-0">
        <CardComponent v-for="card in cardList" :key="card.id" :card="card" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onBeforeMount } from 'vue'
import WordDetailComponent from '@/components/word/WordDetailComponent.vue'
import NewsComponent from '@/components/news/NewsComponent.vue'
import CardComponent from '@/components/feed/CardComponent.vue'
import { getWordDetail, getCardListByWord } from '@/api/word.js'

const props = defineProps({
  id: String
})

const word = ref(null)
const newsList = ref(null)
const cardList = ref(null)
const scrollNo = ref(0)

onBeforeMount(async () => {
  try {
    await getWordDetail(props.id, (res) => {
      word.value = res.data.wordDTO
      newsList.value = res.data.newsList

      getCardListByWord(`${word.value.wordId}/${scrollNo.value}`, (res) => {
        cardList.value = res.data
      }, (err) => {
        console.log(err)
      })
    }, (err) => {
      console.log(err)
    })
  } catch (err) {
    console.log(err)
  }
})

</script>