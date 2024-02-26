<template>
  <div class="m-2">
    <div class="flex text-center" v-if="route.query.q == 2">
      <p class="mt-2 ml-3 mr-2 text-xl font-jalnan text-devoca">단어</p>
      <p class="mt-2 text-xl font-jalnan">요청</p>
    </div>
    <form @submit.prevent="submitBoard">
      <select v-model="inputData.boardType" class="select select-bordered max-w-xs" v-if="route.query.q != 2">
        <option disabled selected>카테고리</option>
        <option value="0">자유 게시판</option>
        <option value="1">Q&A 게시판</option>
      </select>
      
      <h2 class="font-jalnan text-xl pt-4 m-2">제목</h2>
      <input v-model="inputData.boardTitle" type="text" placeholder="제목을 입력하세요" class="input input-bordered w-full" />

      <div>
        <h2 class="font-jalnan text-xl pt-4 m-2">내용</h2>
        <textarea v-model="inputData.boardContent" class="textarea textarea-bordered w-full h-[30vh] resize-none my-2" placeholder="나누고 싶은 글을 적어주세요"></textarea>
      </div>
      
      <div class="flex justify-end">
        <button type="submit" class="btn btn-md bg-devoca text-white text-lg">등록</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { createBoard } from '@/api/board';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute()
const router = useRouter();

const inputData = ref({
  boardType: '카테고리',
  boardTitle: null,
  boardContent: null,
});

const submitBoard = function() {
  if (inputData.value.boardTitle && inputData.value.boardContent) {
    inputData.value.boardTitle = inputData.value.boardTitle.trim();
    inputData.value.boardContent = inputData.value.boardContent.trim();
  } else {
    console.error('boardTitle 또는 boardContent가 존재하지 않습니다.');
    return;
  }

  if (route.query.q == 2) {
    inputData.value.boardType = 2
  }

  createBoard(inputData.value, 
    (res) => {
      if (inputData.value.boardType == 2) {
        router.push({ name: 'SearchView' });
      } else {
        router.push({ name: 'ArticleView' });
      }
    }, 
    (err) => {
      console.log(`err : ${err}`);
    }
  );
}
</script>