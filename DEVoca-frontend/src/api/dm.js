import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function getDmRoomList(success, fail) {
    await local.get('/dm', {
        headers: {
          token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }}).then(success).catch(fail);
}

async function getDmUser(roomUuid, success, fail) {
    await local.get(`/dm/${roomUuid}/user`, {
        headers: {
          token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }}).then(success).catch(fail);
}

async function getDmList(roomUuid, scroll, success, fail) {
    await local.get(`/dm/${roomUuid}/${scroll}`, {
      headers: {
        token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
      }}).then(success).catch(fail);
}

async function getRoomUuid(chatUserId, success, fail){
  await local.get(`/dm/${chatUserId}`,{
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }}).then(success).catch(fail);
  }

export {
    getDmRoomList,
    getDmList,
    getRoomUuid,
    getDmUser,
};