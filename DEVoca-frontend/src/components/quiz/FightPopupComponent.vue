<template>
  <div class="card bg-base-100 shadow-xl m-2 w-96 h-auto text-center">
    <form method="dialog">
      <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2" @click="closeModal">✕</button>
    </form>
    <div class="card-body p-8 w-80 flex flex-col justify-center content-center self-center">
      <div class="w-full mt-1 flex">
        <p class="self-center text-lg">도전할 단어장을 선택하세요.</p>
      </div>
      <select v-model="selectedVocaList" @change="vocaListSelect" class="my-3 text-center text-base border-devoca select select-bordered w-full max-w-xs">
        <optgroup label="도전자의 단어장">
          <option v-for="(vocalist, index) in vocaListMe" :key="index" :value="vocalist">{{ vocalist.vocalistName }}</option>
        </optgroup>
        <optgroup label="응전자의 단어장">
          <option v-for="(vocalist, index) in vocaListOppo" :key="index" :value="vocalist">{{ vocalist.vocalistName }}</option>
        </optgroup>
      </select>
      <form @submit.prevent="" class="max-w-xs mx-auto">
        <label for="counter-input" class="block mb-2 text-base font-medium text-gray-900">문제 수</label>
        <div class="relative">
          <button type="button" id="decrement-button" @click="decreaseCnt" data-input-counter-decrement="counter-input"
            class="flex-shrink-0 bg-devoca_sky inline-flex items-center justify-center border border-gray-300 rounded-md h-6 w-6 focus:ring-gray-100 focus:ring-2 focus:outline-none">
            <svg class="w-2.5 h-2.5 text-gray-900" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
              viewBox="0 0 18 2">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 1h16" />
            </svg>
          </button>
          <input type="text" id="counter-input" data-input-counter
            class="flex-shrink-0 text-gray-900 border-0 bg-transparent text-base font-normal focus:outline-none focus:ring-0 max-w-[2.5rem] text-center"
            placeholder="" :value="selectWordCnt" required />
          <button type="button" id="increment-button" @click="increaseCnt" data-input-counter-increment="counter-input"
            class="flex-shrink-0 bg-devoca_sky inline-flex items-center justify-center border border-gray-300 rounded-md h-6 w-6 focus:ring-gray-100 focus:ring-2 focus:outline-none">
            <svg class="w-2.5 h-2.5 text-gray-900" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
              viewBox="0 0 18 18">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                d="M9 1v16M1 9h16" />
            </svg>
          </button>
        </div>
        <div class="mt-5">
          <button class="btn bg-devoca text-white w-40" @click="goQuizPage">도전하기</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { getBattleVocaList, createBattle } from '@/api/quiz';
import { useUserStore } from '@/stores/user';
import { useRouter } from 'vue-router'

const router = useRouter();

const props = defineProps({
  oppoUserId: String
})

const userStore = useUserStore();

const vocaListMe = ref([]);
const vocaListOppo = ref([]);
const loginUserId = ref(userStore.kakaoUserInfo['id'].toString());

const selectedVocaList = ref({});
const wordCnt = ref(0);
const selectWordCnt = ref(0);

onMounted(() => {
  getBattleVocaList(
    props.oppoUserId,
    ({ data }) => {
      console.log(data);
      data.forEach(element => {
        if(element.vocaCnt !== 0) {
          if (element.userId === loginUserId.value) {
            vocaListMe.value.push(element);
          } else {
            vocaListOppo.value.push(element);
          }
        }
      });
    },
    (error) => {
      console.log(error);
    })
})

const vocaListSelect = () => {
  console.log(selectedVocaList.value);
  wordCnt.value = selectedVocaList.value.vocaCnt;
  if(wordCnt.value > 10) selectWordCnt.value = 10;
  else selectWordCnt.value = wordCnt.value;
}

const increaseCnt = () => {
  if(wordCnt.value < 10) {
    if(selectWordCnt.value < wordCnt.value) selectWordCnt.value++;
  } else {
    if(selectWordCnt.value < 10) selectWordCnt.value++;
  }
}

const decreaseCnt = () => {
  if(selectWordCnt.value > 1) selectWordCnt.value--;
}

const sendVocaList = ref({
  fromUserId: "",
  toUserId: "",
  vocaListId: 0,
  wordCnt: 0
})

const quizId = ref(0);

const goQuizPage = () => {
  sendVocaList.value.fromUserId = userStore.kakaoUserInfo['id'].toString();
  sendVocaList.value.toUserId = props.oppoUserId;
  sendVocaList.value.vocaListId = selectedVocaList.value.vocalistId;
  sendVocaList.value.wordCnt = selectWordCnt.value;
  createBattle(
    sendVocaList.value,
    ({data}) => {
      quizId.value = data;
      router.push({ name: 'QuizPageView', query: { quizId: quizId.value, battleYn: true } });
    },
    (error) => {
      console.log(error);
    }
  )
}

const emit = defineEmits(['closeModal']);

const closeModal = () => {
  emit('closeModal');
}


</script>

<style scoped></style>