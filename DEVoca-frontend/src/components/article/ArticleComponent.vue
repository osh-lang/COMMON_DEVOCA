<template>
  <router-link :to="{ name: 'ArticleDetailView', params: { boardId: board.boardId } }">
    <ArticleDetailComponent :board="board" />
  </router-link>
</template>

<script setup>
import ArticleDetailComponent from "./ArticleDetailComponent.vue";

defineProps({
  board: Object
});

</script>
