import {
    CssBaseline, Container, Box, Typography
} from "@material-ui/core";
import { useState, useEffect } from "react";
import useStyles from "./DashboardStyle";
import ProductContainer from "../products/ProductContainer";
import Copyright from "../copyright/Copyright";

const Dashboard = () => {

    const classes = useStyles();

    const [token, setToken] = useState("");

    useEffect(() => {
        setToken(sessionStorage.getItem('token'));
    }, []);

    const renderContent = () => {
        return (
            <div className={classes.root}>
                <CssBaseline />
                <Container maxWidth='xl' className={classes.container}>
                    <ProductContainer />
                    <Box pt={4}>
                        <Copyright />
                    </Box>
                </Container>
            </div>
        )
    }

    return (
        <div>
            {token == null ?
                <Typography component='h1' variant='h6'>
                    Please login to view the page!
                </Typography>
                :
                renderContent()
            }
        </div>
    )
}

export default Dashboard;

