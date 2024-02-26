<template>
  <div class="card bg-white shadow-xl m-2 w-96 h-auto">
    <div class="card-body p-8 w-80 flex flex-col items-center self-center">
      <img src="@/assets/images/quiz/quiz_logo.png" viewBox="0 0 24 24" class="stroke-info shrink-0 w-56 h-20"
        alt="devoca_logo">
      <div class="relative">
        <p class="text-3xl font-jalnan mt-5">퀴즈 종료!</p>
      </div>
      <div class="flex">
        <img v-if="score > 0" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Activities/Party%20Popper.png" alt="Party Popper" width="50" height="50" />
        <img v-if="score == 0" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Face%20Holding%20Back%20Tears.png" alt="Face Holding Back Tears" width="50" height="50" />
        <p class="mt-5 mx-2 text-center w-auto text-base">{{ Qnum }} 문제 중 {{ Anum }}개 정답</p>
        <img v-if="score > 0" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Activities/Party%20Popper.png" alt="Party Popper" width="50" height="50" />
        <img v-if="score == 0" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Face%20Holding%20Back%20Tears.png" alt="Face Holding Back Tears" width="50" height="50" />
      </div>
      <div>
        <div class="w-80 mb-5 p-5 rounded-lg text-amber-500 flex justfy-center text-center">
          <p class="text-3xl text-devoca font-bold">{{ Math.ceil(score*10) }}점</p>
        </div>
        <div class="flex justify-evenly">
          <button class="mt-2 flex-shrink-0 bg-devoca_sky hover:bg-devoca_skyblue text-sm text-devoca py-1 px-4 rounded-3xl"
            type="button" @click="goQuizList">
            결과 확인하기
          </button>
          <button class="mt-2 flex-shrink-0 bg-devoca_sky hover:bg-devoca_skyblue text-sm text-devoca py-1 px-4 rounded-3xl"
          type="button" @click="goMain">
            메인으로
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
const router = useRouter();

const props = defineProps({
  Qnum: {
    type: Number,
    required: true
  },
  Anum: {
    type: Number,
    required: true
  },
  score: {
    type: Number,
    required: true
  }
});

const goQuizList = function () {
  router.push({ name: 'QuizListView' });
}
const goMain = function () {
  router.push({ name: 'FeedListView' });
}

</script>

<style scoped></style>