import axios from 'axios';
import { getProductSuccess } from "../reduxStore/actions/actions";

export const getProductList = () => {

    let token = sessionStorage.getItem('token');
    if (token === null) {
        token = localStorage.getItem('token');
    }

    return (dispatch) =>
        axios({
            method: 'get',
            url: '/api/v1/products',
            headers: {
                'Authorization': token
            }
        })
            .then((response) => {
                dispatch(getProductSuccess(response.data));
            })
            .catch(error => {
                console.log(error);
            });
}