import { localAxios } from '@/util/http-commons'

const local = localAxios()

export function getNotification(scroll, success, fail) {
  local.get(`/notify/${scroll}`, {
    headers: {
        token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(fail)
}
