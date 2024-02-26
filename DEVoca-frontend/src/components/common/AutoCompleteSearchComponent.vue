<template>
  <div class="flex justify-start items-center p-2 relative" id="autocomplete" 
    data-te-input-wrapper-init>
    <input
      class="input input-bordered w-full px-6"
      type="text"
      placeholder="단어를 선택하세요"
      v-model="inputDiv"
    />
    <svg
      class="absolute right-6 z-10 cursor-pointer"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M10 17C13.866 17 17 13.866 17 10C17 6.13401 13.866 3 10 3C6.13401 3 3 6.13401 3 10C3 13.866 6.13401 17 10 17Z"
        stroke="#4B5563"
        stroke-width="1.66667"
        stroke-linecap="round"
        stroke-linejoin="round"
      />
      <path
        d="M21 21L15 15"
        stroke="#4B5563"
        stroke-width="1.66667"
        stroke-linecap="round"
        stroke-linejoin="round"
      />
    </svg>
  </div>

  <div v-if="isWordSelected" class="relative">
    <button class="btn btn-ghost absolute right-0 z-10" @click="cancelWord">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" /></svg>
    </button>
    <WordComponent :word="selectedWord" />
  </div>

  <hr class="my-4">
</template>

<script setup>
import { Autocomplete, Input, initTE } from "tw-elements"
import { getAutoCompList } from '@/api/search'
import { ref, onMounted } from 'vue'
import { getWordInCard } from '@/api/word'
import WordComponent from '@/components/word/WordComponent.vue'
initTE({ Input })

const emit = defineEmits(['selectedWord'])
const wordList = ref(null)
const isWordSelected = ref(false)
const selectedWord = ref(null)
const inputDiv = ref(null)

const dataFilter = (value) => {
  return wordList.value.filter((item) => {
    return item.wordNameEn.toLowerCase().startsWith(value.toLowerCase()) 
    || item.wordNameKr.toLowerCase().startsWith(value.toLowerCase())
  })
}

const showSelectWord = async (wordId) => {
  getWordInCard(wordId, (res) => {
    selectedWord.value = res.data
    isWordSelected.value = true
    emit('selectedWord', selectedWord.value.wordId)
    inputDiv.value = ''
  }, (err) => {
    console.log(err)
  })
}

const cancelWord = () => {
  isWordSelected.value = false
  selectedWord.value = null
}

onMounted(() => {
  getAutoCompList((res) => {
    wordList.value = res.data
  }, (err) => {
    console.log(err)
  })

  const basicAutocomplete = document.querySelector('#autocomplete')
  new Autocomplete(basicAutocomplete, {
    filter: dataFilter,
    displayValue: (value) => value.wordNameEn,
    noResults: '해당 단어는 없습니다',
    itemContent: (result) => {
    return `
    <div class="flex flex-col">
      <div class="font-medium">${result.wordNameEn}</div>
      <div class="text-xs text-gray-400">${result.wordNameKr}</div>
    </div>
  `
  },
})

basicAutocomplete.addEventListener('itemSelect.te.autocomplete', (e) => {
    showSelectWord(e.value.wordId)
})
})

</script>
