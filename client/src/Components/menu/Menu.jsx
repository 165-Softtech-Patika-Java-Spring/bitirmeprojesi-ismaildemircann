import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import AppColors from '../../utils/CommonStyles';

export default function Menu({ isLoggedOn, logout, ...props }) {

    const handleLogout = () => {
        logout();
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" style={{ background: AppColors.blue }}>
                <Toolbar>
                    <Button variant="contained" href="/">
                        Main Page
                    </Button>
                    <Button variant="contained" href="/dashboard">
                        Products
                    </Button>
                    {isLoggedOn
                        ? <Button variant="contained" href="/login" onClick={handleLogout}>Logout</Button>
                        : (<>
                            <Button variant="contained" href="/sign-up">Sign Up</Button>
                            <Button variant="contained" href="/login">Login</Button>
                        </>)
                    }
                </Toolbar>
            </AppBar>
        </Box>
    );
}


