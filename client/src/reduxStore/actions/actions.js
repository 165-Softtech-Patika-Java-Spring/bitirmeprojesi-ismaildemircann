export const GET_PRODUCTS = "GET_PRODUCTS";

export const getProductSuccess = (products) => {
    return {
        type: GET_PRODUCTS,
        products: products,
    };
};