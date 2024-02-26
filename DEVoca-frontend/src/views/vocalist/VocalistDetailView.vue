<template>
  <p class="mt-2 mr-3 ml-5 text-xl font-jalnan">{{ vocalistName }}</p>
  <div class="flex flex-col items-center" v-if="cardList.length > 0">
    <CardComponent v-for="(card, index) in cardList" :key="index" :card="card" />
    <p v-if="cardList.length == 0" class="text-xl m-12">저장한 단어가 없습니다</p>
  </div>
</template>

<script setup>
import { getVocaListDetail } from '@/api/vocalist'
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import CardComponent from "@/components/feed/CardComponent.vue"

const route = useRoute()
const cardList = ref([])
const vocalistName = history.state.vocalistName

onMounted(() => {
  console.log("라우트 파람아이디!!", route.params.id);
  getVocaListDetail(route.params.id, (res) => {
    let set = new Set()
    cardList.value = res.data.filter((card) => {
      if (!set.has(card.cardId)) {
        set.add(card.cardId)
        return card
      }
    })
    set = null
  }, null)
})
</script>