import {
    Typography,
    Container,
    Box
} from "@material-ui/core";
import useStyles from "./HomePageStyle";

const HomePage = () => {

    const classes = useStyles();

    return (
        <Container component='main' maxWidth='sm'>
            <img
                className={classes.logo}
                src={require("../../assets/softtechLogo.jpg")}
                alt={"Logo"}
            />
            <Typography className={classes.header} component='h1' variant='h5'>
                Softtech Graduation Project 
            </Typography>
            <Box className={classes.box} />
        </Container>
    );
}

export default HomePage;

