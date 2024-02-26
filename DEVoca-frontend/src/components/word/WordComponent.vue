<template>
  <div class="card card-compact bg-base-100 dark:bg-base-100 shadow-xl mb-4 p-2">
    <div class="card-body">
      <h2 class="card-title text-base">{{ word.wordNameEn }}</h2>
      <span class="text-gray-400 text-xs">{{ word.wordNameKr }}</span>
      <div v-if="!isOrigin">
        <hr>
        <p class="text-sm" v-html="word.wordSumm"></p>
        <div v-if="word.wordCategory !== undefined" class="flex justify-start gap-1 mt-2 text-xs">
          <span v-for="category in word.wordCategory" :key="category.id">#{{ category }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
const props = defineProps({
  word: Object,
  isOrigin: Boolean
})

onMounted(() => {
  if (props.word.wordDef && props.word.wordDef.length > 20) {
    props.word.wordSumm = props.word.wordDef.slice(0, 50) + '...'
  }
})
</script>
