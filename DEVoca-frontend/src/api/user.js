import { localAxios } from '@/util/http-commons'

const local = localAxios()

export function joinUser(data, success, fail) {
  local.post('/user', data).then(success).catch(fail)
}

export function getKaKaoToken(param, success, fail) {
  local.get(`/kakao/callback?code=${param}`).then(success).catch(fail)
}

export function getUserInfo(success, fail) {
  local.get(`/user`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getOtherUserInfo(otherId, success, fail) {
  local.get(`/user/${otherId}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getUserBadge(success, fail) {
  local.get(`/mypage/badge`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getOtherFollowList(otherId, success, fail) {
  local.get(`/user/follower/${otherId}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getOtherFollowingList(otherId, success, fail) {
  local.get(`/user/following/${otherId}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

