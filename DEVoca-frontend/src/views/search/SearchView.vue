<template>
  <div class="m-2">
    <SearchBarComponent />

    <h3 class="font-jalnan text-xl ml-2">최근 검색어</h3>
    <div class="mb-4">
      <p v-if="recentKeywordList.length === 0" class="text-center m-8">최근 검색어가 없습니다.</p>
      <RouterLink v-else :to="{ name: 'SearchResultView', query: { q: item } }" v-for="(item, index) in recentKeywordList"
        :key="index" role="button" class="btn m-2 px-6 bg-devoca bg-opacity-50">{{ item }}</RouterLink>
    </div>

    <h3 class="font-jalnan text-xl ml-2 mt-4">인기 검색어</h3>
    <table class="table table-lg text-center">
      <thead class="text-sm">
        <tr>
          <th class="w-20 max-w-20">순위</th>
          <th>검색어</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="recommendWordList.length === 0">
          <th class="w-20 max-w-20"></th>
          <td>인기 검색어가 없습니다.</td>
        </tr>
        <tr v-else v-for="(item, index) in recommendWordList" :key="index">
          <th class="w-20 max-w-20">{{ index + 1 }}</th>
          <td>
            <router-link :to="{ name: 'SearchResultView', query: { q: item.wordNameEn } }">
              {{ item.wordNameEn }}
            </router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import SearchBarComponent from '@/components/common/SearchBarComponent.vue'
import { getRecentKeyword, getRecommendWord } from "@/api/search";

const recentKeywordList = ref([])
const recommendWordList = ref([])

onMounted(async () => {
  await getRecentKeyword((res) => {
    if (res.data.length > 0) {
      recentKeywordList.value = res.data
    }
  }, null)

  await getRecommendWord((res) => {
    console.table(res.data)
    if (res.data.length > 0) {
      recommendWordList.value = res.data.slice(0, 5)
    }
  })
})

</script>
