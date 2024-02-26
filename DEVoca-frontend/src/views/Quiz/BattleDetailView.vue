<template>
  <div class="m-8 p-12 bg-white flex flex-col card shadow-3">
    <div class="w-full rounded-lg">
      <p class="text-3xl text-center font-jalnan">대결 퀴즈</p>
      <p class="text-sm text-center my-2">{{ formatDateTime(quizInfo.participateDate) }}</p>
      <div class="mt-1 flex justify-center">
        <img src='@/assets/images/quiz/list_win.png' class='w-12 h-12' v-if="imgRank[0]"/>
        <img src='@/assets/images/quiz/list_lose.png' class='w-12 h-12' v-if="imgRank[1]"/>
        <img src='@/assets/images/quiz/list_draw.png' class='w-12 h-12' v-if="imgRank[2]"/>
        <img src='@/assets/images/quiz/list_draw.png' class='w-12 h-12' v-if="imgRank[3]"/>
        <p class="self-center ml-3 text-2xl font-jalnan text-amber-400">{{ txtRank[quizInfo.type] }}</p>
      </div>
      <p class="text-xl text-center my-2"> {{ correctCnt }} / {{ quizCnt }} </p>
      <div class="text-end">
        with. <div class="inline bg-orange-50" @click="goBattleUserProfile">
          {{ battleUserInfo.battleUserNickname }}</div>
      </div>
      <hr class="bg-black my-2">
    </div>
    <div >
      <QuizDetailComponent class="mt-1" v-for="(quiz, index) in quizResultList"
      :key="index" :quiz="quiz" :index="index" v-if="quizResultList.length"/>
    </div>
  </div>
  
  
</template>

<script setup>
import QuizDetailComponent from "@/components/quiz/QuizDetailComponent.vue";
import { getBattleResultDetail, getQuiz } from '@/api/quiz'
import { useRoute, useRouter } from "vue-router";
import { ref, onMounted } from "vue";

const imgRank = ref([false, false, false, false]);
const txtRank = ref(["승리", "패배", "무승부", "대기중"]);
const router = useRouter();
const route = useRoute();
const { quizId } = route.params;
const quizInfo = history.state.quizInfo;
const score = ref(null);
const quizAnswerDTOList = ref([]);
const quizList = ref([]);
const quizResultList = ref([]);
const quizCnt = ref(null);
const correctCnt = ref(null);
const battleUserInfo = ref({});

onMounted(() => {
  imgRank.value[quizInfo.type] = true;
  getBattleResultDetail(quizId, (data) => {
    quizAnswerDTOList.value = data.data[0].quizAnswerDTOList;
    score.value = data.data[0].score;
    quizCnt.value = quizAnswerDTOList.value.length;
    correctCnt.value = score.value / quizCnt.value;

    battleUserInfo.value = {
      ...battleUserInfo.value,
      battleUserNickname: data.data[0].battleUserNickname,
      battleUserId: data.data[0].battleUserId,
      battleUserScore: data.data[0].battleUserScore,
    }

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

const goBattleUserProfile = function () {
  router.push({
    name: 'OtherUserProfileView',
    params: { id: battleUserInfo.value.battleUserId }
  })
}

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