import { localAxios } from '@/util/http-commons'

const local = localAxios()

export function getWordList(param, success, fail) {
  local.get(`/dictionary/${param}`).then(success).catch(fail)
}

export async function getWordDetail(param, success, fail) {
  await local.get(`/dictionary/detail/${param}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getCardListByWord(param, success, fail) {
  local.get(`/dictionary/detail/${param}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getWordInCard(param, success, fail) {
  local.get(`/dictionary/select/${param}`).then(success).catch(fail)
}