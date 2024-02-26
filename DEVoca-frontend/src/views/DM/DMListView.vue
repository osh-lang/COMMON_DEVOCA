<template>
  <div class="flex">
    <div>
      <p class="mt-5 mb-3 ml-5 text-xl font-jalnan text-devoca">D</p>
    </div>
    <div>
      <p class="mt-5 mb-3 text-xl font-jalnan">M</p>
    </div>
  </div>
  <div v-if="roomList == null" class="flex flex-col justify-center items-center h-[85vh]">
    <div class="text-center justify-between">참여한 채팅방이 없어요.</div>
  </div>
    <div v-for="room in roomList" :key="room" @click="message(room.roomUuid)">
      <div class="card card-side bg-base-100 items-center flex">
        <div class="avatar basis-1/4 shrink-0">
          <div class="rounded-full m-3">
            <img :src="room.userImg" />
          </div>
        </div>
        <div class="card-body flex p-3 grow">
            <h2 class="card-title font-jalnan mb-1">{{ room.userNickName }}</h2>
            <p>{{ room.lastMessage.length > 10 ? room.lastMessage.slice(0, 10) + '...' : room.lastMessage }}</p>
        </div>
        <div class="flex justify-end flex-col mr-5 shrink-0">
          <p class="text-center text-xs">{{formatDate(room.lastSendDate)}}</p>
          <p class="text-center text-xs mt-1">{{formatTime(room.lastSendDate)}}</p>
          <button v-if="room.unReadCnt != 0">
            <div class="badge bg-devoca_sky text-xs text-center w-6 h-6 mt-1">{{room.unReadCnt}}</div>
          </button>
        </div>
      </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { getDmRoomList } from '@/api/dm';
import router from "@/router";
import { useUserStore } from "@/stores/user";


const userStore = useUserStore();

const message = (roomUuid) => {
  // room을 사용하여 원하는 작업 수행
  // 예를 들어, 다음 화면으로 이동하고자 할 경우
  router.push({ name: 'DMMessageView', params: { roomUuid: roomUuid } });
};

const roomList = ref([]);

onMounted(() => {
  userStore.isDMNotify = false;
  getDmRoomList(
    ({ data }) => {
      roomList.value = data
    },
    (error) => {
      console.log(error);
    }
  )
});

const locale = 'ko-KR';
const options = {
  timeZone: 'Asia/Seoul',
  hour: "2-digit",
  minute: "2-digit"
};

const formatDate = (date) => {
  const dateTime = new Date(date);
  return `${dateTime.toLocaleDateString(locale)}`;
}

const formatTime = (date) => {
  const dateTime = new Date(date);
  return `${dateTime.toLocaleTimeString(locale, options)}`;
}
</script>

<style scoped></style>