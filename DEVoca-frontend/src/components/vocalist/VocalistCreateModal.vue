<template>
  <div>
    <dialog id="NewVocalist" class="modal">
      <div class="modal-box">
        <form method="dialog">
          <button class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
        </form>
        <h3 class="font-bold text-lg">새 단어장 생성</h3>
        <div class="input-container flex mx-2 mt-4">
          <input type="text" placeholder="단어장 이름을 입력하세요" class="input input-bordered w-full" v-model="inputName" />
          <button @click="makeNewVocalist" class="btn bg-devoca text-white text-lg">생성</button>
        </div>
      </div>
    </dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { createVocaList } from '@/api/vocalist'

const inputName = ref('')
const emit = defineEmits(['newVocalist'])

const makeNewVocalist = () => {
  if (inputName.value.trim()) {
    const vocalistInfo = {
      vocalistName: inputName.value,
      cardId: 0
    }
    createVocaList(vocalistInfo, (res) => {
      emit('newVocalist')
      const vocalistModal = document.getElementById('NewVocalist')
      vocalistModal.close()
      inputName.value = ''
    }, (err) => {
      console.log(err)
    })
  } else {
    window.alert('이름을 입력해주세요')
  }
}
</script>