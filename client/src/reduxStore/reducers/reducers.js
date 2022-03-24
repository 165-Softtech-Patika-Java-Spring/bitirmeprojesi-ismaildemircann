import * as actions from "../actions/actions";
import { updateObject } from "../../helper/Helper";

const initialState = {
    data: [],
};

export const productReducer = (state = initialState, action) => {
    if (action.type === actions.GET_PRODUCTS) {
        return updateObject(action.products);
    }
    return state;
};