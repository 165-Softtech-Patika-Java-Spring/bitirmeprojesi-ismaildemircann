import { makeStyles } from "@material-ui/core/styles";
import AppColors from "../../utils/CommonStyles"

const useStyles = makeStyles(() => ({
    logo: {
        width: "75%",
        marginTop: 50
    },
    header: {
        marginTop: 20,
    },
    box: {
        width: "95%",
        height: 10,
        margin: 10,
        backgroundColor: AppColors.red
    }
}));
export default useStyles;
