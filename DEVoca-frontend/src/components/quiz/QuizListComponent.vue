<template>
    <button class="btn btn-lg w-full rounded-lg bg-base-100 shadow-xl my-2 mt-5 flex flex-col"
    @click="goQuizDetail">
        <div class="flex justify-between items-center space-x-7">
          <div class='w-16 h-10 mt-5' v-if="!battleYn">
            <p class='text-sm bg-orange-200 rounded-lg'>게릴라</p>
          </div>
          <div class='w-16 h-10 mt-5' v-if="battleYn">
            <p class='text-sm bg-devoca_skyblue rounded-lg'>대결</p>
          </div>
          <div class="flex flex-col items-center">
            <p class="text-center text-xl">{{ txtRank[type] }}</p>
            <p class="text-center text-sm w-24">{{ formatDate(props.quiz.participateDate) }}</p>
          </div>
          <div>
            <img v-if="imgRank[0]" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Activities/1st%20Place%20Medal.png" alt="1st Place Medal" width="45" height="45" />
            <img v-if="imgRank[1]" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Activities/2nd%20Place%20Medal.png" alt="2nd Place Medal" width="45" height="45" />
            <img v-if="imgRank[2]" src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Activities/3rd%20Place%20Medal.png" alt="3rd Place Medal" width="45" height="45" />
            <img src='@/assets/images/quiz/list_4.png' class='w-12 h-12' v-if="imgRank[3]"/>
            <img src='@/assets/images/quiz/list_win.png' class='w-12 h-12' v-if="imgRank[4]"/>
            <img src='@/assets/images/quiz/list_lose.png' class='w-12 h-12' v-if="imgRank[5]"/>
            <img src='@/assets/images/quiz/list_draw.png' class='w-12 h-12' v-if="imgRank[6]"/>
            <img src='@/assets/images/quiz/list_wait.png' class='w-12 h-12' v-if="imgRank[7]"/>
          </div>
        </div>
    </button>
</template>
  
<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const type = ref(0);
const battleYn = ref(0);
const imgRank = ref([false, false, false, false, false, false, false, false]);
const txtRank = ref(["1등", "2등", "3등", "순위권 밖", "승리", "패배", "무승부", "대기중"]);

const props = defineProps({
    quiz: Object
})

onMounted(() => {
  console.log(props.quiz.battleId);

    if (props.quiz.battleId == null || props.quiz.battleId == 0 ) { // 게릴라 퀴즈라면
      // 이미지, 순위 계산
      switch (props.quiz.rank) {
        case 1:
          type.value = 0;
          break;
        case 2:
          type.value = 1;
          break;
        case 3:
          type.value = 2;
          break;
        default:
          type.value = 3;
          break;
      }
    } else {  // 대결이라면
      // 이미지, 순위 계산
      switch (props.quiz.battleWinYN) {
        case 1:
          type.value = 4;
          break;
        case 0:
          type.value = 5;
          break;
        case 2:
          type.value = 6;
          break;
        default:
          type.value = 7;
          break;
      }
  }
  battleYn.value = Math.floor(type.value / 4);
  imgRank.value[type.value] = true;
})

const goQuizDetail = function () {
  if (battleYn.value == 0) {
    router.push({
      name: 'QuizDetailView', params: { quizId: props.quiz.quizId },
      state: {
        quizInfo: {
          participateDate: props.quiz.participateDate,
          rank: props.quiz.rank,
          type: type.value
        }
      }
    });
  } else {
    router.push({
      name: 'BattleDetailView', params: { quizId: props.quiz.quizId },
      state: {
        quizInfo: {
          participateDate: props.quiz.participateDate,
          battleWinYN: props.quiz.battleWinYN,
          type: type.value - 4
        }
      }
    });
  }
}

// 일시 설정
const locale = 'ko-KR';
const formatDate = (date) => {
  const dateTime = new Date(date);
  return `${dateTime.toLocaleDateString(locale)}`;
}

</script>
  
<style scoped>
</style>