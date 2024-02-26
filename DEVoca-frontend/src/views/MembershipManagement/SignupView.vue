<template>
  <div class="h-screen flex items-center">
    <div class="card shrink-0 w-full max-w-sm bg-base-100 mx-auto">
      <div class="flex justify-center">
        <img src="@/assets/images/DEVoca_logo_en_only.png" class="w-3/4">
      </div>
      <form class="card-body my-2" @submit.prevent="submitUserInfo">
        <div class="form-control">
          <label class="label">
            <span class="label-text">이메일</span>
          </label>
          <input type="email" placeholder="example@naver.com" class="input input-bordered" required v-model.trim="userInfo.userEmail" />
        </div>
        <div class="form-control">
          <label class="label">
            <span class="label-text">이름</span>
          </label>
          <input type="text" placeholder="이름" class="input input-bordered" required v-model.trim="userInfo.userName" />
        </div>
        <div class="form-control">
          <label class="label">
            <span class="label-text">닉네임</span>
          </label>
          <input type="text" placeholder="닉네임" class="input input-bordered" required v-model.trim="userInfo.userNickName" />
        </div>
        <div class="form-control">
          <label class="label">
            <span class="label-tex">생년월일</span>
          </label>
          <input ref="datepicker" class="input input-bordered w-full" type="text" placeholder="날짜를 선택해 주세요" v-model.trim="userInfo.userBirth" >
        </div>

        <div class="form-control mt-6">
          <button class="btn bg-devoca text-white text-xl align-bottom">회원가입</button>
        </div>
      </form>
    </div>
  </div>
</template>
  
<script setup>
import { ref, onMounted, onBeforeMount } from 'vue'
import flatpickr from 'flatpickr'
import 'flatpickr/dist/flatpickr.min.css'
import { useRouter } from 'vue-router'
import { joinUser } from '@/api/user'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()
const datepicker = ref(null)
const userInfo = ref({
  userId: userStore.kakaoUserInfo.id,
  userEmail: null,
  userName: null,
  userNickName: null,
  userBirth: null
})

const submitUserInfo = function () {
  joinUser(userInfo.value, (res) => {
    router.push({name: 'MainView'})
  }, (err) => {
    console.log(err)
  })
}

onBeforeMount (() => {
  if (!userStore.kakaoUserInfo.token) {
    router.push({ name: 'MainView' });
    alert("비정상적인 접근입니다.");
  }
})

onMounted(() => {
  flatpickr(datepicker.value, {
    appendTo: document.body,
    static: true,
  })
})
</script>
