import { localAxios } from '@/util/http-commons'

const local = localAxios()

export function getQuiz(quizId, success, fail) {
  local.get(`/quiz/${quizId}`).then(success).catch(fail)
}

export function saveQuizResult(data, success, fail) {
  console.log(data);
  local.post(`/quiz/save`, data).then(success).catch(fail)
}

export function saveBattleResult(data, success, fail) {
  console.log(data);
  local.post(`/quiz/battle/save`, data).then(success).catch(fail)
}

export function getQuizCnt(success, fail) {
  local.get(`/quiz/cnt`, {
    headers: {
        token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getQuizResultList(success, fail) {
  local.get(`/quiz/result`, {
    headers: {
        token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getQuizResultDetail(quizId, success, fail) {
  local.get(`/quiz/result/${quizId}`, {
    headers: {
        token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getBattleResultDetail(quizId, success, fail) {
  local.get(`/quiz/battle/result/${quizId}`, {
    headers: {
        token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function getBattleVocaList(oppoUserId, success, fail) {
  local.get(`/quiz/battle/vocalist`, {params: {oppoUserId: oppoUserId}, 
    headers: {
        token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}

export function createBattle(sendVocaList, success, fail) {
  local.post(`/quiz/battle`, sendVocaList, {
    headers: {
        token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail);
}