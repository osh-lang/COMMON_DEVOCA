<template>
  <div class="h-screen flex items-center">
    <div class="card shrink-0 w-full max-w-sm bg-base-100 mx-auto">
      <div class="flex justify-center">
        <img src="@/assets/images/DEVoca_logo_en_only.png" class="w-3/4">
      </div>
      <p class="text-center mt-5">관심분야에 따라</p>
      <p class="text-center">다음 이용자를 추천합니다!</p>
      <div>
        <!-- 변경예정 -->
        <!-- <FollowCardComponent/>
        <FollowCardComponent/>
        <FollowCardComponent/>
        <FollowCardComponent/>
        <FollowCardComponent/>
        <FollowCardComponent/> -->
      </div>
      <div class="form-control mt-6">
        <button class="btn bg-devoca">완료</button>
      </div>
      <RouterLink :to="{name: 'MainView'}" class="text-center underline mt-5">나중에 하기</RouterLink>
    </div>
  </div>
</template>

<script setup>
import FollowCardComponent from '@/components/common/FollowCardComponent.vue';

</script>
