<template>
  <div class="m-2 h-full flex flex-col">
    <SearchBarComponent />

    <div role="tablist" class="tabs tabs-bordered m-6">
      <div role="tab" class="tab text-lg" :class="{ 'tab-active': option === 'word' }" @click="option='word'">단어</div>
      <div role="tab" class="tab text-lg" :class="{ 'tab-active': option === 'card' }" @click="option='card'">카드</div>
    </div>

    <div v-show="option === 'word'" class="mx-2">
      <div v-show="hasWordResults" class="flex flex-col">
        <WordComponent v-for="word of searchResultWords" :key="word.id" :word="word" @click="onWordClick(word.wordId)" />
      </div>
      <div v-show="!hasWordResults" class="flex flex-col items-center justify-center mt-12">
        <p>아직 등록되지 않은 단어입니다.</p>
        <button class="btn bg-devoca text-white text-lg m-2" @click="goWordRequset">단어 등록 요청하러 가기</button>
      </div>
    </div>
    <div v-show="option === 'card'" class="mx-2">
      <div v-show="hasCardResults" class="flex flex-col">
        <CardComponent v-for="card of searchResultCards" :key="card.id" :card="card" @click="onCardClick(card.cardId)" />
      </div>
      <div v-show="!hasCardResults" class="flex flex-col items-center justify-center mt-12">
        <p>아직 등록되지 않은 단어입니다.</p>
        <button class="btn bg-devoca text-white text-lg m-2" @click="goWordRequset">단어 등록 요청하러 가기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { onBeforeRouteUpdate, useRoute, useRouter } from 'vue-router'
import { getSearchResultWords, getSearchResultCards } from '@/api/search'
import { useUserStore } from "@/stores/user"
import SearchBarComponent from '@/components/common/SearchBarComponent.vue'
import WordComponent from "@/components/word/WordComponent.vue";
import CardComponent from "@/components/feed/CardComponent.vue";


const hasWordResults = ref(false)
const searchResultWords = ref([])
const hasCardResults = ref(false)
const searchResultCards = ref([])

const userStore = useUserStore()
const router = useRouter()
const route = useRoute()
const param = ref(route.query.q)
const option = ref('')

const getSearchResults = () => {
  if (param.value !== undefined) {
    getSearchResultWords(param.value,
      (res) => {
        hasWordResults.value = res.data.length > 0

        // 검색 결과 없는 경우
        if (hasWordResults.value === false) return

        searchResultWords.value = res.data
      }, (error) => {
        console.log(error)
      }
    )

    getSearchResultCards(`${param.value}/0`,
      (res) => {
        hasCardResults.value = res.data.length > 0

        // 검색 결과 없는 경우
        if (hasCardResults.value === false) return

        searchResultCards.value = res.data
      }, (error) => {
        console.log(error)
      }
    )
  }
}

getSearchResults()

onBeforeRouteUpdate((to,) => {
  param.value = to.query.q
  getSearchResults()
})

onMounted(() => {
  option.value = 'word'
  getSearchResults()
})

const onWordClick = (id) => {
  router.push({ name: 'WordDetailView', params: { id } })
}

const onCardClick = (id) => {
  router.push({ name: 'CardDetailView', params: { id } })
}

const goWordRequset = () => {
  router.push({name : 'ArticleCreateView', query: {q: 2}})
}

</script>
