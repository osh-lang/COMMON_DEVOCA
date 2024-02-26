<template>
  <div class="m-2 h-full flex flex-col">
    <div role="tablist" class="tabs tabs-bordered m-6">
      <input class="tab text-lg" :class="{ 'tab-active': boardType === 0 }" @click="changeBoardType(0)" type="radio"
        name="my_tabs_1" role="tab" aria-label="자유" />
      <input class="tab text-lg" :class="{ 'tab-active': boardType === 1 }" @click="changeBoardType(1)" type="radio"
        name="my_tabs_1" role="tab" aria-label="Q&A" />
    </div>

    <div v-for="board in reversedBoards" :key="board.boardId">
      <ArticleComponent :board="board" />
    </div>
  </div>
</template>

<script setup>
import ArticleComponent from '@/components/article/ArticleComponent.vue';
import { ref, onMounted, computed } from 'vue';
import { getBoardList } from '@/api/board';

const boards = ref([]);
const boardType = ref(0);

// 역순으로 정렬된 배열 계산
const reversedBoards = computed(() => {
      return boards.value.slice().reverse(); // 기존 배열을 변경하지 않고 역순으로 정렬된 새 배열 반환
    })

const changeBoardType = (type) => {
  boardType.value = type;
  fetchBoardData();
};

const fetchBoardData = () => {
  getBoardList(boardType.value, (response) => {
    boards.value = response.data;
  }, null);
};

onMounted(() => {
  // 컴포넌트가 마운트될 때 데이터를 가져옵니다.
  getBoardList(boardType.value, (response) => {
    boards.value = response.data;
  }, null);
});
</script>
