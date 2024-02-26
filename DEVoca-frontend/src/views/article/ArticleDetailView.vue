<template>
  <ArticleDetailComponent :key="boardInfo" :board="boardInfo" />
  <p class="ml-5 mt-5">댓글 {{ comments.length }}개</p>
  <div class="mb-14">
    <div v-for="comment in comments" :key="comment.commentId">
      <CommentComponent :comment="comment" @update-comments="updateComments"/>
    </div>
  </div>
  <div class="input-container flex my-4 fixed bottom-16 left-0 right-0 bg-base-100">
    <input type="text" placeholder="댓글을 남겨보세요" class="mx-2 input input-bordered w-full" v-model="inputComment" @keyup.enter="submitComment"/>
    <button @click="submitComment" class="btn bg-devoca text-white text-lg">등록</button>
  </div>
</template>

<script setup>
import { ref, onBeforeMount } from 'vue';
import { getBoardDetail, getCommentList } from '@/api/board.js'
import ArticleDetailComponent from "@/components/article/ArticleDetailComponent.vue";
import CommentComponent from "@/components/article/CommentComponent.vue";
import { registComment } from "@/api/card";

const props = defineProps({
  boardId: String
})

const userInfo = ref({})
const boardInfo = ref({})
const comments = ref([])
const inputComment = ref(null);

const dateString = ref('')
const timeString = ref('')

onBeforeMount(() => {
  getBoardDetail(props.boardId, (res) => {
    boardInfo.value = res.data
    userInfo.value = {
      userIdx: res.data.userIdx,
      userId: res.data.userId,
      userNickName: res.data.userNickName,
      userImg: res.data.userImg,
      cardRegistDate: res.data.boardRegistDate
    }

    const datetime = new Date(boardInfo.value.boardRegistDate)
    const locale = 'ko-KR'
    const options = { timeZone: 'Asia/Seoul' }
    dateString.value = datetime.toLocaleDateString(locale, options)
    timeString.value = datetime.toLocaleTimeString(locale, options)
  }, (err) => {
    console.log(err);
    router.push({ name: 'MainView' });
    alert("존재하지 않는 게시글입니다.");
  })

  getCommentList("board", props.boardId, (res) => {
    comments.value = res.data
  }, null)
})


const submitComment = () => {
  const commentInfo = {
    cardBoardId: props.boardId,
    flag: 'board',
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
  getCommentList("board", props.boardId, (res) => {
    comments.value = res.data
  }, null)
}

</script>
