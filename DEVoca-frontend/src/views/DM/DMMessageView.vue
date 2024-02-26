<template>
  <div class="flex flex-col justify-between h-full">
    <div class="card card-side bg-base-100 h-24 w-full z-10 shrink-0">
      <div class="flex items-center">
        <div class="ml-5">
          <svg @click="goDmList" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"
            class="w-8 h-8">
            <path fill-rule="evenodd"
              d="M7.28 7.72a.75.75 0 0 1 0 1.06l-2.47 2.47H21a.75.75 0 0 1 0 1.5H4.81l2.47 2.47a.75.75 0 1 1-1.06 1.06l-3.75-3.75a.75.75 0 0 1 0-1.06l3.75-3.75a.75.75 0 0 1 1.06 0Z"
              clip-rule="evenodd" />
          </svg>
        </div>
        <div class="flex items-center" @click="goUser">
          <div class="avatar ml-5">
            <div class="w-14 h-14 rounded-full">
              <img :src="dmUser.userImg" />
            </div>
          </div>
          <div class="card-body pl-5">
            <div>
              <h2 class="card-title text-lg">{{ dmUser.userNickName }}</h2>
              <p class="text-sm">{{ dmUser.userIntro }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="chat" class="overflow-y-scroll h-full grow-0 scrollbar-hide">
      <div class="trigger"></div>
      <div v-for="(chat, index) in messageList.slice().reverse()" :key="index">
        <div v-if="chat.sendUserId == dmUser.userId" class="chat chat-start mt-3 ml-5 z-0">
          <div class="chat-bubble break-words bg-devoca_sky text-black">
            <div>{{ chat.dmContent }}</div>
            <div v-if="chat.dmBattleQuizId != 0" class="flex justify-end">
              <button class="w-14 h-7 bg-white text-black text-xs rounded-full mt-5" @click="selectModal(chat.dmBattleQuizId)">확인</button>
            </div>
          </div>
          <div class="chat-footer">
            <time class="text-xs opacity-50">{{ formatDateTime(chat.dmSendDate) }}</time>
          </div>
        </div>
        <div v-if="chat.sendUserId !== dmUser.userId" class="chat chat-end mt-3 mr-5 z-0">
          <div class="chat-bubble break-words bg-devoca text-white">
            <div>{{ chat.dmContent }}</div>
          </div>
          <div class="chat-footer">
            <time class="text-xs opacity-50">{{ formatDateTime(chat.dmSendDate) }}</time>
          </div>
        </div>
      </div>
      <div v-for="(chat, index) in messages" :key="index">
        <div v-if="chat.sendUserId == dmUser.userId" class="chat chat-start mt-3 ml-5 z-0">
          <div class="chat-bubble break-words bg-devoca_sky text-black">
            <div>{{ chat.dmContent }}</div>
            <div v-if="chat.dmBattleQuizId != 0" class="flex justify-end">
              <button class="w-14 h-7 bg-white text-black text-xs rounded-full mt-5" @click="selectModal(chat.dmBattleQuizId)">확인</button>
            </div>
          </div>
          <div class="chat-footer">
            <time class="text-xs opacity-50">{{ formatDateTime(chat.dmSendDate) }}</time>
          </div>
        </div>
        <div v-if="chat.sendUserId !== dmUser.userId" class="chat chat-end mt-3 mr-5 z-0">
          <div class="chat-bubble break-words bg-devoca text-white">
            <div>{{ chat.dmContent }}</div>
          </div>
          <div class="chat-footer">
            <time class="text-xs opacity-50">{{ formatDateTime(chat.dmSendDate) }}</time>
          </div>
        </div>
      </div>
    </div>
    <div class="input-container  bg-base-100 w-full flex pl-3 py-3 bottom-0">
      <input type="text" placeholder="메시지 입력" class="input input-bordered w-full" v-model="message"
        @keyup.enter="sendMessage" />
      <button @click="sendMessage" class="btn bg-devoca text-white mx-2">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
          <path
            d="M3.478 2.404a.75.75 0 0 0-.926.941l2.432 7.905H13.5a.75.75 0 0 1 0 1.5H4.984l-2.432 7.905a.75.75 0 0 0 .926.94 60.519 60.519 0 0 0 18.445-8.986.75.75 0 0 0 0-1.218A60.517 60.517 0 0 0 3.478 2.404Z" />
        </svg>
      </button>
    </div>
  </div>
  <dialog id="FightChallengeModal" class="modal sm:modal-middle" :class="{'modal-open': isShowModal}">
    <FightChallengeComponent @close-modal="isShowModal=false" :userNickName="dmUser.userNickName" v-if="isShowModal" :dmBattleQuizId="dmBattleQuizId"/>
  </dialog>
</template>


<script setup>
import { ref, onMounted, onUnmounted, onBeforeUnmount, nextTick } from 'vue';
import { getDmList, getDmUser } from '@/api/dm'
import { useUserStore } from '@/stores/user';
import router from '@/router';
import Stomp from 'stompjs';
import FightChallengeComponent from '@/components/quiz/FightChallengeComponent.vue';

const props = defineProps({
  roomUuid: String
})

const userStore = useUserStore();

const dmUser = ref({});
const messageList = ref([]);
const messages = ref([]);

const scrollNum = ref(0)
const observer = ref(null) // 스크롤 관찰 객체
const trigger = ref(null) // 화면 맨 아래 위치할 요소
const hasMoreDm = ref(true) // 더 불러올 데이터가 있는지 여부

const scrollHeight = ref(0);

onMounted(() => {
  getDmUser(
    props.roomUuid,
    ({ data }) => {
      dmUser.value = data;
    },
    (error) => {
      console.log(error);
    });

    // observer 정의 및 관찰 시작
  observer.value = new IntersectionObserver(handleIntersection, {threshold: 0.5});
  trigger.value = document.querySelector('.trigger');
  observer.value.observe(trigger.value);

  connect(props.roomUuid);
})

const loadDmList = () => {
  getDmList(
    props.roomUuid, scrollNum.value,
    ({ data }) => {
      if(data.length > 0) {
        messageList.value = [...messageList.value, ...data]
        nextTick(() => {
          const chat = document.getElementById('chat');
          chat.scrollTop = chat.scrollHeight - scrollHeight.value;
        })
        scrollNum.value++;
      } else {
        hasMoreDm.value = false;
      }
    },
    (error) => {
      console.log(error);
    });
}

// observer가 호출하는 callback 함수
const handleIntersection = (entries) => {
  entries.forEach((entry) => {
    if (hasMoreDm.value && entry.isIntersecting) {
      const chat = document.getElementById('chat');
      scrollHeight.value = chat.scrollHeight;
      loadDmList()
    }
  })
}

onBeforeUnmount(() => {
  // 관찰 해제
  observer.value.disconnect()
})


// websocket
const socket = ref(null);
const stompClient = ref(null);

const message = ref('');

const dm = ref({
  sendUserId: "",
  dmContent: "",
  dmSendDate: "",
  dmBattleQuizId: 0,
})

// websocket 연결
const connect = (uuid) => {
  if (stompClient.value && stompClient.value.connected) {
    if (socket.value.connected) stompClient.value.disconnect();
  }

  socket.value = new WebSocket('wss://i10d112.p.ssafy.io/devoca/chat');
  // socket.value = new WebSocket('ws://localhost/devoca/chat');
  stompClient.value = Stomp.over(socket.value);

  stompClient.value.connect({}, () => {
    console.log('Connected to WebSocket : ' + uuid);
    dm.value.sendUserId = userStore.kakaoUserInfo['id'];

    stompClient.value.send(`/pub/chat/${uuid}/enter`, {}, JSON.stringify({ userId: dm.value.sendUserId, lastDate: new Date() })); // 유저 입장

    //메시지 수신
    stompClient.value.subscribe(`/sub/chat/${uuid}`, (message) => {
      displayMessage(JSON.parse(message.body));
    });
  });

  // websocket 재연결
  socket.value.onclose = function () {
    console.log("WebSocket 연결 해제. 재연결 시도 중..");
    setTimeout(connect(uuid), 1000);
  }
};

// 화면에 메시지 표시
const displayMessage = (message) => {
  messages.value.push(message);
  nextTick(() => {
    const chat = document.getElementById('chat');
    chat.scrollTop = chat.scrollHeight;
  })
};

// 메시지 전송
const sendMessage = () => {
  dm.value.sendUserId = userStore.kakaoUserInfo['id'];
  dm.value.dmContent = message.value;
  dm.value.dmSendDate = new Date();

  if (dm.value.dmContent.trim() !== '') {
    stompClient.value.send(`/pub/chat/${props.roomUuid}`, {}, JSON.stringify(dm.value));
    message.value = '';
  }
}

// 유저 퇴장
const exitChat = () => {
  stompClient.value.send(`/pub/chat/${props.roomUuid}/exit`, {}, JSON.stringify({ userId: dm.value.sendUserId, lastDate: new Date() }));

  stompClient.value.disconnect();
  console.log("웹소켓 연결 해제");
}

const goDmList = () => {
  router.push({ name: 'DMListView', params: { id: userStore.kakaoUserInfo['id'] } });
}

const goUser = () => {
  router.push({name : 'OtherUserProfileView', params: {id: dmUser.value.userId}})
}

onUnmounted(() => {
  exitChat();
})

// 메시지 시간 설정
const locale = 'ko-KR';
const options = {
  timeZone: 'Asia/Seoul',
  hour: "2-digit",
  minute: "2-digit"
};

const formatDateTime = (date) => {
  const dateTime = new Date(date);
  return `${dateTime.toLocaleDateString(locale, options)}`;
}


// 도전장 모달
const isShowModal = ref(false);
const dmBattleQuizId = ref(0);

const selectModal = (battleQuizid) => {
  if(battleQuizid === -1) {
    alert("이미 참여한 대결입니다.");
  } else {
    dmBattleQuizId.value = battleQuizid;
    isShowModal.value = true;
  }
}

</script>

<style scoped></style>