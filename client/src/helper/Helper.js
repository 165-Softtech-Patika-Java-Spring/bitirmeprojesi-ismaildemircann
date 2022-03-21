
export const mapUserData = (res) => {
    return res.data.map(el => {
        let {
            id, username, fullname, readDataBankTitle
        } = el;
        return [id, username, fullname, readDataBankTitle];
    });
}