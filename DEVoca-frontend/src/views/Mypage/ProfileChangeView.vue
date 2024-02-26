<template>
  <form class="card-body" @submit.prevent="modifyInfo">
    <img id="image" src="/profile.png" alt="" class="mt-5 m-auto w-40 h-40 rounded-full">
    <!-- <div class="flex justify-center">
      <a class="upload" @click="triggerFileUpload">프로필 사진 선택하기</a>
      <input type="file" id="profileUpload" @change="handleFileUpload" style="display:none">
    </div> -->

    <div class="form-control">
      <label class="label">
        <span class="label-text">닉네임</span>
      </label>
      <input type="text" id="user-nickname" placeholder="닉네임자리" class="input input-bordered" required 
      v-model="inputData.userNickName"/>
    </div>
    <div class="form-control">
      <label class="label">
        <span class="label-text">한 줄 소개</span>
      </label>
      <input type="text" id="user-intro" placeholder="한줄소개" class="input input-bordered" required 
      v-model="inputData.userIntro"/>
    </div>
    <div class="flex justify-end">
      <button class="btn btn-sm w-24 bg-devoca text-white ">정보 수정</button>
    </div>
  </form>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { updateUserInfo, uploadImage } from '@/api/mypage'
import { getUserInfo } from '@/api/user';
import { useRouter } from 'vue-router'

const router = useRouter()

const inputData = ref({
  userNickName: null,
  userIntro: null
})

onMounted(() => {
  getUserInfo((res) => {
    const imageUrl = res.data.userImg;
    inputData.value.userNickName = res.data.userNickName;
    inputData.value.userIntro = res.data.userIntro;
    document.getElementById('image').src = imageUrl;
  })
})

const triggerFileUpload = function() {
  console.log("triggerFileUpload")
  document.getElementById('profileUpload').click();
}

const handleFileUpload = function(e) {
  const imgname = e.target.files[0]
  console.log(e.target.files[0])

  const formData = new FormData();
  formData.append('image', imgname);

  uploadImage(formData, (res) => {
    const imageUrl = res.data;
    document.getElementById('image').src = imageUrl;
  }, null)
}

const modifyInfo = function() {
  updateUserInfo(inputData.value, (res) => {
    router.push({name: 'MypageView'})
  }, (err) => {
    console.log(err)
  })
}
</script>

<style scoped></style>