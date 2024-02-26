<template>
  <div class="flex text-center">
    <div>
      <p class="mt-5 mr-3 ml-5 text-xl font-jalnan">참여한</p>
    </div>
    <div>
      <p class="mt-5 mr-3 text-xl font-jalnan text-devoca">퀴즈</p>
    </div>
    <div>
      <p class="mt-5 text-xl font-jalnan">목록</p>
    </div>
  </div>
  <div class="m-12 flex flex-col">
    <div class="w-full h-20 rounded-lg p-4 bg-[#e5e5e5]">
      <p class="text-base text-center">누적 퀴즈 참여</p>
      <p class="text-xl text-center">{{ quizCnt }}개</p>
    </div>
    <div>
      <QuizListComponent v-for="(quiz, index) in quizList" :key="index" :quiz="quiz"/>
    </div>
  </div>
</template>



<script setup>
import QuizListComponent from "@/components/quiz/QuizListComponent.vue";
import { onMounted, ref } from "vue";
import { getQuizCnt, getQuizResultList } from '@/api/quiz';

const quizCnt = ref(null);
const quizList = ref([]);

onMounted(() => {
  getQuizCnt((data) => {
    quizCnt.value = data.data;
  }, (err) => {
    console.log(err)
  })

  getQuizResultList((data) => {
    quizList.value = data.data;
    // console.log(data.data);
  }, (err) => {
    console.log(err)
  })

})

</script>

<style scoped></style>