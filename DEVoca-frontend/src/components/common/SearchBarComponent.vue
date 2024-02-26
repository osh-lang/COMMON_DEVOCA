<template>
  <div>
    <div class="flex justify-start items-center p-2 relative " id="searchbar">
      <input
        class="input input-bordered w-full px-6"
        type="text"
        placeholder="검색어를 입력하세요"
        v-model="searchInput"
        @keyup.enter="searchBtnHandler"
      />
      <FindIcon class="mr-4" @click="searchBtnHandler" />
    </div>
  </div>
  <hr class="my-4">
</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { saveSearchKeyword } from '@/api/search'
import FindIcon from "@/components/icon/FindIcon.vue";

const route = useRoute()
const router = useRouter()

// url에 있는 쿼리 스트링을 입력 박스에 기본값으로 채워넣기
const searchInput = ref(route.query.q)

// 검색 버튼 클릭 핸들러
const searchBtnHandler = () => {
  // 입력이 없는 경우
  if (searchInput.value === "" || searchInput.value === undefined) return
  // 검색어 저장
  saveSearchKeyword(searchInput.value, () => {
    console.log("검색어 저장 완료");
    // 화면 전환
    router.push({
      name: 'SearchResultView',
      query: {
        q: searchInput.value
      }
    })
  }, (err) => {
    console.log(err);
  })

  
}
</script>
