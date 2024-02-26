<template>
  <!-- <QuizPopupComponent/> -->
  <div>
    <div v-for="card in cardList">
      <CardComponent :card="card" />
    </div>
    <WriteButtonComponent onclick="bottom_modal.showModal()"/>
    <BottomModalComponent />
    <div class="trigger"></div>
  </div>
</template>

<script setup>
// import QuizPopupComponent from "@/components/quiz/QuizPopupComponent.vue";
import CardComponent from '@/components/feed/CardComponent.vue'
import WriteButtonComponent from '@/components/feed/WriteButtonComponent.vue'
import BottomModalComponent from '@/components/common/BottomModalComponent.vue'
import { getCardList } from '@/api/card'
import { ref, onMounted, onBeforeUnmount } from 'vue'

const cardList = ref([])
const scrollNum = ref(0)
const observer = ref(null) // 스크롤 관찰 객체
const trigger = ref(null) // 화면 맨 아래 위치할 요소
const hasMoreCard = ref(true) // 더 불러올 데이터가 있는지 여부

// observer가 호출하는 callback 함수
const handleIntersection = (entries) => {
  entries.forEach((entry) => {
    if (hasMoreCard.value && entry.isIntersecting) {
      loadCards()
    }
  })
}

// 불러올 데이터 api
const loadCards = () => {
  getCardList(scrollNum.value, (res) => {
    if (res.data.length > 0) {
      cardList.value = [...cardList.value, ...res.data]
      scrollNum.value++
    } else {
      hasMoreCard.value = false
    }
  }, (err) => {
    console.log(err)
  })
}

onMounted(() => {
  // observer 정의 및 관찰 시작
  observer.value = new IntersectionObserver(handleIntersection, {threshold: 0.5})
  trigger.value = document.querySelector('.trigger')
  observer.value.observe(trigger.value)
})

onBeforeUnmount(() => {
  // 관찰 해제
  observer.value.disconnect()
})

</script>