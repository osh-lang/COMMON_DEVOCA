import { localAxios } from '@/util/http-commons'

const local = localAxios()

export function registCard(data, success, fail) {
  local.post('/card', data).then(success).catch(fail)
}

export function getCardList(scroll, success, fail) {
  local.get(`/card/list/${scroll}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getCardListByUserId(param, success, fail) {
  local.get(`/card/list/${param}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export async function getCardDetail(cardId, success, fail) {
  await local.get(`/card/${cardId}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function updateCard(cardId, data, success, fail) {
  local.patch(`/card/${cardId}`, data).then(success).catch(fail)
}

export function deleteCard(cardId, success, fail) {
  local.delete(`/card/${cardId}`).then(success).catch(fail)
}

export function repostCard(data, success, fail) {
  local.post('/card/repost', data).then(success).catch(fail)
}

export function likeCard(cardId, data, success, fail) {
  local.post(`/card/like/${cardId}`, data, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function registComment(comment, success, fail) {
  local.post('/comment', comment, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getCommentList(cardId, success, fail) {
  local.get(`/comment/card/${cardId}`).then(success).catch(fail)
}

export function deleteComment(commentId, success, fail) {
  local.delete(`/comment/${commentId}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}