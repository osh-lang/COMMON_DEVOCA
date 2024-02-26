import { localAxios } from '@/util/http-commons'
import axios from 'axios';

const local = localAxios()

export function getMyCard(param, success, fail) {
    local.get(`/mypage/mycard/${param}`, {
        headers: {
            token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }
    }).then(success).catch(fail)
}

export function getMyBoard(success, fail) {
    local.get(`/mypage/myboard`, {
        headers: {
            token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }
    }).then(success).catch(fail)
}

export function getMyComment(success, fail) {
    local.get(`/mypage/mycomment`, {
        headers: {
            token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }
    }).then(success).catch(fail)
}

export function getFollower(success, fail) {
    local.get(`/mypage/follower`, {
        headers: {
            token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }
    }).then(success).catch(fail)
}

export function getFollowing(success, fail) {
    local.get(`/mypage/following`, {
        headers: {
            token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }
    }).then(success).catch(fail)
}

export function followUser(userId, success, fail) {
    local.get(`/mypage/follow/${userId}`, {
        headers: {
            token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }
    }).then(success).catch(fail)
}

export function unfollowUser(userId, success, fail) {
    local.delete(`/mypage/unfollow/${userId}`, {
        headers: {
            token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }
    }).then(success).catch(fail)
}

export function uploadImage(formData, success, fail){
    axios.post(`https://i10d112.p.ssafy.io/devoca/mypage/profile`, formData, {
        headers: {
            token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }
    }).then(success).catch(fail)
}

export function updateUserInfo(data, success, fail){
    local.patch(`/user`, data, {
        headers: {
            token: JSON.parse(localStorage.getItem('user')).kakaoUserInfo.token
        }
    }).then(success).catch(fail)
}