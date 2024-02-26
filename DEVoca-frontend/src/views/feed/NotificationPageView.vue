<template>
  <div>
    <div class="flex">
      <div>
        <p class="ml-5 mr-3 text-xl">내</p>
      </div>
      <div>
        <p class="text-xl text-devoca">알림</p>
      </div>
    </div>

    <div>
      <NotificationPageComponent v-for="(notify, index) in notifyList" :key="index" :notify="notify"/>
    </div>
  </div>
</template>

<script setup>
import NotificationPageComponent from "@/components/notify/NotificationPageComponent.vue";
import { getNotification } from "@/api/notify";
import { ref, onMounted } from "vue";
import { useUserStore } from "@/stores/user";
const userStore = useUserStore();
const scroll = ref(0);
const notifyList = ref([]);

onMounted(() => {
  userStore.isNotify = false;
  getNotification(scroll.value, (data) => {
    notifyList.value = data.data;
  }, (err) => {
    console.log(err);
  });

})


</script>

<style scoped>
</style>