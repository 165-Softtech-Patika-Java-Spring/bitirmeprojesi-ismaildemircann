import axios from 'axios';

const loginReq = (username, password) => {

    const data = {
        username: username,
        password: password
    }

    const url = "/auth/login"

    return axios.post(url, data);
}

export default loginReq;