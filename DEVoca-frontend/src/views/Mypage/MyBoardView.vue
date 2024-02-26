<template>
  <div v-show="hasBoardResults" class="flex flex-col">
    <ArticleComponent v-for="(board, index) in boardList" :key="index" :board="board" />
  </div>
</template>
  
<script setup>
import { getMyBoard } from '@/api/mypage'
import { ref, onMounted } from 'vue'
import ArticleComponent from "@/components/article/ArticleComponent.vue";

const hasBoardResults = ref(false)
const boardList = ref([])

onMounted(() => {
  getMyBoard((res) => {
    hasBoardResults.value = res.data.length > 0
    if (!hasBoardResults.value) return
    boardList.value = res.data
  }, null)
})
</script>
