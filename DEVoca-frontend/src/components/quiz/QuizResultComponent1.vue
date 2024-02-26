<template>
  <div id="quiz_result_comp_0" class="card bg-white shadow-xl m-2 w-96 h-fit flex flex-col items-center p-12">
    <div class="card-body p-8 w-80 flex flex-col items-center self-center">
      <img src="@/assets/images/quiz/quiz_logo.png" viewBox="0 0 24 24" class="stroke-info shrink-0 w-56 h-20"
        alt="devoca_logo">
      <div class="relative">
        <img src="@/assets/images/quiz/result_mark1.png" alt=""
          class="absolute -top-10 left-2 transform -translate-x-1/2 w-20 h-20">
        <p class="text-3xl font-jalnan mt-2">정답입니다!</p>
      </div>
      <div>
        <p class="text-xl">Q. {{ props.wordSumm }}</p>
        <p class="text-devoca break-words text-2xl font-jalnan mt-4 text-end">A. {{ props.wordNameKr }} / {{ props.wordNameEn }}</p>
      </div>
    </div>
    <div class="mt-2">3 초 후 종료됩니다...</div>
  </div>
</template>

<script setup>
// 정답시 1, 오답시 0
const props = defineProps({
  quizAnswer: {
    type: Object,
    required: true
  },
  wordSumm: {
    type: String,
    required: true
  },
  wordNameKr: {
    type: String,
    required: true
  },
  wordNameEn: {
    type: String,
    required: true
  },
  });

</script>

<style scoped></style>