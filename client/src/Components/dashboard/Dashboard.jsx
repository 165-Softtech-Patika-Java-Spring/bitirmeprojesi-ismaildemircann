import {
    CssBaseline, Typography, Container, Box
} from "@material-ui/core";
import { useState, useEffect } from "react";
import useStyles from "./DashboardStyle";
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
                <main className={classes.content}>
                    <Container maxWidth='xl' className={classes.container}>
                        <Box pt={4}>
                            <Copyright />
                        </Box>
                    </Container>
                </main>
            </div>
        )
    }

    return (
        <div>
            {token == null ?
                <Typography component='h1' variant='h6'>
                    Sayfayı görüntüleyebilmek için lütfen giriş yapınız!
                </Typography>
                :
                renderContent()
            }
        </div>
    )
}

export default Dashboard;

