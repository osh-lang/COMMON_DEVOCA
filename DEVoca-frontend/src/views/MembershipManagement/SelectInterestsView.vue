<template>
  <div class="flex items-center">
    <div class="card shrink-0 w-full bg-base-100">
      <div class="flex justify-center">
        <img src="@/assets/images/DEVoca_logo_en_only.png" class="w-3/4">
      </div>
      <p class="text-center mt-6">관심 분야를 선택해 주세요</p>
      <p class="text-center mt-1 mb-5">최대 3개 선택 가능</p>
      <div class="grid grid-cols-2 gap-2">
        <!-- <div v-for="(interest, index) in interests" :key="index" class="flex">
          <button type="submit" class="btn flex-grow m-2"
            :class="{ 'bg-white': !button.clicked, 'bg-devoca': button.clicked }">
            @click="handleClick(groupIndex, index)">
            {{ interest }}
          </button>
        </div> -->
        <div v-for="(interest, index) in interests" :key="index">
          <button type="submit" class="btn m-2 w-full">{{ interest }}</button>
        </div>
      </div>
      <div class="form-control mt-6">
        <button @click="submitInterest" class="btn bg-devoca text-white">다음</button>
      </div>
      <RouterLink :to="{name: 'FollowRecommendationView'}" class="text-center underline mt-5">나중에 하기</RouterLink>
    </div>
  </div>
</template>

<script setup>
import router from '@/router';
import { ref } from 'vue'

const buttons = ref(Array.from({ length: 10 }, () => ({ clicked: false })))

const interests = ref(['Frontend', 'Backend', 'mobile', 'AI/딥러닝', 'DB', 'DevOps', '빅데이터', '게임', '네트워크', '운영체제'])

// Split buttons into groups of 2
// for (let i = 0; i < buttons.value.length; i += 2) {
//   buttonGroups.value.push(buttons.value.slice(i, i + 2));
// }

// const handleClick = (groupIndex, buttonIndex) => {
//   const button = buttonGroups.value[groupIndex][buttonIndex];
//   button.clicked = !button.clicked;
// };

const submitInterest = function () {
  // axios 요청
  // 팔로우 추천 페이지로 이동
}
</script>

<style scoped></style>