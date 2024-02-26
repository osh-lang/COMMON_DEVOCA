<template>
  <div>
    <dialog id="bottom_modal" class="modal modal-bottom">
      <div class="modal-box">
        <h3 class="font-bold text-lg">글쓰기</h3>
        <a href="/card/create">
          <p class="py-4">의견 카드 작성</p>
        </a>
        <a href="/article/create">
          <p class="py-4">게시판 글 작성</p>
        </a>
      </div>
      <form method="dialog" class="modal-backdrop">
        <button>close</button>
      </form>
    </dialog>
  </div>
</template>

<script setup>

</script>

<style scoped>

</style>