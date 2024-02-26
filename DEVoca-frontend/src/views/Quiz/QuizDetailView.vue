<template>
  <div class="m-8 p-12 bg-white flex flex-col card shadow-3">
    <div class="w-full rounded-lg">
      <p class="text-3xl text-center font-jalnan">게릴라 퀴즈</p>
      <p class="text-sm text-center my-2">{{ formatDateTime(quizInfo.participateDate) }}</p>
      <div class="mt-1 flex justify-center">
        <img v-if="imgRank[0]" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Activities/1st%20Place%20Medal.png" alt="1st Place Medal" width="35" height="35" />
        <img v-if="imgRank[1]" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Activities/2nd%20Place%20Medal.png" alt="2nd Place Medal" width="35" height="35" />
        <img v-if="imgRank[2]" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Activities/3rd%20Place%20Medal.png" alt="3rd Place Medal" width="35" height="35" />
        <img src='@/assets/images/quiz/list_4.png' class='w-12 h-12' v-if="imgRank[3]"/>
        <p class="self-center ml-3 text-2xl font-jalnan text-amber-400">{{ txtRank[quizInfo.type] }}</p>
      </div>
      <p class="text-xl text-center my-2"> {{ correctCnt }} / {{ quizCnt }} </p>
      <hr class="bg-black my-2">
    </div>
    <div>
      <QuizDetailComponent class="mt-1" v-for="(quiz, index) in quizResultList"
      :key="index" :quiz="quiz" :index="index" v-if="quizResultList.length"/>
    </div>
  </div>
</template>

<script setup>
import QuizDetailComponent from "@/components/quiz/QuizDetailComponent.vue";
import { getQuizResultDetail, getQuiz } from '@/api/quiz'
import { useRoute } from "vue-router";
import { ref, onMounted } from "vue";

const imgRank = ref([false, false, false, false]);
const txtRank = ref(["1등", "2등", "3등", "순위권 밖"]);
const route = useRoute();
const { quizId } = route.params;
const quizInfo = history.state.quizInfo;
const score = ref(null);
const quizAnswerDTOList = ref([]);
const quizList = ref([]);
const quizResultList = ref([]);
const quizCnt = ref(null);
const correctCnt = ref(null);

onMounted(() => {
  imgRank.value[quizInfo.type] = true;
  getQuizResultDetail(quizId, (data) => {
    quizAnswerDTOList.value = data.data[0].quizAnswerDTOList;
    score.value = data.data[0].score;
    quizCnt.value = quizAnswerDTOList.value.length;
    correctCnt.value = score.value * 10 / quizCnt.value;
    getQuiz(quizId, (data) => {
      quizList.value = data.data;

      quizResultList.value = quizAnswerDTOList.value.map((item, index) => { 
        return { ...item, ...quizList.value[index] };
      })

    }, (err) => {
      console.log(err)
    })
  }, (err) => {
    console.log(err)
  });

})

// 일시 설정
const locale = 'ko-KR';
const options = {
  timeZone: 'Asia/Seoul',
  hour: "2-digit",
  minute: "2-digit"
};

const formatDateTime = (date) => {
  const dateTime = new Date(date);
  return `${dateTime.toLocaleDateString(locale, options)}`;
}
</script>

<style scoped></style>