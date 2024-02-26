<template>
  <div class="card-body">
    <div class="flex flex-col items-center">
      <div class="flex flex-grow justify-center mt-5">
        <img src="@/assets/images/badge.png" alt="" class="w-8 h-8">
        <p class="text-3xl">배지 획득 현황</p>
      </div>
      <div class="flex justify-center mt-3">
        배지 {{ userBadge.length }}개 중 {{ userBadge.filter(b => b.badgeHaveYn === '1').length }}개 보유
      </div>
      <div class="relative mt-8">
        <div class="justify-center mt-8 grid grid-cols-3 gap-4">
          <template v-for="(badge, index) in userBadge" :key="index">
            <div class="checkbox-container mr-4">
              <label class="swap swap-flip text-3xl">
                <input type="checkbox" @click="selectBadge(badge)" v-model="badge.isSelected"/>
                <div class="swap-on">
                  <img :src="getImgUrl(badge.badgeImgName, 1)">
                </div>
                <div class="swap-off">
                  <img :src="getImgUrl(badge.badgeImgName, badge.badgeHaveYn)">
                </div>
              </label>
            </div>
            <dialog id="badge_modal" class="modal" :class="{'modal-open' : badge.isSelected}">
              <div class="modal-box h-32 flex flex-col justify-center bg-opacity-95">
                <h2 class="card-title">{{ badge.badgeName }}</h2>
                <p class="grow-0">{{ badge.badgeInfo }}</p>
              </div>
              <form method="dialog" class="modal-backdrop" @click="badge.isSelected = false">
                <button>close</button>
              </form>
            </dialog>
          </template>
        </div>
      </div>
    </div>
  </div>
  
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getUserBadge } from '@/api/user.js'

const userBadge = ref([])

onMounted(() => {
  getUserBadge((res) => {
    userBadge.value = res.data.map(badge => ({
      ...badge,
      isSelected: false // 각 뱃지 객체에 isSelected 속성 추가
    }))
  }, (err) => {
    console.err(err)
  })
})

const getImgUrl = (badgeImgName, badgeHaveYn) => {
  return new URL(`/src/assets/images/badge/${badgeImgName}_${badgeHaveYn}.png`, import.meta.url).href
}

const selectBadge = (badge) => {
  console.log(badge.isSelected)
  badge.isSelected = !badge.isSelected
  console.log(badge.isSelected)
}
</script>

<style scope>
/* 기존에 정의하려고 했던 ::backdrop 스타일을 대체합니다. */
#badge_modal.modal-open {
  background: transparent;
}
</style>
