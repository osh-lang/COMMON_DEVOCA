import { localAxios } from '@/util/http-commons'

const local = localAxios()

export function createBoard(data, success, error) {
  local.post(`/board`, data, {
    headers: {
        token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
})
.then(success)
.catch((error)=>{
  console.log(error)
})
}

export function getBoardList(boardType, success, error) {
  local.get(`/board/list/${boardType}`)
    .then(success)
    .catch(error);
}

export function deleteBoard(data,success, error){
  local.patch(`/board/${boardid}`,data,{
    headers: {
        token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
})
.then(success)
.catch((error) => {
  console.log(error)
})
}

export function getBoardDetail(boardId, success, error){
  local.get(`/board/${boardId}`, {
    headers: {
      token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
    }
  }).then(success).catch(error)
}

export function getCommentList(flag, cardBoardId, success, error){
  local.get(`/comment/${flag}/${cardBoardId}`
  ).then(success).catch(error)
}