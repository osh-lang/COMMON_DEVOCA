<template>
  <div class="flex flex-col items-center ml-12 mr-8 my-4">
    <div class="flex flex-row w-full justify-center" v-for="vocalist in vocalistInfo">
      <div @click="goVocalistDetail(vocalist)"
        class="btn btn-lg w-full bg-base-100 shadow-xl flex justify-between my-2">
        <p>{{ vocalist.vocalistName }}</p>
        <RightArrowIcon />
      </div>
      <button class="btn btn-ghost my-auto btn-sm p-2 ml-2" @click="deleteVocalists(vocalist.vocalistId)">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" /></svg>
      </button>
    </div>
    <button class="btn btn-circle m-3 bg-devoca bg-opacity-50" onclick="NewVocalist.showModal()">
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
        <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
      </svg>
    </button>
  </div>
  <VocalistCreateModal @new-vocalist="loadVocalist"/>

</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from "vue-router"
import RightArrowIcon from '@/components/icon/RightArrowIcon.vue'
import VocalistCreateModal from '@/components/vocalist/VocalistCreateModal.vue'
import { getVocaList, deleteVocaList } from '@/api/vocalist'
const router = useRouter();
const vocalistInfo = ref(null)

const loadVocalist = () => {
  getVocaList((res) => {
    vocalistInfo.value = res.data
    // console.log(res.data)
  }, (err) => {
    console.log(err)
  })
}

const deleteVocalists = (id) => {
  const confirmResult = confirm("단어장을 삭제하시겠어요?")
  if (confirmResult) {
    deleteVocaList(id, (res) => {
      loadVocalist()
    }, (err) => {
      console.log(err)
    })
  } else {
    return
  }
}

const goVocalistDetail = (vocalist) => {
  router.push({
    name: 'VocalistDetailView',
    params: { id: vocalist.vocalistId },
    state: { vocalistName: vocalist.vocalistName }
  });
};

onMounted(() => {
  loadVocalist()
})
</script>