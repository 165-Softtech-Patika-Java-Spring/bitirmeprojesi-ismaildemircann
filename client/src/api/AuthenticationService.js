import axios from 'axios';

export const loginReq = (username, password) => {

    const data = {
        username: username,
        password: password
    }

    const url = "/auth/login"

    return axios.post(url, data);
}

export const registerReq = (userSaveDto) => {

    const url = "/auth/register"

    return axios.post(url, userSaveDto);
}