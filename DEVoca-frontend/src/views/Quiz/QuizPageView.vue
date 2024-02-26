<template>
  <div class="bg-devoca_skyblue w-full h-[85dvh] pt-6 pb-6 flex justify-center items-center">
      <router-view v-if="quizList.length" :quizList="quizList" :quizId="quizId" :userId="userId"
      :answerList="answerList" :battleYn="battleYn" @add-answer="addAnswer" :key="$route.fullPath"></router-view>
  </div>
</template>
  
<script setup>
import { getQuiz } from '@/api/quiz'
import { useUserStore } from '@/stores/user'
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const userStore = useUserStore();
const quizId = ref(null);
const userId = userStore.kakaoUserInfo['id'];
const battleYn = ref(null);
const answerList = ref([]);
const addAnswer = (quizAnswer) => {
  answerList.value.push(quizAnswer);
};
const quizList = ref([]);

onMounted(() => {
  quizId.value = route.query.quizId;
  battleYn.value = route.query.battleYn;

  console.log(quizId.value);
  getQuiz(quizId.value, (data) => {
    quizList.value = data.data;
    console.log(quizList.value);
  }, (err) => {
    console.log(err)
  })

})


</script>
  
<style scoped>
    
</style>