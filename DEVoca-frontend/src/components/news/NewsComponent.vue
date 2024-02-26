<template>
    <a :href="news.link">
        <div class="card card-compact bg-base-100 shadow-xl m-2 text-xs">
            <div class="card-body">
                <h2 class="card-title text-sm">
                    <NewsIcon /><div v-html="news.title"></div>
                </h2>
                <hr>
                <p v-html="news.description" class="text-wrap break-words truncate"></p>
                <p class="text-right text-gray-500">{{ pubDate }}</p>
            </div>
        </div>
    </a>
</template>

<script setup>
import { ref } from 'vue'
import NewsIcon from '@/components/icon/NewsIcon.vue';

const props = defineProps({
    news: Object
})

const datetime = new Date(props.news.pubDate)
const locale = 'ko-KR'
const options = { timeZone: 'Asia/Seoul' }
const pubDate = ref(datetime.toLocaleDateString(locale, options))
</script>