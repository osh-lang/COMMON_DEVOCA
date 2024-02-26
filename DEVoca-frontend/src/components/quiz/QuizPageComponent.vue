<template>
    <QuizResultComponent0 v-if="showModal0"
    :wordSumm="props.quizList[index].wordSumm" :wordNameKr="props.quizList[index].wordNameKr" :wordNameEn="props.quizList[index].wordNameEn"/>
    <QuizResultComponent1 v-if="showModal1"
    :wordSumm="props.quizList[index].wordSumm" :wordNameKr="props.quizList[index].wordNameKr" :wordNameEn="props.quizList[index].wordNameEn"/>
    <QuizFinishComponent v-if="showModal2"
    :Qnum="props.quizList.length" :Anum="score/10*props.quizList.length" :score="score" />
    <div v-if="!showModal" class="card bg-white shadow-xl m-2 w-96 h-fit flex flex-col items-center p-12">
      <img src="@/assets/images/quiz/quiz_page_logo.png" viewBox="0 0 24 24" class="stroke-info shrink-0 w-60 h-32" alt="devoca_logo">
      <div>
        <div class="text-slate-400 flex justify-end">{{ pageIndex }} / {{ quizList.length }}</div>
        <div class="w-80 p-5 rounded-lg bg-slate-100">Q. {{ Question }}</div>
        <div class="mt-5 flex items-center border-b border-devoca py-2">
          <input class="appearance-none bg-transparent border-none w-full text-gray-700 mr-3 py-1 px-2 leading-tight focus:outline-none"
          type="text" placeholder="답을 입력하세요" aria-label="Answer" v-model="answer"
          v-on:keydown.enter="goNext"/>
        </div>
        <div v-if="!answerYN" class="text-[red]">답을 입력해주세요!</div>
        <div class="flex justify-end">
          <button class="mt-2 flex-shrink-0 bg-devoca text-sm text-white py-1 px-4 rounded-3xl"
          type="button" @click="goNext">
            제 출
          </button>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import QuizResultComponent0 from "./QuizResultComponent0.vue";
  import QuizResultComponent1 from "./QuizResultComponent1.vue";
  import QuizFinishComponent from "./QuizFinishComponent.vue";
  import { useRoute, useRouter } from "vue-router";
  import { saveQuizResult, saveBattleResult } from '@/api/quiz'
  import { ref } from "vue";
  
  const emit = defineEmits(['add-answer']);
  const route = useRoute();
  const router = useRouter();
  const { index } = route.params;
  const pageIndex = Number(index) + 1;
  const answer = ref("");
  const quizYn = ref(0);
  const score = ref(0);
  const showModal = ref(false);
  const showModal0 = ref(false);  // 결과 : 오답일 경우
  const showModal1 = ref(false);  // 결과 : 정답일 경우
  const showModal2 = ref(false);  // 종료
  
  const props = defineProps({
    quizList: {
      type: Array,
      required: true
    },
    quizId: {
      type: Number,
      required: true
    },
    userId: {
      type: String,
      required: true
    },
    answerList: {
      type: Array,
      required: true
    },
    battleYn: {
      type: Number,
      required: true
    },
  });
  
  const answerYN = ref(true);

  let Question = props.quizList[index].wordSumm;
  Question = Question.replaceAll(props.quizList[index].wordNameKr, " [ ? ] ");
  Question = Question.replaceAll(props.quizList[index].wordNameEn, " [ ? ] ");
  Question = Question.replaceAll(props.quizList[index].wordNameEn.toLowerCase(), " [ ? ] ");
  
  const grading = function () {
    if (answer.value == props.quizList[index].wordNameKr
    || answer.value.toLowerCase() == props.quizList[index].wordNameEn.toLowerCase()) {
      quizYn.value = 1;
    }
    const quizAnswer = {
      "quizWordId": props.quizList[index].wordId,
      "quizAnswer": answer.value,
      "quizYn": quizYn.value
    };
    emit('add-answer', quizAnswer);
  }

  const goNext = function () {
    if(answer.value.length == 0) {
      answerYN.value = false;
    } else {
      // 채점하고 결과에 따라 창 띄우기
      grading();
      showModal.value = true;
      if (quizYn.value == 0) {
        showModal0.value = true;
      } else if(quizYn.value == 1) { 
        showModal1.value = true;
      }
      setTimeout(() => {
        timeout();
      }, 3000);
      }
    }

  const timeout = function () {
    // 마지막 문제 아니면 다음 문제로 넘기기
    if (pageIndex != props.quizList.length) {
      router.push({ name: "QuizPageComponent", params: { index: pageIndex } });
    } else {
      // 마지막 문제면 score 계산
      score.value = props.answerList.reduce((total, item) => {
        return total + item.quizYn;
      }, 0);
      score.value = score.value / props.quizList.length * 10;
      finish();
    }
  }
  const finish = function () {
    let data = {
      quizAnswerDTOList: props.answerList,
      score: score.value,
      quizId: props.quizId,
      userId: props.userId
    }

    // saveQuizResult api에 전부 담아서 보내기
    if (props.battleYn == 0) {
      saveQuizResult(data, () => {
        console.log("게릴라 퀴즈 결과 저장 성공");
      }, (err) => {
        console.log(err)
      })
    } else {
      saveBattleResult(data, () => {
        console.log("대결 퀴즈 결과 저장 성공");
      }, (err) => {
        console.log(err)
      })
    }

    // 종료 페이지
    showModal0.value = false;
    showModal1.value = false;
    showModal2.value = true;
  }
  </script>
  
  <style scoped></style>