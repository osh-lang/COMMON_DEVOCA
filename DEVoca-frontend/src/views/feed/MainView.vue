<template>
  <div class="py-16 relative">
    <TopNavbarComponent/>
    <div v-if="popup" @click="isPopupClicked"
    class="z-30 fixed">
      <NotificationComponent :pushedData="pushedData"/>
    </div>
    <div class="flex justify-center" v-if="quizPopup" @click="isPopupClicked">
      <QuizPopupComponent/>
    </div>

    <RouterView/>
    <BottomNavbarComponent />
  </div>
</template>

<script setup>
import TopNavbarComponent from '@/components/navbar/TopNavbarComponent.vue'
import BottomNavbarComponent from '@/components/navbar/BottomNavbarComponent.vue'
import NotificationComponent from "@/components/common/NotificationComponent.vue";
import QuizPopupComponent from "@/components/quiz/QuizPopupComponent.vue";
import { EventSourcePolyfill } from 'event-source-polyfill';
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";
const userStore = useUserStore();
const popup = ref(false);
const router = useRouter();

const isPopupClicked = function () {
  popup.value = !popup.value;
  if(pushedData.value.notificationType == 4) {
    router.push({name : 'DMListView', params : {id : userStore.kakaoUserInfo['id']} })
  } else {
    router.push({ name: 'NotificationPageView', });
  }
}

const quizPopup = ref(false);
const pushedData = ref({});
const lastEventId = ref(null);
const SSE = ref(null);

onMounted(() => {
  initSSE();
})

const showPopup = () => {
  popup.value = true;
  setTimeout(() => {
    popup.value = false;
  }, 3000);
}

const initSSE = () => { 
  if (!SSE.value) {
    const token = JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token;

    SSE.value = new EventSourcePolyfill(
      'https://i10d112.p.ssafy.io/devoca/notify/connect?Last-Event-ID=' + lastEventId.value,
      {
        headers: { token: token },
        withCredentials: true,
        heartbeatTimeout: 300000000,
      }
    );

    SSE.value.addEventListener('sse', (event) => {
      console.log(event.data);
      if (event.data != "SSE Connected.") {
        console.log("pushed!");
        pushedData.value = JSON.parse(event.data);
        if(pushedData.value.notificationType == 4) {
          userStore.isDMNotify = true;
        } else {
          userStore.isNotify = true;
        }
        showPopup();
      }

      const eventId = event.lastEventId;
      if (eventId) {
        lastEventId.value = eventId;
      }
    })

    SSE.value.onerror = (err) => {
      console.log(err);
      SSE.value.close();
      console.log("SSE 종료!");
      SSE.value = null;
    }
  } else {
    console.log("기존 SSE 연결 이용");
  }
}

</script>