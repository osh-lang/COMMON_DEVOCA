<template>
<div onclick="">
    <div role="alert" class="alert flex flex-row bg-opacity-30 w-screen">
        <img src="@/assets/images/DEVoca_logo_initial.png" viewBox="0 0 24 24" class="stroke-info shrink-0 w-12 h-11" alt="devoca_logo">
        <div class="flex flex-col items-start">
            <h1 class="font-bold text-lg">DEVoca</h1>
            <h3>{{ notification }}</h3>
        </div>
    </div>
</div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
const props = defineProps({
    pushedData: {
      type: Object,
      required: true
    },
  });

const notificationString = ["내 게시글에 댓글이 달렸어요.", "내 댓글이 채택되었어요.",
    "내 카드가 좋아요를 받았어요.", "내 카드가 재게시되었어요.",
    "새로운 DM이 도착했어요.", "좋은 아침입니다! 오늘의 퀴즈가 도착했어요.",
    "내 카드에 댓글이 달렸어요."];

const notificationType = ref(5);
const notification = computed(() => notificationString[notificationType.value]);

onMounted(() => {
    notificationType.value = props.pushedData.notificationType;
})
</script>