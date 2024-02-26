<template>
  <div class="flex flex-col items-center">
    <div v-if="hasCardResults" >
      <CardComponent v-for="(card, index) in cardList" :key="index" :card="card" />
    </div >
    <div v-else>
      <p class="text-xl m-12">작성한 글이 없습니다</p>
    </div>
  </div>
</template>

<script setup>
import { getMyCard } from '@/api/mypage'
import { ref, onMounted } from 'vue'
import CardComponent from "@/components/feed/CardComponent.vue";

const hasCardResults = ref(false)
const cardList = ref([])

onMounted(() => {
  // TODO: 무한 스크롤 적용 필요
  getMyCard(0, (res) => {
    hasCardResults.value = res.data.length > 0
    if (!hasCardResults.value) return
    cardList.value = res.data
  }, (err) => {
    console.log(err)
  })
})
</script>
