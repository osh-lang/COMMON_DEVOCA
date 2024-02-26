<template>
  <dialog id="selectVocalist" class="modal">
    <div class="modal-box h-1/2 w-3/4">
      <form method="dialog">
        <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2" @click="closeModal">✕</button>
      </form>
      <div v-for="vocalist in vocalistInfo" class="flex py-4" role="button" @click="saveOrCancelVocalist(vocalist)">
        <BookmarkIcon :class="{ 'fill-devoca stroke-devoca' : vocalist.includeYN === 'Y' }" />
        <p>{{ vocalist.vocalistName }}</p>
      </div>
    </div>
  </dialog>
</template>

<script setup>
import { storeVocaList, cancelVocaList } from '@/api/vocalist'
import BookmarkIcon from "@/components/icon/BookmarkIcon.vue"

const props = defineProps({
  vocalistInfo : Object
})
const emit = defineEmits(['closeModal', 'loadVocalist'])

const closeModal = () => {
  emit('closeModal')
}

// ToDo : detailvocalist에서 추가삭제 시 카드 재로딩하기
const saveOrCancelVocalist = (vocalist) => {
  if (vocalist.includeYN === 'Y') {
    // 저장되어있으면 단어장에서 삭제
    cancelVocaList(vocalist.vocalistId, vocalist.cardId, (res) => {
      console.log('삭제완료')
      emit('loadVocalist')
    }, (err) => {
      console.log(err)
    })
  } else if (vocalist.includeYN === 'N') {
    // 저장 안되어있으면 단어장에 추가
    const data = {
      cardId: vocalist.cardId,
      vocalistId: vocalist.vocalistId
    }
    console.log(data)
    storeVocaList(data, (res) => {
      console.log('저장 완료')
      emit('loadVocalist')
    }, (err) => {
      console.log(err)
    })
  }
}

</script>