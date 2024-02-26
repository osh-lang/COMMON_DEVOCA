<template>
  <div>
    <div class="bg-neutral-100 py-1">
      <CardDetailComponent :card="card" v-if="card"/>
    </div>

    <div class="input-container flex mx-2 mt-4">
      <input type="text" placeholder="댓글을 남겨보세요" class="input input-bordered w-full" v-model="inputComment" @keyup.enter="submitComment"/>
      <button @click="submitComment" class="btn bg-devoca text-white text-lg">등록</button>
    </div>

    <div class="collapse collapse-arrow bg-base-100 shadow-xl m-2 w-auto" v-show="hasCommentResults" >
      <input type="checkbox" /> 
      <div class="collapse-title text-xl font-jalnan">
        댓글
      </div>
      <div class="collapse-content"> 
        <div class="flex flex-col">
          <CardCommentComponent v-for="(comment, index) in commentList" :key="index" :comment="comment" 
            @update-comments="updateComments"/>
        </div>
      </div>
    </div>
  </div>  
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, onBeforeRouteUpdate } from 'vue-router'
import CardDetailComponent from '@/components/feed/CardDetailComponent.vue'
import CardCommentComponent from "@/components/feed/CardCommentComponent.vue"
import { getCardDetail, getCommentList, registComment } from '@/api/card'
import router from "@/router"

const route = useRoute()
const card = ref(null)
const inputComment = ref('')
const hasCommentResults = ref(false)
const commentList = ref([])

const submitComment = () => {
  const commentInfo = {
    cardBoardId: route.params.id,
    flag: 'card',
    commentContent: inputComment.value
  }
  registComment(commentInfo, (res) => {
    inputComment.value = ''
    updateComments()
  }, (err) => {
    console.log(err)
  })
}

const updateComments = () => {
  getCommentList(route.params.id, (res) => {
    hasCommentResults.value = res.data.length > 0
    if (!hasCommentResults.value) return
    commentList.value = res.data
  }, (err) => {
    console.log(err)
  })
}

const ReloadCard = (id) => {
    getCardDetail(id, (res) => {
    card.value = res.data
    updateComments()
  }, (err) => {
    console.log(err)
  })
}

onMounted(() => {
  getCardDetail(route.params.id, (res) => {
    card.value = res.data
    updateComments()
  }, (err) => {
    console.log(err)
    router.push({ name: 'MainView' });
    alert("존재하지 않는 카드입니다.");
  })
})

onBeforeRouteUpdate(async (to, from, next) => {
  if (to.params.id !== from.params.id) {
    ReloadCard(to.params.id)
    next()
  }
})
</script>