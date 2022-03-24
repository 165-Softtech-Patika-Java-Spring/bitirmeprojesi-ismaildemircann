import { ImageList, ListItem } from "@material-ui/core";
import ProductCard from "./ProductCard";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getProductList } from "../../api/ProductService";

const ProductContainer = () => {

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getProductList());
    }, [dispatch]);

    const productsData = useSelector((state) => state.products.data);

    return (
        <ImageList cols={3} gap={15}>
            {productsData.map((item) => (
                <ListItem key={`item-${item?.id}-${item}`}>
                    {<ProductCard
                        name={item.name}
                        lastPrice={item.lastPrice}
                        taxFreePrice={item.taxFreePrice}
                        vatPrice={item.vatPrice} />
                    }
                </ListItem>
            ))}
        </ImageList >
    );
}

export default ProductContainer;

