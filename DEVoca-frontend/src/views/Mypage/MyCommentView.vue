<template>
  <div v-show="hasCommentResults" class="flex flex-col">
    <CommentComponent v-for="(comment, index) in commentList" :key="index" :comment="comment" />
  </div>
</template>
  
<script setup>
import { getMyComment } from '@/api/mypage'
import { ref, onMounted } from 'vue'
import CommentComponent from "@/components/article/CommentComponent.vue";

const hasCommentResults = ref(false)
const commentList = ref([])

onMounted(() => {
  getMyComment((res) => {
    hasCommentResults.value = res.data.length > 0
    if (!hasCommentResults.value) return
    commentList.value = res.data
  }, null)
})
</script>
