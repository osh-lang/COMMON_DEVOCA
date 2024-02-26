<template>
  <div class="flex justify-center my-2 relative">
    <button class="btn btn-ghost indicator absolute -top-3 -right-4 z-10" @click="deleteComments(comment.commentId)" v-if="comment.userId == userStore.kakaoUserInfo.id">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" /></svg>
    </button>
    <div class="card h-fit w-full bg-base-100 shadow-xl">
      <AvartarComponent :userInfo="userInfo" />

      <div class="card-body p-4">
        <div class="flex justify-around">
          <p>{{ comment.commentContent }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import AvartarComponent from '@/components/common/AvatarComponent.vue'
import { useUserStore } from '@/stores/user'
import { deleteComment } from '@/api/card'

const props = defineProps({
  comment: Object
})
const emit = defineEmits(['updateComments'])
const userStore = useUserStore()

// console.log(props.comment)
const userInfo = ref({
  userId: props.comment.userId,
  userImg: props.comment.userImg,
  userIntro: props.comment.userIntro,
  userNickName: props.comment.userNickName,
  cardRegistDate: props.comment.commentRegistDate
})

const deleteComments = (commentId) => {
  deleteComment(commentId, (res) => {
    console.log(res)
    emit('updateComments')
  }, (err) => {
    console.log(err)
  })
}
</script>
