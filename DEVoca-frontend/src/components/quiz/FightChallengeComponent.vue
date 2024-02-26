<template>
  <div class="card bg-base-100 shadow-xl m-2 w-96 h-auto text-center">
    <div class="card-body p-8 w-80 flex flex-col items-center self-center">
      <div class="w-full mt-1 flex">
        <p class="m-3 self-center font-jalnan text-3xl text-devoca">DEVOCA</p>
        <img src="@/assets/images/quiz/fight.png">
      </div>
      <img src="@/assets/images/quiz/challenge.png" class="mb-5">
      <div class="flex">
        <p class="font-bold text-lg mr-2">{{ props.userNickName }}</p> <p class="text-lg">님이</p>
      </div>
      <p class="text-lg">도전장을 보내셨습니다!</p>
      <p class="text-lg">수락하시겠습니까??</p>
      <div class="modal-action w-60 mt-5 flex flex-row justify-evenly">
        <button class="btn bg-devoca text-white w-20" @click="goQuizPage">수락</button>
        <form method="dialog">
          <button class="btn bg-devoca text-white w-20" @click="closeModal">거절</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const props = defineProps({
  userNickName: String,
  dmBattleQuizId: Number
})

const router = useRouter();

const emit = defineEmits(['closeModal']);

const goQuizPage = function () {
    router.push({ name: 'QuizPageView', query: { quizId: props.dmBattleQuizId, battleYn: true } });
  }

const closeModal = () => {
  emit('closeModal');
}

</script>

<style scoped></style>