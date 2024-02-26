import { localAxios } from '@/util/http-commons'

const local = localAxios()

export function createVocaList(data, success, fail) {
  local.post('/vocalist', data, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getVocaList(success, fail) {
  local.get('/vocalist', {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getVocaListDetail(vocaListId, success, fail) {
  local.get(`/vocalist/detail/${vocaListId}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function deleteVocaList(vocaListId, success, fail) {
  local.delete(`/vocalist/list/${vocaListId}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function storeVocaList(data, success, fail) {
  local.post('/vocalist/store', data, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export async function checkVocaList(cardId, success, fail) {
  await local.get(`/vocalist/${cardId}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function cancelVocaList(vocaListId, cardId, success, fail) {
  local.delete(`/vocalist/${vocaListId}/${cardId}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}