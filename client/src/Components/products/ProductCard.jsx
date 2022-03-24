import {
    Card,
    CardContent,
    Grid,
    Typography,
    Avatar
} from "@material-ui/core";
import CategoryIcon from '@mui/icons-material/Category';
import AppColors from "../../utils/CommonStyles";
import { styled } from '@mui/material/styles';

const ProductCard = ({ name, lastPrice, taxFreePrice, vatPrice, ...props }) => {

    return (
        <CustomCard sx={{ height: "100%" }} {...props}>
            <CardContent>
                <Grid container spacing={2}>
                    <Grid item xs={8} md={8} lg={8}>
                        <Grid container>
                            <Grid item xs={12} md={12} lg={12}>
                                <Typography color='textPrimary' gutterBottom variant='h4' align="left">
                                    {name}
                                </Typography>
                            </Grid>
                            <Grid item xs={12} md={12} lg={12}>
                                <Typography color='textPrimary' variant='h4' align="left">
                                    {lastPrice}$
                                </Typography>
                            </Grid>
                            <Grid item xs={12} md={12} lg={12}>
                                <Typography color='textSecondary' variant='body1' align="left">
                                    VAT Price {vatPrice}$
                                </Typography>
                            </Grid>
                            <Grid item xs={12} md={12} lg={12}>
                                <Typography color='textSecondary' variant='body1' align="left">
                                    Tax Free Price {taxFreePrice}$
                                </Typography>
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid item xs={4} md={4} lg={4}>
                        <img
                            style={{width: "6rem", height: "6rem"}}
                            src={require("../../assets/productImage.png")}
                            alt={"Logo"}
                        />
                    </Grid>
                </Grid>
            </CardContent>
        </CustomCard>
    );
}

const CustomCard = styled(Card)(() => ({
    '&:hover': {
        backgroundColor: AppColors.grayLighter,
    },
}));

export default ProductCard;

