<template>
    <div class="flex flex-col relative">
        <img v-if="!isCorrect" src="@/assets/images/quiz/incorrect.png"
        class="w-10 h-10 absolute top-0 left-3 transform -translate-x-1/2">
        <img v-if="isCorrect" src="@/assets/images/quiz/correct.png"
        class="w-12 h-12 absolute top-0 left-3 transform -translate-x-1/2">
        <p class="text-lg my-3">{{ props.index + 1 }}. {{ props.quiz.wordSumm }}</p>
        <div class="flex justify-start"  v-if="!isCorrect">
            <p class="text-sm bg-red-100 rounded-lg shrink-0 h-6 p-1 mr-5 mt-1 text-center">오답</p>
            <p class="text-[red] text-lg p-1 flex justify-start mr-5">{{ props.quiz.quizAnswer }}</p>
        </div>
        <div class="flex justify-start">
            <p class="text-sm bg-devoca_skyblue rounded-lg shrink-0 h-6 p-1 mr-5 mt-1 text-center">정답</p>
            <div class="flex-col p-1">
                <p class="text-lg break-words justify-end" v-if="isCorrect">{{ props.quiz.quizAnswer }}</p>
                <p class="text-lg break-words justify-end" v-if="!isCorrect">{{ props.quiz.wordNameKr }}</p>
                <p class="text-lg break-words justify-end" v-if="!isCorrect">{{ props.quiz.wordNameEn }}</p>
            </div>
        </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
const props = defineProps({
    quiz: Object,
    index: Number
})
const isCorrect = ref(null);

onMounted(() => {
    isCorrect.value = props.quiz.quizYn;
})

</script>

<style>
</style>