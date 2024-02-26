<template>
  <div class="flex justify-center m-2">
    <div class="card h-fit w-full bg-base-100 shadow-xl">
      <div class="card-body p-6">
        <p class="card-title text-xl">{{ board.boardTitle }}</p>
        <div class="flex items-center gap-2">
          <div class="avatar basis-8">
            <img :src="board.userImg" class="rounded-full" />
          </div>
          <h2 class="grow text-sm font-jalnan">{{ board.userNickName }}</h2>
          <div class="flex flex-col basis-1/3 text-xs text-right">
            <span :key="dateString">{{ dateString }}</span>
            <span :key="timeString">{{ timeString }}</span>
          </div>
        </div>
        <hr>
        <p>{{ board.boardContent }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps({
  board: Object
})

const datetime = new Date(props.board.boardRegistDate)
const locale = 'ko-KR'
const options = { timeZone: 'Asia/Seoul' }
const dateString = ref(datetime.toLocaleDateString(locale, options))
const timeString = ref(datetime.toLocaleTimeString(locale, options))
</script>
